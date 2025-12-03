package se.osoco.sie;

import se.osoco.Application;
import se.osoco.domain.accounting.Daybook;
import se.osoco.domain.accounting.GeneralLedger;
import se.osoco.domain.reports.BalanceSheet;
import se.osoco.domain.reports.IncomeStatement;
import se.osoco.sie.legacy.SIE;

import java.io.IOException;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ExampleSIETest {

    //TODO: add opening balance
    @Test
    public void sie4() throws IOException {
        String source = "/sie/SIE4 Exempelfil.SE";
        SIE sie = SIE.fromClasspathResource(source);
        assertEquals(3490, sie.size());

        Daybook daybook = new Daybook(sie);
        assertEquals(59, daybook.verifications("A").size());
        assertEquals(88, daybook.verifications("B").size());

        //Result post sums
        IncomeStatement incomeStatement = daybook.incomeStatement();
        assertEquals(new BigDecimal("-5778873.41"), incomeStatement.totalIncome().closing().value());
        assertEquals(new BigDecimal("-5782818.36"), incomeStatement.netSales().closing().value());
        assertEquals(BigDecimal.ZERO, incomeStatement.activatedWork().closing().value());
        assertEquals(new BigDecimal("3944.95"), incomeStatement.otherOperatingIncome().closing().value());
        assertEquals(new BigDecimal("2466533.74"), incomeStatement.materials().closing().value());
        assertEquals(new BigDecimal("466117.90"), incomeStatement.otherExternalExpenses().closing().value());
        assertEquals(new BigDecimal("-1072856.22"), incomeStatement.ebit().closing().value());
        assertEquals(new BigDecimal("-1074344.11"), incomeStatement.resultAfterFinancial().closing().value());
        assertEquals(new BigDecimal("-1074344.11"), incomeStatement.preliminaryResult().closing().value());
        assertEquals(new BigDecimal("-1074344.11"), incomeStatement.preliminaryResult().closing().value());

        //Income statement accounts
        GeneralLedger generalLedger = daybook.ledger();
        assertEquals(BigDecimal.ZERO, generalLedger.account(3030).closingBalance().value());
        assertEquals(new BigDecimal("-512299.95"), generalLedger.account(3045).closingBalance().value());
        assertEquals(new BigDecimal("-446450.00"), generalLedger.account(3048).closingBalance().value());
        assertEquals(new BigDecimal("-92500.95"), generalLedger.account(3055).closingBalance().value());
        assertEquals(new BigDecimal("-42350.00"), generalLedger.account(3058).closingBalance().value());
        assertEquals(new BigDecimal("-20.00"), generalLedger.account(3540).closingBalance().value());
        assertEquals(new BigDecimal("-20.00"), generalLedger.account(3541).closingBalance().value());
        assertEquals(new BigDecimal("-20.00"), generalLedger.account(3542).closingBalance().value());
        assertEquals(new BigDecimal("-7224.00"), generalLedger.account(3590).closingBalance().value());
        assertEquals(new BigDecimal("-5689.00"), generalLedger.account(3592).closingBalance().value());
        assertEquals(new BigDecimal("-5.06"), generalLedger.account(3740).closingBalance().value());
        assertEquals(new BigDecimal("3944.95"), generalLedger.account(3960).closingBalance().value());

        assertEquals(new BigDecimal("2391104.75"), generalLedger.account(4010).closingBalance().value());
        assertEquals(new BigDecimal("151216.50"), generalLedger.account(4056).closingBalance().value());
        assertEquals(new BigDecimal("-75787.51"), generalLedger.account(4990).closingBalance().value());

        //TODO add more accounts

        //Balance post sums
        BalanceSheet balanceSheet = daybook.balanceSheet();
        assertEquals(new BigDecimal("151303.03"), balanceSheet.assets().tangibleFixedAssets().closing().value());
    }

    @Test
    public void export() {
        SIE importedSIE = SIE.fromClasspathResource("/sie/SIE4 Exempelfil.SE");
        Daybook daybook = new Daybook(importedSIE);
        String exportedSIE = daybook.export();
        assertEquals(importedSIE.toString(), exportedSIE.replace(Application.NAME, "\"Visma Administration 2000 med Visma Integration\" 2022.2"));
    }
}