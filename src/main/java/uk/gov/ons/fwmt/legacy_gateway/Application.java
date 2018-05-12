/*
 * Copyright.. etc
 */

package uk.gov.ons.fwmt.legacy_gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Main entry point into the Legacy Gateway
 *
 * @author Thomas Poot
 * @author James Berry
 * @author Jacob Harrison
 */

@Slf4j
@SpringBootApplication
@EntityScan("uk.gov.ons.fwmt.legacy_gateway.entity")
@EnableJpaRepositories("uk.gov.ons.fwmt.legacy_gateway.repo")
@EnableScheduling
public class Application {
    /**
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        log.debug("Started UI Application");
    }

    /**
     * @param
     * @return
     */
    @Bean
    CommandLineRunner init() {
        return (args) -> {
            //storageService.deleteAll();
            //storageService.init();
        };
    }
}
