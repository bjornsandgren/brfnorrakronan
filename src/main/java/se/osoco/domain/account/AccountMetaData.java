package se.osoco.domain.account;

import se.osoco.domain.SRU;

import java.math.BigDecimal;

public interface AccountMetaData {

    String number();

    BigDecimal openingBalance();

    BigDecimal closingBalance();

    BigDecimal lastYearClosing();

    String name();

    String type();

    SRU sru();
}