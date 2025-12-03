package se.osoco.domain.account;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AccountNumberTest {

    @Test
    public void handleNull() {
        Assertions.assertThrows(NullPointerException.class, () -> new AccountNumber(null));
    }

    @Test
    public void noNumber() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new AccountNumber("Not a number"));
    }

    @Test
    public void toFewNumbers() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new AccountNumber("111"));
    }

    @Test
    public void toManyNumbers() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new AccountNumber("11144"));
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
