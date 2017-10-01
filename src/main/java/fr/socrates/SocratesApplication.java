package fr.socrates;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
public class SocratesApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(SocratesApplication.class, args);
    }
}
