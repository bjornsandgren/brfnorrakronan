package se.osoco.bas;

import org.junit.Assert;
import org.junit.Test;
import se.osoco.domain.account.AccountMetaData;
import se.osoco.domain.accounting.AccountChart;

public class Bas24Test {

    @Test
    public void base24() throws Exception {
        Bas24 bas24 = Bas24.readFile("/bas/Kontoplan-BAS-2024.xlsx");
        AccountChart accountChart = bas24.accountChart();
        Assert.assertEquals(1222, accountChart.size());
        AccountMetaData account = accountChart.get(1010);
        Assert.assertEquals("Utvecklingsutgifter", account.name());
    }
}
