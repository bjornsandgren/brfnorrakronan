package se.osoco.domain.reports;

import org.junit.Test;
import se.osoco.domain.Client;
import se.osoco.domain.OrganisationNumber;
import se.osoco.domain.Transaction;
import se.osoco.domain.Verification;
import se.osoco.domain.accounting.Daybook;
import se.osoco.sie.legacy.SIE;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * A complete test according to this: https://www.bas.se/kontoplaner/sru/
 *
 * 1. RESULTATRÄKNING (Income statement)
 */
public class CompleteIncomeStatementTest {

    @Test
    public void complete() {
        Daybook daybook = new Daybook(SIE.fromScratch(new Client(new OrganisationNumber("222"), "empty")));
        daybook = daybook.add(new Verification(1, "1", LocalDate.now(), "test",
                Arrays.asList(new Transaction("3200", "Konto", new BigDecimal("100")))));

        assertEquals(new BigDecimal("100"), daybook.incomeStatement().netSales().closing().value());

        daybook = daybook.add(new Verification(2, "1", LocalDate.now(), "test",
                Arrays.asList(new Transaction("3800", "Konto", new BigDecimal("100")))));
        assertEquals(new BigDecimal("100"), daybook.incomeStatement().activatedWork().closing().value());

        daybook = daybook.add(new Verification(3, "1", LocalDate.now(), "test",
                Arrays.asList(new Transaction("3910", "Konto", new BigDecimal("100")))));
        assertEquals(new BigDecimal("100"), daybook.incomeStatement().otherOperatingIncome().closing().value());

        assertEquals(new BigDecimal("300"), daybook.incomeStatement().totalIncome().closing().value());

        daybook = daybook.add(new Verification(3, "1", LocalDate.now(), "test",
                Arrays.asList(new Transaction("4400", "Konto", new BigDecimal("-100")))));
        assertEquals(new BigDecimal("-100"), daybook.incomeStatement().materials().closing().value());

        assertEquals(new BigDecimal("200"), daybook.incomeStatement().grossProfit().closing().value());

        daybook = daybook.add(new Verification(3, "1", LocalDate.now(), "test",
                Arrays.asList(new Transaction("5500", "Konto", new BigDecimal("-100")))));
        assertEquals(new BigDecimal("-100"), daybook.incomeStatement().otherExternalExpenses().closing().value());

        daybook = daybook.add(new Verification(3, "1", LocalDate.now(), "test",
                Arrays.asList(new Transaction("7200", "Konto", new BigDecimal("-100")))));
        assertEquals(new BigDecimal("-100"), daybook.incomeStatement().personnel().closing().value());

        daybook = daybook.add(new Verification(3, "1", LocalDate.now(), "test",
                Arrays.asList(new Transaction("7710", "Konto", new BigDecimal("-100")))));
        assertEquals(new BigDecimal("-100"), daybook.incomeStatement().depreciation().closing().value());

        daybook = daybook.add(new Verification(3, "1", LocalDate.now(), "test",
                Arrays.asList(new Transaction("7940", "Konto", new BigDecimal("-100")))));
        assertEquals(new BigDecimal("-100"), daybook.incomeStatement().otherOperatingExpenses().closing().value());

        assertEquals(new BigDecimal("-500"), daybook.incomeStatement().totalOperatingExpenses().closing().value());

        assertEquals(new BigDecimal("-200"), daybook.incomeStatement().ebit().closing().value());

        daybook = daybook.add(new Verification(3, "1", LocalDate.now(), "test",
                Arrays.asList(new Transaction("8187", "Konto", new BigDecimal("-100")))));
        assertEquals(new BigDecimal("-100"), daybook.incomeStatement().resultOtherCompanies().closing().value());

        daybook = daybook.add(new Verification(3, "1", LocalDate.now(), "test",
                Arrays.asList(new Transaction("8210", "Konto", new BigDecimal("-100")))));
        assertEquals(new BigDecimal("-100"), daybook.incomeStatement().resultFromOtherFixedAssets().closing().value());

        daybook = daybook.add(new Verification(3, "1", LocalDate.now(), "test",
                Arrays.asList(new Transaction("8310", "Konto", new BigDecimal("-100")))));
        assertEquals(new BigDecimal("-100"), daybook.incomeStatement().financialIncome().closing().value());

        daybook = daybook.add(new Verification(3, "1", LocalDate.now(), "test",
                Arrays.asList(new Transaction("8420", "Konto", new BigDecimal("-100")))));
        assertEquals(new BigDecimal("-100"), daybook.incomeStatement().financialExpenses().closing().value());

        assertEquals(new BigDecimal("-400"), daybook.incomeStatement().totalFinancial().closing().value());

        assertEquals(new BigDecimal("-600"), daybook.incomeStatement().resultAfterFinancial().closing().value());

        daybook = daybook.add(new Verification(3, "1", LocalDate.now(), "test",
                Arrays.asList(new Transaction("8860", "Konto", new BigDecimal("-100")))));
        assertEquals(new BigDecimal("-100"), daybook.incomeStatement().appropriations().closing().value());

        assertEquals(new BigDecimal("-700"), daybook.incomeStatement().resultBeforeTax().closing().value());

        daybook = daybook.add(new Verification(3, "1", LocalDate.now(), "test",
                Arrays.asList(new Transaction("8910", "Konto", new BigDecimal("-100")))));
        assertEquals(new BigDecimal("-100"), daybook.incomeStatement().tax().closing().value());

        assertEquals(new BigDecimal("-800"), daybook.incomeStatement().preliminaryResult().closing().value());
        assertEquals("Beräknat resultat", daybook.incomeStatement().preliminaryResult().text());
    }

    @Test
    public void tax() {
        Daybook daybook = new Daybook(SIE.fromScratch(new Client(new OrganisationNumber("222"), "empty")));
        daybook = daybook.add(new Verification(1, "1", LocalDate.now(), "test",
                Arrays.asList(new Transaction("8910", "Konto", new BigDecimal("100")))));
        assertEquals(new BigDecimal("100"), daybook.incomeStatement().tax().closing().value());
    }

    @Test
    public void result() {
        Daybook daybook = new Daybook(SIE.fromScratch(new Client(new OrganisationNumber("222"), "empty")));
        daybook = daybook.add(new Verification(1, "1", LocalDate.now(), "test",
                Arrays.asList(new Transaction("3200", "Konto", new BigDecimal("100")))));
        daybook = daybook.add(new Verification(3, "1", LocalDate.now(), "test",
                Arrays.asList(new Transaction("4400", "Konto", new BigDecimal("-10")))));

        //prel result
        assertEquals(new BigDecimal("90"), daybook.incomeStatement().preliminaryResult().closing().value());

        //prel result after tax
        daybook = daybook.add(new Verification(1, "1", LocalDate.now(), "test",
                Arrays.asList(new Transaction("8910", "Konto", new BigDecimal("-10")))));
        assertEquals(new BigDecimal("-10"), daybook.incomeStatement().tax().closing().value());
        assertEquals(new BigDecimal("80"), daybook.incomeStatement().preliminaryResult().closing().value());

        //final result not yet stated
        assertEquals(new BigDecimal("0"), daybook.incomeStatement().yearResult().closing().value());

        //final result
        daybook = daybook.add(new Verification(1, "1", LocalDate.now(), "test",
                Arrays.asList(new Transaction("8999", "Konto", new BigDecimal("-80")))));
        assertEquals(new BigDecimal("-10"), daybook.incomeStatement().tax().closing().value());
        assertEquals(new BigDecimal("-80"), daybook.incomeStatement().yearResult().closing().value());
        assertEquals(new BigDecimal("0"), daybook.incomeStatement().preliminaryResult().closing().value());
    }
}