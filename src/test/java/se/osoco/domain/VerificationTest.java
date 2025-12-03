package se.osoco.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

public class VerificationTest {

    @Test
    public void toSIEString() {
        Transaction transaction = new Transaction("1010", "Bank", BigDecimal.ZERO);
        Verification verification = new Verification(1, "A", LocalDate.of(2024, 8, 12), "text", Arrays.asList(transaction));
        Assertions.assertEquals("#VER A 1 20240812 text\n" +
                "{\n" +
                "\t#TRANS 1010 {} 0\n" +
                "}\n", verification.serialize());
    }
}
