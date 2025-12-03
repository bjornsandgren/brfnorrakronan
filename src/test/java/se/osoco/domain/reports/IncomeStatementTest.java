package se.osoco.domain.reports;

import org.junit.jupiter.api.Test;

import se.osoco.domain.Client;
import se.osoco.domain.OrganisationNumber;
import se.osoco.domain.Transaction;
import se.osoco.domain.Verification;
import se.osoco.domain.accounting.Daybook;
import se.osoco.sie.legacy.SIE;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

public class IncomeStatementTest {

    @Test
    public void result() {
        Daybook daybook = new Daybook(SIE.fromScratch(new Client(new OrganisationNumber("222"), "empty")));
        daybook = daybook.add(new Verification(1, "1", LocalDate.now(), "test",
                Arrays.asList(new Transaction("3200", "Konto", new BigDecimal("100")))));
        daybook = daybook.add(new Verification(3, "1", LocalDate.now(), "test",
                Arrays.asList(new Transaction("4400", "Konto", new BigDecimal("-10")))));
    }
}