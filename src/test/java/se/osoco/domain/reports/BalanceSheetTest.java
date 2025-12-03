package se.osoco.domain.reports;

import se.osoco.domain.Client;
import se.osoco.domain.OrganisationNumber;
import se.osoco.domain.Transaction;
import se.osoco.domain.Verification;
import se.osoco.domain.accounting.Daybook;
import se.osoco.sie.legacy.SIE;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class BalanceSheetTest {

    @Test
    public void edison() {
        String source = "/sie/edison.se";
        SIE importedSIE = SIE.fromClasspathResource(source);
        Daybook daybook = new Daybook(importedSIE);
        BalanceSheet balanceSheet = new BalanceSheet(daybook);

        AccountGroupBalance tangibleFixedAssets = balanceSheet.assets().tangibleFixedAssets();
        assertEquals(2, tangibleFixedAssets.size());
        assertEquals(new BigDecimal("147245.00"), tangibleFixedAssets.opening().value());
        assertEquals(BigDecimal.ZERO, tangibleFixedAssets.period().value());
        assertEquals(new BigDecimal("147245.00"), tangibleFixedAssets.closing().value());

        AccountGroupBalance fixedAssets = balanceSheet.assets().totalFixedAssets();
        assertEquals(2, fixedAssets.size());
        assertEquals(new BigDecimal("147245.00"), fixedAssets.opening().value());
        assertEquals(BigDecimal.ZERO, fixedAssets.period().value());
        assertEquals(new BigDecimal("147245.00"), fixedAssets.closing().value());

        AccountGroupBalance receivables = balanceSheet.assets().receivables();
        assertEquals(3, receivables.size());
        assertEquals(new BigDecimal("461467.99"), receivables.opening().value());
        assertEquals(new BigDecimal("-188663.00"), receivables.period().value());
        assertEquals(new BigDecimal("272804.99"), receivables.closing().value());

        AccountGroupBalance cashAndBank = balanceSheet.assets().cashAndBank();
        assertEquals(6, cashAndBank.size());
        assertEquals(new BigDecimal("3397703.72"), cashAndBank.opening().value());
        assertEquals(new BigDecimal("254604.44"), cashAndBank.period().value());
        assertEquals(new BigDecimal("3652308.16"), cashAndBank.closing().value());

        AccountGroupBalance currentAssets = balanceSheet.assets().totalCurrentAssets();
        assertEquals(9, currentAssets.size());
        assertEquals(new BigDecimal("3859171.71"), currentAssets.opening().value());
        assertEquals(new BigDecimal("65941.44"), currentAssets.period().value());
        assertEquals(new BigDecimal("3925113.15"), currentAssets.closing().value());

        LiabilitiesAndEquityCapital liabilitiesAndEquityCapital = balanceSheet.liabilitiesAndEquityCapital();
        AccountGroupBalance equityCapital = liabilitiesAndEquityCapital.equityCapital();
        assertEquals(3, equityCapital.size());
        AccountGroupBalance untaxedReserves = liabilitiesAndEquityCapital.untaxedReserves();
        assertEquals(3, untaxedReserves.size());

        IncomeStatement incomeStatement = new IncomeStatement(daybook);
        AccountGroupBalance tax = incomeStatement.tax();
        assertEquals(1, tax.size());
    }

    @Test
    public void fromScratch() {
        Daybook daybook = new Daybook(SIE.fromScratch(new Client(new OrganisationNumber("222"), "name")));
        BalanceSheet balanceSheet = daybook.balanceSheet();
        assertEquals(BigDecimal.ZERO, balanceSheet.assets().totalAssets().closing().value());
        assertEquals(BigDecimal.ZERO, balanceSheet.liabilitiesAndEquityCapital().totalLiabilitiesAndEquityCapital().closing().value());

        daybook = daybook.add(new Verification(1, "1", LocalDate.now(), "text",
                Arrays.asList(new Transaction("1920", null, BigDecimal.ONE), new Transaction("2010", null, BigDecimal.ONE))));
        balanceSheet = daybook.balanceSheet();
        assertEquals(BigDecimal.ONE, balanceSheet.assets().totalAssets().closing().value());
        assertEquals(BigDecimal.ONE, balanceSheet.liabilitiesAndEquityCapital().totalLiabilitiesAndEquityCapital().closing().value());
    }
}