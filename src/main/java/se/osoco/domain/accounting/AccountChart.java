package se.osoco.domain.accounting;

import se.osoco.domain.account.AccountMetaData;

import java.util.Comparator;
import java.util.List;

public interface AccountChart {

    default List<AccountMetaData> asSortedList() {
        List<AccountMetaData> accounts = asList();
        return accounts.stream().sorted((Comparator.comparing(AccountMetaData::number))).toList();
    }

    List<AccountMetaData> asList();

    AccountMetaData get(int accountNumber);

    int size();
}