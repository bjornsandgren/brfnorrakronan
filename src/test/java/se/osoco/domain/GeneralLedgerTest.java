package se.osoco.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import se.osoco.domain.account.Account;
import se.osoco.domain.accounting.Daybook;
import se.osoco.domain.accounting.GeneralLedger;
import se.osoco.sie.legacy.SIE;

import java.math.BigDecimal;

public class GeneralLedgerTest {

    @Test
    public void taxAccount() {
        String source = "/sie/edison.se";
        SIE importedSIE = SIE.fromClasspathResource(source);
        Daybook daybook = new Daybook(importedSIE);
        GeneralLedger generalLedger = daybook.ledger();
        Account account = generalLedger.account(8910);
        Assertions.assertEquals(8910, account.number());
        Assertions.assertEquals(new BigDecimal("148846.00"), account.lastYear().value());
    }
}