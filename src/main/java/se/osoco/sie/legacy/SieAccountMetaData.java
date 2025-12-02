package se.osoco.sie.legacy;

import se.osoco.domain.SRU;
import se.osoco.domain.account.AccountMetaData;

import java.math.BigDecimal;

class SieAccountMetaData implements AccountMetaData {
    private final String number;
    private final String name;
    private final String accountType;
    private final SRU sru;
    private final BigDecimal openingBalance;
    private final BigDecimal closingBalance;
    private final BigDecimal lastYearClosing;

    public SieAccountMetaData(Konto konto) {
        this.number = konto.number;
        this.name = konto.name;
        this.accountType = konto.ktyp != null ? konto.ktyp.type : null;
        this.sru = konto.sru != null ? new SRU(konto.sru.value) : null;
        if (konto.isRes()) {
            this.closingBalance = konto.res != null ? new BigDecimal(konto.res.balance) : BigDecimal.ZERO;
            this.lastYearClosing = konto.resLastYear != null ? new BigDecimal(konto.resLastYear.balance) : BigDecimal.ZERO;
            this.openingBalance = BigDecimal.ZERO;
        } else {
            this.openingBalance = konto.ib != null ? new BigDecimal(konto.ib.balance) : BigDecimal.ZERO;
            this.closingBalance = konto.ub != null ? new BigDecimal(konto.ub.balance) : null; //use null when not set, must be calculated instead
            this.lastYearClosing = openingBalance;
        }
    }

    public String number() {
        return number;
    }

    public String name() {
        return name;
    }

    public String type() {
        return accountType;
    }

    public SRU sru() {
        return sru;
    }

    public BigDecimal openingBalance() {
        return openingBalance;
    }

    public BigDecimal closingBalance() {
        return closingBalance;
    }

    public BigDecimal lastYearClosing() {
        return lastYearClosing;
    }
}