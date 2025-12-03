package se.osoco.api;

import static se.osoco.api.CurrentUserConfig.SIE_FILE;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.osoco.api.model.BalanceReport;
import se.osoco.api.model.IncomeReport;
import se.osoco.domain.accounting.Daybook;
import se.osoco.domain.reports.BalanceSheet;
import se.osoco.domain.reports.IncomeStatement;
import se.osoco.sie.legacy.SIE;

@RestController
@RequestMapping(value = "/report", produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
public class ReportResource {

    @GetMapping("/balance/")
    public BalanceReport daybook() {
        SIE importedSIE = SIE.fromClasspathResource(SIE_FILE);
        Daybook daybook = new Daybook(importedSIE);
        BalanceSheet balanceSheet =
            new BalanceSheet(daybook);;
        return new BalanceReport(balanceSheet);
    }

    @GetMapping("/income/")
    public IncomeReport income() {
        SIE importedSIE = SIE.fromClasspathResource(SIE_FILE);
        Daybook daybook = new Daybook(importedSIE);
        IncomeStatement incomeStatement = new IncomeStatement(daybook);
        return new IncomeReport(incomeStatement);
    }
}
