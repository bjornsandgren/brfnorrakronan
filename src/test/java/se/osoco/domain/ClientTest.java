package se.osoco.domain;

import org.junit.Assert;
import org.junit.Test;
import se.osoco.sie.legacy.SIE;

public class ClientTest {

    @Test
    public void asLegacySIE() {
        Client client = new Client(new OrganisationNumber("556767-2752"), "name");
        Assert.assertEquals("#FNAMN \"name\"" + SIE.NEW_LINE + "#ORGNR 556767-2752" + SIE.NEW_LINE, client.toLegacySIE());
    }
}
