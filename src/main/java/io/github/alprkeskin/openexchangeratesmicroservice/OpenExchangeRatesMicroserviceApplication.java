package io.github.alprkeskin.openexchangeratesmicroservice;

import io.github.alprkeskin.openexchangeratesmicroservice.service.OpenExchangeRatesMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

@SpringBootApplication
@EnableScheduling
public class OpenExchangeRatesMicroserviceApplication {
    public static void main(String[] args) {
        SpringApplication.run(OpenExchangeRatesMicroserviceApplication.class, args);
    }


    private final Logger logger = Logger.getLogger("OpenExchangeRatesMicroserviceApplication.class");

    @Autowired
    private OpenExchangeRatesMainService openExchangeRatesMainService;

    /**
    *: every
    second minute hour day_of_month month day_of_week
     */
    @Scheduled(cron = "*/10 * * * * *")
    public void getDailyRates() {
        logger.log(Level.INFO, "Scheduled getDailyRates...");
        openExchangeRatesMainService.getResponse(LocalDate.now(), "TRY,EUR,USD");
    }
}


@Configuration
@EnableScheduling
@ConditionalOnProperty(name = "scheduling.enabled", matchIfMissing = true)
class SchedulingConfiguration {
}