package se.osoco.api;

import static se.osoco.api.CurrentUserConfig.SIE_FILE;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import se.osoco.domain.account.AccountMetaData;
import se.osoco.sie.legacy.SIE;
import se.osoco.sie.legacy.SIELegacyAccountChart;

import java.util.List;

@RestController
@RequestMapping(value = "/accounts", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountChartResource {


    @GetMapping("/")
    public List<AccountMetaData> accounts() {
        SIE importedSIE = SIE.fromClasspathResource(SIE_FILE);
        SIELegacyAccountChart sieLegacyAccountChart = new SIELegacyAccountChart(importedSIE);
        return sieLegacyAccountChart.asSortedList();
    }
}