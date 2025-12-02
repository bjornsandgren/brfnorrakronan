package se.osoco.domain.reports;

import se.osoco.domain.account.AccountGroup;
import se.osoco.domain.accounting.GeneralLedger;

public class Assets {

    private GeneralLedger ledger;

    public Assets(GeneralLedger ledger) {
        this.ledger = ledger;
    }

    public String text() {
        return "TILLGÅNGAR";
    }

    public AccountGroupBalance intangibleFixedAssets() {
        return ledger.accountsBalance(AccountGroup.INTANGIBLE_FIXED_ASSETS);
    }

    public AccountGroupBalance tangibleFixedAssets() {
        return ledger.accountsBalance(AccountGroup.TANGIBLE_FIXED_ASSETS);
    }

    public AccountGroupBalance financialFixedAssets() {
        return ledger.accountsBalance(AccountGroup.FINANCIAL_FIXED_ASSETS);
    }

    public AccountGroupBalance inventory() {
        return ledger.accountsBalance(AccountGroup.INVENTORY);
    }

    public AccountGroupBalance receivables() {
        return ledger.accountsBalance(AccountGroup.RECEIVABLES);
    }

    public AccountGroupBalance shortTermInvestments() {
        return ledger.accountsBalance(AccountGroup.SHORT_TERM_INVESTMENTS);
    }

    public AccountGroupBalance cashAndBank() {
        return ledger.accountsBalance(AccountGroup.CASH_AND_BANK);
    }

    public AccountGroupBalance totalFixedAssets() {
        return ledger.accountsBalance("Summa anläggningstillgångar",
                AccountGroup.fixedAssets());
    }

    public AccountGroupBalance totalCurrentAssets() {
        return ledger.accountsBalance("Summa omsättningstillgångar",
                AccountGroup.currentAssets());
    }

    public AccountGroupBalance totalAssets() {
        return ledger.accountsBalance(text(), AccountGroup.assets());
    }

    public AccountGroupBalance totalLiabilities() {
        return ledger.accountsBalance(text(), AccountGroup.liabilities());
    }
}