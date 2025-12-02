package se.osoco.domain.reports;

import se.osoco.domain.accounting.Daybook;
import se.osoco.domain.accounting.GeneralLedger;

public class BalanceSheet {

    private GeneralLedger ledger;

    public BalanceSheet(Daybook daybook) {
        ledger = daybook.ledger();
    }

    public Assets assets() {
        return new Assets(ledger);
    }

    public LiabilitiesAndEquityCapital liabilitiesAndEquityCapital() {
        return new LiabilitiesAndEquityCapital(ledger);
    }

    public AccountGroupBalance diff() {
        return ledger.accountsBalance("Ännu ej bokfört resultat",
                assets().totalAssets(),
                liabilitiesAndEquityCapital().totalLiabilitiesAndEquityCapital());
    }
}
