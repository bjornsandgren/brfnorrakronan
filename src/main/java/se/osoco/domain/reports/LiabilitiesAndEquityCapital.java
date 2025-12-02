package se.osoco.domain.reports;

import se.osoco.domain.account.AccountGroup;
import se.osoco.domain.accounting.GeneralLedger;

public class LiabilitiesAndEquityCapital {

    private GeneralLedger ledger;

    public LiabilitiesAndEquityCapital(GeneralLedger ledger) {
        this.ledger = ledger;
    }

    public String text() {
        return "Skulder";
    }

    public AccountGroupBalance totalLiabilitiesAndEquityCapital() {
        return ledger.accountsBalance("SKULDER OCH EGET KAPITAL",
                AccountGroup.EQUITY_CAPITAL,
                AccountGroup.UNTAXED_RESERVES,
                AccountGroup.PROVISIONS,
                AccountGroup.LONG_TERM_LIABILITIES,
                AccountGroup.SHORT_TERM_LIABILITIES);
    }

    public AccountGroupBalance equityCapital() {
        return ledger.accountsBalance(AccountGroup.EQUITY_CAPITAL);
    }

    public AccountGroupBalance untaxedReserves() {
        return ledger.accountsBalance(AccountGroup.UNTAXED_RESERVES);
    }

    public AccountGroupBalance provisions() {
        return ledger.accountsBalance(AccountGroup.PROVISIONS);
    }

    public AccountGroupBalance liabilities() {
        return ledger.accountsBalance(AccountGroup.LONG_TERM_LIABILITIES);
    }

    public AccountGroupBalance longTermLiabilities() {
        return ledger.accountsBalance(AccountGroup.LONG_TERM_LIABILITIES);
    }

    public AccountGroupBalance shortTermLiabilities() {
        return ledger.accountsBalance(AccountGroup.SHORT_TERM_LIABILITIES);
    }
}