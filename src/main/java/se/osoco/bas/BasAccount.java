package se.osoco.bas;

import se.osoco.domain.SRU;
import se.osoco.domain.account.AccountGroup;
import se.osoco.domain.account.AccountMetaData;
import se.osoco.domain.account.AccountNumber;

import java.math.BigDecimal;

public class BasAccount implements AccountMetaData {
    private final String number;
    private final String name;

    public BasAccount(int number, String text) {
        this.number = String.valueOf(number);
        this.name = text;
    }

    @Override
    public String number() {
        return number;
    }

    @Override
    public BigDecimal openingBalance() {
        return BigDecimal.ZERO;
    }

    @Override
    public BigDecimal closingBalance() {
        //use null to indicate that closing value must be calculated
        return null;
    }

    @Override
    public BigDecimal lastYearClosing() {
        return BigDecimal.ZERO;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String type() {
        return null;
    }

    @Override
    public SRU sru() {
        return null;
    }
}