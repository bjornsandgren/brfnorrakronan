package se.osoco.domain.accounting;

import org.junit.Assert;
import org.junit.Test;
import se.osoco.domain.account.Account;
import se.osoco.sie.legacy.SIE;

import java.math.BigDecimal;
import java.util.List;

public class AccountingTest {

    @Test
    public void importSIE4() {
        SIE sie = SIE.fromClasspathResource("/sie/SIE4 Exempelfil.SE");
        Accounting accounting = new Accounting(sie);
        Daybook daybook = accounting.daybook();

      //  Assert.assertEquals();
    }

    @Test
    public void ib() {
        SIE sie = SIE.fromClasspathResource("/sie/ib.se");
        Daybook daybook = new Daybook(sie);
        Account account = daybook.ledger().account(1221);
        Assert.assertEquals("Inventarier", account.text());
        Assert.assertEquals(new BigDecimal("421457.53"), account.openingBalance().value());
        Assert.assertEquals(new BigDecimal("518057.53"), account.closingBalance().value());
    }

    @Test
    public void format() {
        SIE sie = SIE.fromClasspathResource("/sie/ib.se");
        Daybook daybook = new Daybook(sie);
        Account account = daybook.ledger().account(1221);
        Assert.assertEquals(new BigDecimal("518057.53"), account.closingBalance().value());
        Assert.assertEquals("518\u00A0057,53", account.closingBalance().formatted());
    }

    @Test
    public void fiscalYear() {
        SIE sie = SIE.fromClasspathResource("/sie/SIE4 Exempelfil.SE");
        Accounting accounting = new Accounting(sie);
        Daybook daybook = accounting.daybook();
        FiscalYear fiscalYear = daybook.fiscalYear();
        Assert.assertEquals("2021-01-01", fiscalYear.start());
        Assert.assertEquals("2021-12-31", fiscalYear.end());
    }

    @Test
    public void verifications() {
        SIE sie = SIE.fromClasspathResource("/sie/edison.se");
        Accounting accounting = new Accounting(sie);
        Daybook daybook = accounting.daybook();
        Assert.assertEquals(51, daybook.allVerifications().size());
        Assert.assertEquals(2, daybook.series().size());
    }

        /*
        @Test
    public void ib() {
        List<Post> posts = new ArrayList<>();
        posts.add(new Post("#KONTO 1221 Inventarier"));
        posts.add(new Post("#IB 0 1221 421457.53"));


        Konto konto = new Konto();
        assertEquals("1221", konto.number);
        assertEquals("Inventarier", konto.name);

        Balance balance = new Balance();
        konto.ib = balance
    }
     */
}