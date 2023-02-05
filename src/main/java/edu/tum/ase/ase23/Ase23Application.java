package edu.tum.ase.ase23;

import edu.tum.ase.ase23.security.CorsConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(CorsConfig.class)
@SpringBootApplication
public class Ase23Application {

    public static void main(String[] args) {
        SpringApplication.run(Ase23Application.class, args);
    }
}