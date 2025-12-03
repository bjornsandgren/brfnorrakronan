package se.osoco.bas;

import org.junit.jupiter.api.Test;

import se.osoco.domain.account.AccountMetaData;
import se.osoco.domain.accounting.AccountChart;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Bas24Test {

    @Test
    public void base24() throws Exception {
        Bas24 bas24 = Bas24.readFile("/bas/Kontoplan-BAS-2024.xlsx");
        AccountChart accountChart = bas24.accountChart();
        assertEquals(1222, accountChart.size());
        AccountMetaData account = accountChart.get(1010);
        assertEquals("Utvecklingsutgifter", account.name());
    }
}
