package ru.jsft.gtdfan.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import ru.jsft.gtdfan.web.util.JsonUtil;

@Configuration
public class JacksonConfig {
    @Autowired
    void configureAndStoreObjectMapper(ObjectMapper objectMapper) {
        objectMapper.registerModule(new JavaTimeModule());
        JsonUtil.setMapper(objectMapper);
    }
}
