package se.osoco.config;

import java.io.IOException;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.Response;

public class CORSResponseFilter implements ContainerResponseFilter {
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
            throws IOException {

        String allowedOrigin = "http://localhost:5173";

        if (requestContext.getMethod().equalsIgnoreCase("OPTIONS")) {
            responseContext.getHeaders().add("Access-Control-Allow-Origin", allowedOrigin);
            responseContext.getHeaders().add("Vary", "Origin");
            responseContext.getHeaders().add("Access-Control-Allow-Credentials", "true");
            responseContext.getHeaders().add("Access-Control-Request-Method", "GET");
            responseContext.getHeaders().add("Access-Control-Allow-Headers", "*");
        } else {
            responseContext.getHeaders().add("Access-Control-Allow-Origin", allowedOrigin);
            responseContext.getHeaders().add("Access-Control-Allow-Headers",
                    "CSRF-Token, X-Requested-By, Authorization, Content-Type");
            responseContext.getHeaders().add("Access-Control-Allow-Methods", "GET");

        }

    }


    protected void preflight(String origin, ContainerRequestContext requestContext) throws IOException
    {

        Response.ResponseBuilder builder = Response.ok();


    }
}