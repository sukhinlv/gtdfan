package ru.jsft.gtdfan.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing;
import ru.jsft.gtdfan.web.util.JsonUtil;

import java.sql.SQLException;
import java.time.Clock;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Configuration
@EnableJdbcAuditing
@Slf4j
class ApplicationConfig {
    @Autowired
    void configureAndStoreObjectMapper(ObjectMapper objectMapper) {
        JsonUtil.setMapper(objectMapper);
    }

    @Bean
    @Profile("!test")
    public Clock clock() {
        return Clock.systemDefaultZone();
    }

    @Bean
    @Profile("test")
    public Clock testClock() {
        ZonedDateTime now = ZonedDateTime.of(
                2022, 11, 15, 11, 0, 0, 0,
                ZoneId.of("GMT"));

        return Clock.fixed(now.toInstant(), now.getZone());
    }

    @Profile("!test")
    @Bean(initMethod = "start", destroyMethod = "stop")
    Server h2Server() throws SQLException {
        log.info("Start H2 TCP server");
        return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092");
    }
}
