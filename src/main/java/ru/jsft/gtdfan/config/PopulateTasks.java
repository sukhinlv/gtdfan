package ru.jsft.gtdfan.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.jsft.gtdfan.repository.NoteRepository;

@Configuration
public class PopulateTasks {

    @Bean
    CommandLineRunner commandLineRunner(NoteRepository repository) {
        return args -> {
            // TODO: implement some actions or delete class

        };
    }
}
