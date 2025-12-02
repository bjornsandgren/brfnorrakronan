package se.osoco.domain.account;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AccountNumber {

    private String number;

    public AccountNumber(String number) {
        Objects.requireNonNull(number);
        validate(number);
        this.number = number;
    }

    public Integer value() {
        return Integer.valueOf(number);
    }

    public Type type() {
        int value = Integer.parseInt(number.substring(0,1));
        return new Type(value);
    }

    public Type subType() {
        int value = Integer.parseInt(number.substring(0,2));
        return new Type(value);
    }

    private void validate(String number) {
        Pattern pattern = Pattern.compile("^[0-9]{4}$");
        Matcher matcher = pattern.matcher(number);
        if (!matcher.find()) {
            throw new IllegalArgumentException("Not a account number: " + number);
        }
    }

    public boolean isSubType(AccountGroup accountType) {
        return subType().number() >= accountType.start() && subType().number() < accountType.end();
    }
}
