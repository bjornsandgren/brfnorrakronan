package se.osoco.domain.reports;

import se.osoco.domain.Amount;
import se.osoco.domain.account.Account;

import java.util.List;

public class IncomeStatementAccounts extends AccountGroupBalance {
    public IncomeStatementAccounts(String text, List<Account> accounts) {
        super(text, accounts);
    }

    @Override
    public Amount period() {
        return new Amount(super.period().value().negate());
    }
}