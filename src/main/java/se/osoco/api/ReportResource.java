package se.osoco.api;

import static se.osoco.api.CurrentUserConfig.SIE_FILE;

import org.springframework.stereotype.Component;


import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import se.osoco.api.model.BalanceReport;
import se.osoco.api.model.IncomeReport;
import se.osoco.domain.accounting.Daybook;
import se.osoco.domain.reports.BalanceSheet;
import se.osoco.domain.reports.IncomeStatement;
import se.osoco.sie.legacy.SIE;

@Component
@Path("/report")
@Produces({MediaType.APPLICATION_JSON})
public class ReportResource {

    @GET
    @Path("/balance")
    public BalanceReport daybook() {
        SIE importedSIE = SIE.fromClasspathResource(SIE_FILE);
        Daybook daybook = new Daybook(importedSIE);
        BalanceSheet balanceSheet =
            new BalanceSheet(daybook);;
        return new BalanceReport(balanceSheet);
    }

    @GET
    @Path("/income")
    public IncomeReport income() {
        SIE importedSIE = SIE.fromClasspathResource(SIE_FILE);
        Daybook daybook = new Daybook(importedSIE);
        IncomeStatement incomeStatement = new IncomeStatement(daybook);
        return new IncomeReport(incomeStatement);
    }
}
