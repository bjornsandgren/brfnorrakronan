package se.osoco.api;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import static se.osoco.api.CurrentUserConfig.SIE_FILE;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import se.osoco.domain.accounting.Daybook;
import se.osoco.domain.Verification;
import se.osoco.sie.legacy.SIE;

import java.util.List;

@Component
@Path("/daybook")
@Produces({APPLICATION_JSON})
public class DaybookResource {

    @GET
    @Path("/")
    public List<Verification> daybook(MultipartFile multipartFile) {
        SIE importedSIE = SIE.fromClasspathResource(SIE_FILE);
        return new Daybook(importedSIE).allVerifications();
    }

    @GET
    @Path("/series/")
    @Produces(APPLICATION_JSON)
    public List<String> series() {
        SIE importedSIE = SIE.fromClasspathResource(SIE_FILE);
        Daybook daybook = new Daybook(importedSIE);
        return daybook.series();
    }

    @GET
    @Path("/series/{seriesId}/")
    @Produces(APPLICATION_JSON)
    public List<Verification> verifications(@PathParam("seriesId") final String seriesId) {
        SIE importedSIE = SIE.fromClasspathResource(SIE_FILE);
        Daybook daybook = new Daybook(importedSIE);
        return daybook.verifications(seriesId);
    }

    @GET
    @Path("/series/{seriesId}/verification/{verificationId}/")
    public Verification verification(
            @PathParam("seriesId") final String seriesId,
            @PathParam("verificationId") final String verificationId) {
        SIE importedSIE = SIE.fromClasspathResource(SIE_FILE);
        Daybook daybook = new Daybook(importedSIE);
        return daybook.verification(seriesId, Integer.parseInt(verificationId));
    }

    @GET
    @Path("/export")
    public String export() {
        SIE importedSIE = SIE.fromClasspathResource(SIE_FILE);
        Daybook daybook = new Daybook(importedSIE);
        return daybook.export();
    }
}
