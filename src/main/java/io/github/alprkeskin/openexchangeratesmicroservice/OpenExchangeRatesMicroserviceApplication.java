package io.github.alprkeskin.openexchangeratesmicroservice;

import io.github.alprkeskin.openexchangeratesmicroservice.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;

@SpringBootApplication
@EnableScheduling
public class OpenExchangeRatesMicroserviceApplication {
    public static void main(String[] args) {
        SpringApplication.run(OpenExchangeRatesMicroserviceApplication.class, args);
    }

//    // OpenExchangeRatesService::getResponse()'u static mi yapsak?
//    @Autowired
//    private MainService mainService;
//
//    // *: every
//    // second minute hour day_of_month month day_of_week
//    @Scheduled(cron = "*/10 * * * * *")
//    public void getDailyRates() {
//        System.out.println("Scheduled getDailyRates...");
//        mainService.getResponse("latest.json", "TRY,EUR,USD", LocalDate.now());
//    }
}


//// Bunu ve getDailyRates'i ayrı bir class'ta define etme şansımız var mı?
//@Configuration
//@EnableScheduling
//@ConditionalOnProperty(name = "scheduling.enabled", matchIfMissing = true)
//class SchedulingConfiguration {
//}