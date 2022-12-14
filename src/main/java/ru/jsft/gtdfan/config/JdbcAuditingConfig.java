package ru.jsft.gtdfan.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Optional;

@Configuration
// https://stackoverflow.com/a/65109489/20265936
@EnableJdbcAuditing(dateTimeProviderRef = "customAuditingDateTimeProvider")
class JdbcAuditingConfig {

    @Bean(name = "customAuditingDateTimeProvider")
    public DateTimeProvider dateTimeProvider(Clock clock) {
        return () -> Optional.of(LocalDateTime.now(clock));
    }
}

