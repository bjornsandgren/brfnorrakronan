package se.osoco.domain.account;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AccountNumberTest {

    @Test(expected = NullPointerException.class)
    public void handleNull() {
        new AccountNumber(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void noNumber() {
        new AccountNumber("Not a number");
    }

    @Test(expected = IllegalArgumentException.class)
    public void toFewNumbers() {
        new AccountNumber("111");
    }

    @Test(expected = IllegalArgumentException.class)
    public void toManyNumbers() {
        new AccountNumber("11144");
    }

    @Test
    public void patent() {
        AccountNumber patent = new AccountNumber("1030");
        Type type = patent.type();
        assertEquals(1, type.number());
        assertEquals("asset", type.text());
        Type subType = patent.subType();
        assertEquals(10, subType.number());
        assertEquals("intangible assets", subType.text());
    }
}
