package se.osoco;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static final String NAME = "IOLO!";

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}