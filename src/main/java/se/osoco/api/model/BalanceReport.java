package se.osoco.api.model;

import java.util.List;

public class BalanceReport {
    private final List<Account> assets;
    private final List<Account> liabilitiesAndEquityCapital;


    public BalanceReport(se.osoco.domain.reports.BalanceSheet balanceSheet) {
        assets = balanceSheet.assets().totalAssets().accounts().stream().map(Account::new).sorted().toList();
        liabilitiesAndEquityCapital = balanceSheet.liabilitiesAndEquityCapital()
                .totalLiabilitiesAndEquityCapital().accounts().stream().map(Account::new).sorted().toList();
    }


}
