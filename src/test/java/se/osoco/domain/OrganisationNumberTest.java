package se.osoco.domain;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class OrganisationNumberTest {

    @Test
    public void handleNull() {
        Assertions.assertThrows(RuntimeException.class, () -> new OrganisationNumber(null));
    }

    @Test
    public void handleEmpty() {
        Assertions.assertThrows(RuntimeException.class, () -> new OrganisationNumber(""));
    }

    @Test
    public void handleBlank() {
        Assertions.assertThrows(RuntimeException.class, () -> new OrganisationNumber(" "));
    }

    @Test
    public void handleHyphen() {
        new OrganisationNumber("556767-2752");
    }
}
