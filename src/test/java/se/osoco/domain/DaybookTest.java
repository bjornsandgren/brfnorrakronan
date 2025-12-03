package se.osoco.domain;

import org.junit.jupiter.api.Test;
import org.springframework.util.ResourceUtils;
import se.osoco.domain.account.Account;
import se.osoco.domain.accounting.Daybook;
import se.osoco.domain.accounting.GeneralLedger;
import se.osoco.sie.legacy.SIE;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DaybookTest {

    @Test
    public void edisonSIE4() {
        String source = "/sie/edison.se";
        SIE importedSIE = SIE.fromClasspathResource(source);
        Daybook daybook = new Daybook(importedSIE);
        List<Verification> verifications = daybook.verifications("1");
        assertEquals(50, verifications.size());

        Verification first = daybook.verification("1", 1);
        assertEquals(1, first.number());
        assertEquals(LocalDate.of(2024,1,4), first.date());
        assertEquals("Inbetalning kundfordran", first.text());

        Verification last = daybook.verification("1", 50);
        assertEquals(50, last.number());
        assertEquals(LocalDate.of(2024,3,31), last.date());
        assertEquals("Momsredovisning", last.text());
    }

    @Test
    public void ledger() {
        String source = "/sie/edison.se";
        SIE importedSIE = SIE.fromClasspathResource(source);
        Daybook daybook = new Daybook(importedSIE);

        GeneralLedger ledger = daybook.ledger();
        assertEquals(7, ledger.account(1511).transactions().size());

        assertEquals(1, ledger.account(2099).transactions().size());

        Account aktiefonder = ledger.account(1960);
        assertEquals(new BigDecimal("245000.00"), aktiefonder.openingBalance().value());
        assertEquals(3, aktiefonder.transactions().size());
        assertEquals(new BigDecimal("276500.00"), aktiefonder.closingBalance().value());

        Account skattefordran = ledger.account(1640);
        assertEquals(BigDecimal.ZERO, skattefordran.openingBalance().value());
    }

    @Test
    public void toSameSIE() {
        String source = "/sie/edison.se";
        SIE importedSIE = SIE.fromClasspathResource(source);
        Daybook daybook = new Daybook(importedSIE);

        String export = daybook.export();
        assertEquals(importedSIE.serialize(), export);
    }

    @Test
    public void toSIEWithAddedVerification() {
        String source = "/sie/edison.se";
        SIE importedSIE = SIE.fromClasspathResource(source);
        Daybook origin = new Daybook(importedSIE);
        assertEquals(50, origin.verifications("1").size());
        Daybook addedOne = origin.add(new Verification(1, "1", LocalDate.now(), "text", null));
        assertEquals(51, addedOne.verifications("1").size());
        Daybook addedOneMore = addedOne.add(new Verification(2, "1", LocalDate.now(), "text", null));
        assertEquals(52, addedOneMore.verifications("1").size());
    }

    @Test
    public void newDaybook() {
        SIE sie = SIE.fromScratch(new Client(new OrganisationNumber("556767-2752"), "bsab"));
        Daybook daybook = new Daybook(sie);
        List<String> serieIds = daybook.series();
        assertEquals(1, serieIds.size()); //the default serie should always exist
        assertEquals("1", daybook.series().get(0)); //default serie
        assertEquals(0, daybook.verifications(daybook.series().get(0)).size()); //default series has 0 verifications

        Verification latestVerification = new Verification(1, "1", LocalDate.now(), "test", new ArrayList<>());
        daybook = daybook.add(latestVerification);
        assertEquals(1, daybook.series().size());
        assertEquals(1, daybook.verifications("1").size());
        assertEquals(latestVerification, daybook.verification("1", 1));

        latestVerification = new Verification(2, "1", LocalDate.now(), "test", new ArrayList<>());
        daybook = daybook.add(latestVerification);
        assertEquals(1, daybook.series().size());
        assertEquals(2, daybook.verifications("1").size());
        assertEquals(latestVerification, daybook.verification("1", 2));

    }

    @Test
    public void newBookTransactions() {
        SIE sie = SIE.fromScratch(new Client(new OrganisationNumber("556767-2752"), "bsab"));
        Daybook daybook = new Daybook(sie);
        Transaction debit = new Transaction("1010", "Utvecklingsutgifter", new BigDecimal("-40"));
        Transaction credit = new Transaction("2820", "Kortfristiga skulder till anställda", new BigDecimal("40"));
        Verification latestVerification = new Verification(1, "1", LocalDate.now(), "test", Arrays.asList(debit, credit));
        daybook = daybook.add(latestVerification);
        assertEquals(latestVerification, daybook.verification("1", 1));
        GeneralLedger generalLedger = daybook.ledger();
        Account kassaBank = generalLedger.account(1010);
        assertEquals("0", kassaBank.openingBalance().toString());
        assertEquals(new Amount(new BigDecimal("-40")), kassaBank.closingBalance());
        Account egetUttag = generalLedger.account(2820);
        assertEquals("0", egetUttag.openingBalance().toString());
        assertEquals("40", egetUttag.closingBalance().toString());
    }

    @Test
    public void export() throws IOException {
        SIE importedSIE = SIE.fromClasspathResource("/sie/edison.se");
        Daybook daybook = new Daybook(importedSIE);
        String exportedSIE = daybook.export();

        File file = ResourceUtils.getFile("classpath:sie/edison.se");
        String sieAsString = Files.readString(file.toPath(), Charset.forName("Cp437"));
        assertEquals(sieAsString.replace("\"Edison Ekonomi Start\" \"6.5\"", "IOLO!"), exportedSIE);
    }
}