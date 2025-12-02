package se.osoco.domain.accounting;

import se.osoco.sie.legacy.SIE;

public class Accounting {

    private Daybook daybook;

    public Accounting(SIE sie) {
        this.daybook = new Daybook(sie);
    }

    public Daybook daybook() {
        return daybook;
    }

    public GeneralLedger ledger() {
        return daybook.ledger();
    }
}
