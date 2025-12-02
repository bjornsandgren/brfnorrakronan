package se.osoco.sie;

import org.junit.Test;
import se.osoco.Application;
import se.osoco.domain.Client;
import se.osoco.domain.OrganisationNumber;
import se.osoco.domain.Verification;
import se.osoco.domain.account.AccountMetaData;
import se.osoco.domain.accounting.Daybook;
import se.osoco.domain.reports.BalanceSheet;
import se.osoco.sie.legacy.SIE;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class EdisonTest {

    @Test
    public void sie4() {
        String source = "/sie/edison.se";
        SIE sie = SIE.fromClasspathResource(source);
        Daybook daybook = new Daybook(sie);
        assertEquals(2, daybook.series().size());

        BalanceSheet balanceSheet = daybook.balanceSheet();
        assertEquals(new BigDecimal("147245.00"), balanceSheet.assets().tangibleFixedAssets().closing().value());

        Verification kontoavslut = daybook.verification("0", 1);
        assertEquals("Kontoavslut 2099 mot 2098", kontoavslut.text());
        assertEquals(1, daybook.verifications("0").size());
        List<Verification> verifications1 = daybook.verifications("1");
        assertEquals(50, verifications1.size());
        Verification last = daybook.verification("1",50);
        assertEquals(50, last.number());
        assertEquals(LocalDate.of(2024, 03, 31), last.date());
        assertEquals("Momsredovisning", last.text());
        assertEquals("2610", last.transactions().get(0).accountNumber());
        assertEquals(new BigDecimal("92720.00"), last.transactions().get(0).amount());

        AccountMetaData plusgiro = sie.chartOfAccounts().get(1920);
        assertEquals("1920", plusgiro.number());
        assertEquals(new BigDecimal("372.60"), plusgiro.openingBalance());
        assertEquals(new BigDecimal("223477.04"), plusgiro.closingBalance());
    }

    @Test
    public void client() {
        String source = "/sie/edison.se";
        SIE sie = SIE.fromClasspathResource(source);
        Client client = sie.client();
        assertEquals("Bjorn Sandgren Konsult AB", client.name());
        assertEquals(new OrganisationNumber("556767-2752"), client.organisationNumber());
    }

    @Test
    public void export() {
        SIE sie = SIE.fromClasspathResource("/sie/edison.se");
        Daybook daybook = new Daybook(sie);
        String exportedSIE = daybook.export();
        assertEquals(sie.toString(), exportedSIE.replace(Application.NAME,"\"Edison Ekonomi Start\" \"6.5\""));
    }

}