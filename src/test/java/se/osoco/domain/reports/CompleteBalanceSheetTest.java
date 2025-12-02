package se.osoco.domain.reports;

import org.junit.Assert;
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

/**
 * A complete test according to this: https://www.bas.se/kontoplaner/sru/
 *
 * 1. BALANSRÄKNING (Balance sheet)
 * 2. Tillgångar (Asset)
 * 3. Anläggningstillgångar (Fixed assets)
 * 4. Immateriella anläggningstillgångar (Intangible fixed assets)
 * 4. Materiella anläggningstillgångar (Tangible fixed assets)
 * 4. Finansiella anläggningstillgångar (Financial fixed assets)
 * 3. Omsättningstillgångar (Current assets)
 * 2. Eget kapital och skulder (Equity and liabilities)
 * 3. Eget kapital (Equity)
 * 3. Obeskattade reserver och avsättningar (Untaxed reserves and provisions)
 * 4. Obeskattade reserver (Untaxed reserves)
 * 4. Avsättningar (Provisions)
 * 3. Skulder (Liabilities)
 * 4. Långfristiga skulder (Long term liabilities)
 * 4. Kortfristiga skulder (Short term liabilities)
 */
public class CompleteBalanceSheetTest {

    @Test
    public void complete() {
        Daybook daybook = new Daybook(SIE.fromScratch(new Client(new OrganisationNumber("222"), "empty")));
        //add some "Immateriella anläggningstillgångar"
        daybook = daybook.add(
                new Verification(1, "1", LocalDate.now(), "text",
                        Arrays.asList(new Transaction("1088", "", BigDecimal.ONE))));
        BalanceSheet balanceSheet = daybook.balanceSheet();
        Assert.assertEquals(BigDecimal.ONE, balanceSheet.assets().intangibleFixedAssets().closing().value());

        //add some "Materiella anläggningstillgångar"
        daybook = daybook.add(
                new Verification(1, "1", LocalDate.now(), "text",
                        Arrays.asList(new Transaction("1181", "", BigDecimal.ONE))));
        balanceSheet = daybook.balanceSheet();
        Assert.assertEquals(BigDecimal.ONE, balanceSheet.assets().tangibleFixedAssets().closing().value());

        //add some "Finansiella anläggningstillgångar"
        daybook = daybook.add(
                new Verification(1, "1", LocalDate.now(), "text",
                        Arrays.asList(new Transaction("1311", "", BigDecimal.ONE))));
        balanceSheet = daybook.balanceSheet();
        Assert.assertEquals(BigDecimal.ONE, balanceSheet.assets().financialFixedAssets().closing().value());

        //verifying total fixed assets
        Assert.assertEquals(new BigDecimal("3"), balanceSheet.assets().totalFixedAssets().closing().value());

        //add some "Varulager"
        daybook = daybook.add(
                new Verification(1, "1", LocalDate.now(), "text",
                        Arrays.asList(new Transaction("1410", "", BigDecimal.ONE))));
        balanceSheet = daybook.balanceSheet();
        Assert.assertEquals(BigDecimal.ONE, balanceSheet.assets().inventory().closing().value());

        //add some "Kortfristiga fordringar"
        daybook = daybook.add(
                new Verification(1, "1", LocalDate.now(), "text",
                        Arrays.asList(new Transaction("1510", "", BigDecimal.ONE))));
        balanceSheet = daybook.balanceSheet();
        Assert.assertEquals(BigDecimal.ONE, balanceSheet.assets().receivables().closing().value());

        //add some "Kortsiktiga investeringar"
        daybook = daybook.add(
                new Verification(1, "1", LocalDate.now(), "text",
                        Arrays.asList(new Transaction("1860", "", BigDecimal.ONE))));
        balanceSheet = daybook.balanceSheet();
        Assert.assertEquals(BigDecimal.ONE, balanceSheet.assets().shortTermInvestments().closing().value());


        //add some "Kassa och bank"
        daybook = daybook.add(
                new Verification(1, "1", LocalDate.now(), "text",
                        Arrays.asList(new Transaction("1920", "", BigDecimal.ONE))));
        balanceSheet = daybook.balanceSheet();
        Assert.assertEquals(BigDecimal.ONE, balanceSheet.assets().cashAndBank().closing().value());

        //verifying total current assets
        Assert.assertEquals(new BigDecimal("4"), balanceSheet.assets().totalCurrentAssets().closing().value());


        //verifying total assets
        Assert.assertEquals(new BigDecimal("7"), balanceSheet.assets().totalAssets().closing().value());

        //add som "Eget kapital"
        daybook = daybook.add(
                new Verification(1, "1", LocalDate.now(), "text",
                        Arrays.asList(new Transaction("2080", "", BigDecimal.ONE))));
        balanceSheet = daybook.balanceSheet();
        Assert.assertEquals(BigDecimal.ONE, balanceSheet.liabilitiesAndEquityCapital().equityCapital().closing().value());

        //verifying total equity capital
        Assert.assertEquals(new BigDecimal("1"), balanceSheet.liabilitiesAndEquityCapital().equityCapital().closing().value());

        //add som "Obeskattade reserver"
        daybook = daybook.add(
                new Verification(1, "1", LocalDate.now(), "text",
                        Arrays.asList(new Transaction("2110", "", BigDecimal.ONE))));
        balanceSheet = daybook.balanceSheet();
        Assert.assertEquals(BigDecimal.ONE, balanceSheet.liabilitiesAndEquityCapital().untaxedReserves().closing().value());

        //add som "Avsättningar"
        daybook = daybook.add(
                new Verification(1, "1", LocalDate.now(), "text",
                        Arrays.asList(new Transaction("2210", "", BigDecimal.ONE))));
        balanceSheet = daybook.balanceSheet();
        Assert.assertEquals(BigDecimal.ONE, balanceSheet.liabilitiesAndEquityCapital().provisions().closing().value());

        //add som "Långfristiga skulder"
        daybook = daybook.add(
                new Verification(1, "1", LocalDate.now(), "text",
                        Arrays.asList(new Transaction("2330", "", BigDecimal.ONE))));
        balanceSheet = daybook.balanceSheet();
        Assert.assertEquals(BigDecimal.ONE, balanceSheet.liabilitiesAndEquityCapital().longTermLiabilities().closing().value());

        //add som "Kortsiktiga skulder"
        daybook = daybook.add(
                new Verification(1, "1", LocalDate.now(), "text",
                        Arrays.asList(new Transaction("2480", "", BigDecimal.ONE))));
        balanceSheet = daybook.balanceSheet();
        Assert.assertEquals(BigDecimal.ONE, balanceSheet.liabilitiesAndEquityCapital().shortTermLiabilities().closing().value());

        //verifying total liabilities
        Assert.assertEquals(new BigDecimal("2"), balanceSheet.assets().totalLiabilities().closing().value());

        //verifying total liabilities and equity
        Assert.assertEquals(new BigDecimal("5"), balanceSheet.liabilitiesAndEquityCapital().totalLiabilitiesAndEquityCapital().closing().value());
    }
}