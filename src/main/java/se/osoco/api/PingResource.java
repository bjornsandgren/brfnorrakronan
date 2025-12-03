package se.osoco.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/ping", produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
public class PingResource {

    @GetMapping("/")
    public String ping() {
        return "pong";
    }
}
