package se.osoco.api;

import static se.osoco.api.CurrentUserConfig.SIE_FILE;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.springframework.stereotype.Component;
import se.osoco.domain.account.AccountMetaData;
import se.osoco.sie.legacy.SIE;
import se.osoco.sie.legacy.SIELegacyAccountChart;

import java.util.List;

@Component
@Path("/accounts")
@Produces({MediaType.APPLICATION_JSON})
public class AccountChartResource {


    @GET
    @Path("/")
    public List<AccountMetaData> accounts() {
        SIE importedSIE = SIE.fromClasspathResource(SIE_FILE);
        SIELegacyAccountChart sieLegacyAccountChart = new SIELegacyAccountChart(importedSIE);
        return sieLegacyAccountChart.asSortedList();
    }
}