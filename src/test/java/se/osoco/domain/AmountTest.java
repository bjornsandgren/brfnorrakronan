package se.osoco.domain;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class AmountTest {

    @Test
    public void formatted() {
        Amount amount = new Amount(new BigDecimal("518057.53"));
        Assert.assertEquals("518\u00A0057,53", amount.formatted());
    }

    @Test
    public void rounded() {
        Amount amount = new Amount(new BigDecimal("518057.53"));
        Assert.assertEquals("518\u00A0058", amount.rounded());
    }

    @Test
    public void toStringTest() {
        Assert.assertEquals("518\u00A0058", new Amount(new BigDecimal("518057.53")).toString());
    }

    @Test
    public void negate() {
        Amount amount = new Amount(new BigDecimal("518057.53"));
        Assert.assertEquals(new Amount(new BigDecimal("-518057.53")), amount.negate());
    }
}