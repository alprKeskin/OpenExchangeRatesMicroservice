package io.github.alprkeskin.openexchangeratesmicroservice.config;

import io.github.alprkeskin.openexchangeratesmicroservice.service.OpenExchangeRatesMainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;


@Configuration
@EnableScheduling
@ConditionalOnProperty(name = "scheduling.enabled", matchIfMissing = true)
public class SchedulingConfiguration {
    private final Logger LOGGER = LoggerFactory.getLogger(SchedulingConfiguration.class);

    @Autowired
    private OpenExchangeRatesMainService openExchangeRatesMainService;

    @Scheduled(cron = "*/10 * * * * *")
    public void getDailyRates() {
        LOGGER.info("Scheduled getDailyRates...");
        openExchangeRatesMainService.getCurrencyRates(LocalDate.now(), "TRY,EUR,USD");
    }
}
