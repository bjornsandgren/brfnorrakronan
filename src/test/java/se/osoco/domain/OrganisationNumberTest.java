package se.osoco.domain;

import org.junit.Test;

public class OrganisationNumberTest {

    @Test (expected = RuntimeException.class)
    public void handleNull() {
        new OrganisationNumber(null);
    }

    @Test (expected = RuntimeException.class)
    public void handleEmpty() {
        new OrganisationNumber("");
    }

    @Test (expected = RuntimeException.class)
    public void handleBlank() {
        new OrganisationNumber(" ");
    }

    @Test
    public void handleHyphen() {
        OrganisationNumber organisationNumber = new OrganisationNumber("556767-2752");
    }
}
