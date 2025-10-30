package com.pxbtdev.tradingcalculatorweb.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zalando.logbook.*;

import java.io.IOException;

@Configuration
public class CustomLogbookConfig {

    private static final Logger logger = LoggerFactory.getLogger("Logbook");

    @Bean
    public Logbook logbook() {
        return Logbook.builder()
                .sink(new SimpleBodySink())
                .build();
    }

    private static class SimpleBodySink implements Sink {

        @Override
        public boolean isActive() {
            return true;
        }

        @Override
        public void write(Precorrelation precorrelation, HttpRequest request) throws IOException {
            logger.info("{} {}", request.getMethod(), request.getRequestUri());

            if (!request.getBodyAsString().isEmpty()) {
                logger.info("Request: {}", request.getBodyAsString());
            }
        }

        @Override
        public void write(Correlation correlation, HttpRequest request, HttpResponse response) throws IOException {
            logger.info("{}", response.getStatus());

            if (!response.getBodyAsString().isEmpty()) {
                logger.info("Response: {}", response.getBodyAsString());
            }
        }
    }
}