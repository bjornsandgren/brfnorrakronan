package se.osoco.domain.accounting;

import se.osoco.domain.Client;
import se.osoco.domain.OrganisationNumber;
import se.osoco.domain.Verification;
import se.osoco.sie.legacy.SIE;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DaybookTest {

    @Test
    public void sortByDate() {
        Daybook daybook = new Daybook(SIE.fromScratch(new Client(new OrganisationNumber("5567676-2752"), "test")));
        daybook = daybook.add(new Verification(1, "serie1", LocalDate.now(), "text", new ArrayList<>()));
        daybook = daybook.add(new Verification(2, "serie2", LocalDate.now().minusDays(1), "text", new ArrayList<>()));
        assertEquals(2, daybook.size());
        assertEquals(2, daybook.sortedByDate().get(0).number());

    }
}