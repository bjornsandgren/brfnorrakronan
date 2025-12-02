package se.osoco.domain.reports;

import se.osoco.domain.account.Account;
import se.osoco.domain.accounting.Daybook;
import se.osoco.domain.accounting.GeneralLedger;
import se.osoco.domain.account.AccountGroup;

import java.util.Arrays;
import java.util.stream.Stream;


public class IncomeStatement {

    private GeneralLedger generalLedger;

    public IncomeStatement(Daybook daybook) {
        this.generalLedger = daybook.ledger();
    }

    public AccountGroupBalance netSales() {
        return generalLedger.accountsBalance(AccountGroup.NET_SALES);
    }

    /*
    public AccountGroupBalance changesOfInventory() {
        return generalLedger.accountsBalance(AccountGroup.CHANGES_OF_INVENTORIES);
    }

     */

    public AccountGroupBalance activatedWork() {
        return generalLedger.accountsBalance(AccountGroup.ACTIVATED_WORK);
    }

    public AccountGroupBalance otherOperatingIncome() {
        return generalLedger.accountsBalance(AccountGroup.OTHER_OPERATING_INCOME);
    }

    public AccountGroupBalance materials() {
        return generalLedger.accountsBalance(AccountGroup.MATERIALS);
    }

    public AccountGroupBalance otherExternalExpenses() {
        return generalLedger.accountsBalance("Övriga externa kostnader", AccountGroup.OTHER_EXTERNAL_EXPENSES);
    }

    public AccountGroupBalance depreciation() {
        return generalLedger.accountsBalance(AccountGroup.DEPRECIATION);
    }

    public AccountGroupBalance otherOperatingExpenses() {
        return generalLedger.accountsBalance(AccountGroup.OTHER_OPERATING_EXPENSES);
    }

    public AccountGroupBalance personnel() {
        return generalLedger.accountsBalance(AccountGroup.PERSONNEL);
    }

    public AccountGroupBalance resultOtherCompanies() {
        return generalLedger.accountsBalance(AccountGroup.RESULT_OTHER);
    }

    public AccountGroupBalance resultFromOtherFixedAssets() {
        return generalLedger.accountsBalance(AccountGroup.RESULT_OTHER_FIXED_ASSETS);
    }

    public AccountGroupBalance financialIncome() {
        return generalLedger.accountsBalance(AccountGroup.FINANCIAL_INCOME);
    }

    public AccountGroupBalance financialExpenses() {
        return generalLedger.accountsBalance(AccountGroup.FINANCIAL_EXPENSES);
    }

    public AccountGroupBalance appropriations() {
        return generalLedger.accountsBalance(AccountGroup.APPROPRIATIONS);
    }

    public AccountGroupBalance tax() {
        return generalLedger.accountsBalance(AccountGroup.TAX);
    }

    public AccountGroupBalance totalIncome() {
        return generalLedger.accountsBalance("Summa rörelsens intäkter", AccountGroup.income());
    }

    public AccountGroupBalance total() {
        return generalLedger.accountsBalance(AccountGroup.TOTAL_REVENUE_AND_EXPENSES);
    }

    public AccountGroupBalance grossProfit() {
        return generalLedger.accountsBalance("BRUTTOVINST",
                totalIncome(),
                AccountGroup.MATERIALS);
    }

    public AccountGroupBalance totalOperatingExpenses() {
        return generalLedger.accountsBalance("Summa rörelsens kostnader", AccountGroup.operatingExpenses());
    }

    public AccountGroupBalance ebit() {
        return generalLedger.accountsBalance("Rörelseresultat",
                Stream.concat(Arrays.stream(
                        AccountGroup.income()),
                        Arrays.stream(AccountGroup.operatingExpenses()
                        )).toArray(AccountGroup[]::new));
    }

    public AccountGroupBalance resultBeforeTax() {
        return generalLedger.accountsBalance("Resultat före skatt",
                resultAfterFinancial(), appropriations());
    }

    public AccountGroupBalance yearResult() {
        return new AccountGroupBalance("Årets resultat", Arrays.asList(generalLedger.account(8999)));
    }

    /**
     * The current result. Must be same as BalanceSheet.diff(). yearResult() is deducted so as soon as the financial
     * statements is done this should be zero
     * @return
     */
    public AccountGroupBalance preliminaryResult() {
        AccountGroupBalance yearResult = yearResult();
        AccountGroupBalance result = generalLedger.accountsBalance("", resultBeforeTax(), AccountGroup.TAX);
        return generalLedger.accountsBalance("Beräknat resultat", yearResult, result);
    }

    public AccountGroupBalance totalFinancial() {
        return generalLedger.accountsBalance("Finansiella poster",
                AccountGroup.RESULT_OTHER,
                AccountGroup.RESULT_OTHER_FIXED_ASSETS,
                AccountGroup.FINANCIAL_INCOME,
                AccountGroup.FINANCIAL_EXPENSES
        );
    }

    public AccountGroupBalance resultAfterFinancial() {
        return generalLedger.accountsBalance("Resultat efter finansiella poster", ebit(), totalFinancial());
    }
}