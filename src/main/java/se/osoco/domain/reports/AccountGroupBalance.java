package se.osoco.domain.reports;

import se.osoco.domain.Amount;
import se.osoco.domain.account.Account;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AccountGroupBalance {

    private final String text;
    private final List<Account> accounts = new ArrayList<>();

    public AccountGroupBalance(final String text, final List<Account> accounts) {
        this.text = text;
        this.accounts.addAll(accounts);
    }

    public String text() {
        return text;
    }

    public boolean isEmpty() {
        return opening().value().equals(BigDecimal.ZERO) && period().value().equals(BigDecimal.ZERO) && closing().value().equals(BigDecimal.ZERO);
    }

    public Amount opening() {
        BigDecimal opening = BigDecimal.ZERO;
        for (Account account : accounts) {
            opening = opening.add(account.openingBalance().value());
        }
        return new Amount(opening);
    }

    public Amount lastYear() {
        BigDecimal opening = BigDecimal.ZERO;
        for (Account account : accounts) {
            opening = opening.add(account.lastYear().value());
        }
        return new Amount(opening);
    }

    public Amount period() {
        BigDecimal period = BigDecimal.ZERO;
        for (Account account : accounts) {
            period = period.add(account.period().value());
        }
        return new Amount(period);
    }

    public Amount closing() {
        return new Amount(opening().value().add(period().value()));
    }

    public int size() {
        return accounts.size();
    }

    public List<Account> accounts() {
        return accounts;
    }

    public boolean isBalanceAccounts() {
        return accounts.stream().allMatch(a -> a.number() < 2999);
    }
}