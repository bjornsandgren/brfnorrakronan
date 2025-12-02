package se.osoco.api;

import org.springframework.stereotype.Component;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Component
@Path("/ping")
@Produces({MediaType.APPLICATION_JSON})
public class PingResource {

    @GET
    @Path("/")
    public String ping() {
        return "pong";
    }
}
