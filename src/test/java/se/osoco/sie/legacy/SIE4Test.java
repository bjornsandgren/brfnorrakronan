package se.osoco.sie.legacy;

import org.junit.Test;
import se.osoco.domain.*;
import se.osoco.domain.account.Account;
import se.osoco.domain.account.AccountMetaData;
import se.osoco.domain.accounting.AccountChart;
import se.osoco.domain.accounting.Daybook;
import se.osoco.domain.reports.AccountGroupBalance;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SIE4Test {

    @Test
    public void valid() {
        SIE sie = SIE.fromClasspathResource("/sie/sie2.se");
        assertEquals("2", sie.sietyp());
        assertEquals("20080630", sie.omfattn());
        // assertEquals("", sie2.psaldo());
        //assertEquals("", sie2.pbudget());
    }

    @Test
    public void accounts() {
        SIE sie = SIE.fromClasspathResource("/sie/typ4.se");
        AccountChart accountChart = sie.chartOfAccounts();

        assertNotNull(accountChart);
        assertEquals(6, accountChart.size());

        AccountMetaData a1060 = accountChart.get(1060);
        assertEquals("1060", a1060.number());
        assertEquals("Hyresratt", a1060.name());
        assertEquals("T", a1060.type());
        assertEquals("7201", a1060.sru().value());
        assertEquals(new BigDecimal("421457.53"), a1060.openingBalance());

        AccountMetaData a1069 = accountChart.get(1069);
        assertEquals("1069", a1069.number());
        assertEquals("Ack avskrivn hyresratt", a1069.name());
        assertEquals("T", a1069.type());
        assertEquals("7201", a1069.sru().value());

        AccountMetaData a2081 = accountChart.get(2081);
        assertEquals("2081", a2081.number());
        assertEquals("Aktiekapital", a2081.name());
        assertEquals("S", a2081.type());
        assertEquals("7301", a2081.sru().value());
        assertEquals(new BigDecimal("-200000.00"), a2081.openingBalance());
        assertEquals(new BigDecimal("-200000.00"), a2081.closingBalance());

        AccountMetaData a3020 = accountChart.get(3020);
        assertEquals("3020", a3020.number());
        assertEquals("Forsaljning VMB varor", a3020.name());
        assertEquals("I", a3020.type());
        assertEquals("7410", a3020.sru().value());
        assertEquals(new BigDecimal("-1690380.20"), a3020.closingBalance());

        AccountMetaData a4031 = accountChart.get(4031);
        assertEquals("4031", a4031.number());
        assertEquals("Inkop vissa varor i Sverige 25%", a4031.name());
        assertEquals("K", a4031.type());
        assertEquals("7512", a4031.sru().value());
    }

    @Test
    public void verifications() {
        SIE sie = SIE.fromClasspathResource("/sie/typ4.se");
        Daybook daybook = new Daybook(sie);
        assertEquals(2, daybook.verifications("A").size());

        Verification verification = daybook.verification("A",1);
        assertEquals(1, verification.number());
        assertEquals("A", verification.serie());
        assertEquals("2021-01-05", verification.date().toString());
        assertEquals("Kaffebrod", verification.text());
        assertEquals(3, verification.transactions().size());

        verification = daybook.verification("A",2);
        assertEquals(2, verification.number());
        assertEquals("A", verification.serie());
        assertEquals("2021-01-05", verification.date().toString());
        assertEquals("Kaffebrod", verification.text());
        assertEquals(4, verification.transactions().size());

        assertEquals("1060", verification.transactions().get(0).accountNumber());

    }

    @Test
    public void previousResult() {
        Post konto = new Post("#KONTO 3000 \"Försäljning 25% sv\"");
        Post ktyp = new Post("#KTYP  3000   K");
        Post currentRes = new Post("#RES 0 3000 -1690380.20");
        Post previousRes = new Post("#RES -1 3000 -33.90");

        SIE sie = new SIE(Arrays.asList(konto, ktyp, currentRes, previousRes));
        Daybook daybook = new Daybook(sie);
        Account account = daybook.ledger().account(3000);
        assertEquals(new BigDecimal("-1690380.20"), account.closingBalance().value());
    }

    @Test
    public void tax() {
        SIE sie = SIE.fromClasspathResource("/sie/typ4.se");
        Daybook daybook = new Daybook(sie);
        AccountGroupBalance accountGroupBalance = daybook.incomeStatement().tax();
        assertEquals(1, accountGroupBalance.accounts().size());
        assertEquals(new BigDecimal("148846.00"), accountGroupBalance.lastYear().value());
    }
}