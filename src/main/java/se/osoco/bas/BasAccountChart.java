package se.osoco.bas;

import se.osoco.domain.account.AccountMetaData;
import se.osoco.domain.accounting.AccountChart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class BasAccountChart implements AccountChart {

    private HashMap<String,AccountMetaData> accountChartMap = new HashMap<>();

    public BasAccountChart(List<AccountMetaData> accounts) {
        for (AccountMetaData accountMetaData : accounts) {
            accountChartMap.put(accountMetaData.number(), accountMetaData);
        }
    }


    @Override
    public List<AccountMetaData> asList() {
        return new ArrayList<>(accountChartMap.values());
    }

    @Override
    public AccountMetaData get(int accountNumber) {
        AccountMetaData accountMetaData = accountChartMap.get(String.valueOf(accountNumber));
        if (accountMetaData == null) {
            throw new IllegalArgumentException("No account found in BAS with number: " + accountNumber);
        }
        return accountMetaData;
    }

    @Override
    public int size() {
        return accountChartMap.size();
    }
}
