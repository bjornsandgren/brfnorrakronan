package se.osoco.domain.accounting;

import se.osoco.domain.Transaction;
import se.osoco.domain.Verification;
import se.osoco.domain.account.Account;
import se.osoco.domain.account.AccountMetaData;
import se.osoco.domain.account.AccountGroup;
import se.osoco.domain.reports.AccountGroupBalance;

import java.util.*;

public class GeneralLedger {
    private final Map<String, Account> accountIndex = new TreeMap<>();

    public GeneralLedger(Series series, AccountChart accountChart) {
        Map<String,List<Transaction>> accountTransactions = series.accountTransactions();
        for(AccountMetaData accountInfo : accountChart.asList()) {
            accountIndex.put(accountInfo.number(), new Account(accountInfo, accountTransactions.get(accountInfo.number())));
        }
    }

    public List<Account> accountList() {
        List<Account> accountList = new ArrayList<>(accountIndex.values());
        Collections.sort(accountList, Comparator.comparingInt(Account::number));
        return accountList;
    }

    public Account account(int number) {
        return accountIndex.get(String.valueOf(number));
    }

    public AccountGroupBalance accountsBalance(AccountGroup accountGroup) {
        return new AccountGroupBalance(accountGroup.groupName(), accounts(accountGroup));
    }

    public AccountGroupBalance accountsBalance(String text, AccountGroup... accountGroups) {
        List<Account> accounts = new ArrayList<>();
        for (AccountGroup accountGroup: accountGroups) {
            accounts.addAll(accounts(accountGroup));
        }
        return new AccountGroupBalance(text, accounts);
    }

    public AccountGroupBalance accountsBalance(String text, AccountGroupBalance accountGroupBalance, AccountGroup... accountGroups) {
        List<Account> accounts = new ArrayList<>();
        accounts.addAll(accountGroupBalance.accounts());
        for (AccountGroup accountGroup: accountGroups) {
            accounts.addAll(accounts(accountGroup));
        }
        return new AccountGroupBalance(text, accounts);
    }

    public AccountGroupBalance accountsBalance(String text, AccountGroupBalance... accountGroupBalance) {
        List<Account> accounts = new ArrayList<>();
        for (AccountGroupBalance a : accountGroupBalance) {
            accounts.addAll(a.accounts());
        }
        return new AccountGroupBalance(text, accounts);
    }

    private List<Account> accounts(AccountGroup accountGroup) {
        List<Account> accounts = new ArrayList<>();
        for (Map.Entry<String,Account> entry : accountIndex.entrySet()) {
            Account account = entry.getValue();
            if (account.isType(accountGroup)) {
                if (!account.isEmpty() && !account.isYearResult()) {
                    accounts.add(account);
                }
            }
        }
        return accounts;
    }

}