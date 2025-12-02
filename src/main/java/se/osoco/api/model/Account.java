package se.osoco.api.model;

import java.math.BigDecimal;

public class Account implements Comparable<Account> {
    private final int number;
    private final String name;
    private final BigDecimal openingBalance;
    private final BigDecimal closingBalance;

    public Account(se.osoco.domain.account.Account account) {
        this.number = account.number();
        this.name = account.text();
        this.openingBalance = account.openingBalance().value();
        this.closingBalance = account.closingBalance().value();
    }

    @Override
    public int compareTo(Account otherAccount) {
        return Integer.compare(this.number, otherAccount.number);
    }
}
