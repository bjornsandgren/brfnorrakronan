package se.osoco;

import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;
import se.osoco.api.AccountChartResource;
import se.osoco.api.DaybookResource;
import se.osoco.api.PingResource;
import se.osoco.api.ReportResource;

@Component
@ApplicationPath("/api")
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(DaybookResource.class);
        register(AccountChartResource.class);
        register(ReportResource.class);
        register(PingResource.class);
    }

}