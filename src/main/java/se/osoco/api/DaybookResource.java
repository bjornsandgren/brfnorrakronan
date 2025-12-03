package se.osoco.api;

import static se.osoco.api.CurrentUserConfig.SIE_FILE;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import se.osoco.domain.accounting.Daybook;
import se.osoco.domain.Verification;
import se.osoco.sie.legacy.SIE;

import java.util.List;

@RestController
@RequestMapping(value = "/daybook", produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
public class DaybookResource {

    @GetMapping("/")
    public List<Verification> daybook(MultipartFile multipartFile) {
        SIE importedSIE = SIE.fromClasspathResource(SIE_FILE);
        return new Daybook(importedSIE).allVerifications();
    }

    @GetMapping("/series/")
    public List<String> series() {
        SIE importedSIE = SIE.fromClasspathResource(SIE_FILE);
        Daybook daybook = new Daybook(importedSIE);
        return daybook.series();
    }

    @GetMapping("/series/{seriesId}/")
    public List<Verification> verifications(@PathVariable("seriesId") final String seriesId) {
        SIE importedSIE = SIE.fromClasspathResource(SIE_FILE);
        Daybook daybook = new Daybook(importedSIE);
        return daybook.verifications(seriesId);
    }

    @GetMapping("/series/{seriesId}/verification/{verificationId}/")
    public Verification verification(
            @PathVariable("seriesId") final String seriesId,
            @PathVariable("verificationId") final String verificationId) {
        SIE importedSIE = SIE.fromClasspathResource(SIE_FILE);
        Daybook daybook = new Daybook(importedSIE);
        return daybook.verification(seriesId, Integer.parseInt(verificationId));
    }

    @GetMapping("/export")
    public String export() {
        SIE importedSIE = SIE.fromClasspathResource(SIE_FILE);
        Daybook daybook = new Daybook(importedSIE);
        return daybook.export();
    }
}
