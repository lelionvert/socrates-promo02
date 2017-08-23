package fr.socrates;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableCaching
@EnableJpaRepositories(basePackages = "fr.socrates.infra.storage.repositories")
public class SocratesApplication {
    public static void main(String[] args) {
        SpringApplication.run(SocratesApplication.class, args);
    }
}