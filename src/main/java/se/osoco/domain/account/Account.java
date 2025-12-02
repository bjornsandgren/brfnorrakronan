package se.osoco.domain.account;

import se.osoco.domain.Amount;
import se.osoco.domain.Transaction;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Account {
    private final AccountMetaData accountInfo;
    private final List<Transaction> transactions = new ArrayList<>();
    private final AccountNumber accountNumber;

    public Account(AccountMetaData accountInfo, List<Transaction> transactions) {
        this.accountInfo = accountInfo;
        if (transactions != null) {
            this.transactions.addAll(transactions);
        }
        this.accountNumber = new AccountNumber(accountInfo.number());
    }

    public int number() {
        return accountNumber.value();
    }

    public String text() {
        return accountInfo.name();
    }

    public Amount openingBalance() {
        return new Amount(accountInfo.openingBalance());
    }

    public Amount closingBalance() {
        if (accountInfo.closingBalance() != null) {
            //use set value from SIE as a short cut
            return new Amount(accountInfo.closingBalance());
        }
        BigDecimal ub = accountInfo.openingBalance();
        for(Transaction verificationTransaction : transactions) {
            ub = ub.add(verificationTransaction.amount());
        }
        return new Amount(ub);
    }

    public Amount lastYear() {
        return new Amount(accountInfo.lastYearClosing());
    }

    public boolean isEmpty() {
        return accountInfo.openingBalance().equals(BigDecimal.ZERO)
                &&
                transactions.isEmpty()
                &&
                accountInfo.lastYearClosing().equals(BigDecimal.ZERO);
    }

    public Amount period() {
        BigDecimal period = BigDecimal.ZERO;
        for (Transaction verificationTransaction : transactions) {
            period = period.add(verificationTransaction.amount());
        }
        return new Amount(period);
    }

    public boolean isType(AccountGroup accountGroup) {
        return accountNumber.isSubType(accountGroup);
    }

    public boolean isYearResult() {
        return accountNumber.value() == 8999;
    }

    public List<Transaction> transactions() {
        return Collections.unmodifiableList(transactions);
    }

    public String name() {
        return accountInfo.name();
    }
}