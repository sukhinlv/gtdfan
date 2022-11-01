package ru.jsft.gtdfan.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.jsft.gtdfan.note.NoteRepository;

@Configuration
public class PopulateNotes {

    @Bean
    CommandLineRunner commandLineRunner(NoteRepository repository) {
        return args -> {
//            repository.save(new Note(LocalDateTime.now(), "Sample note"));
//            repository.save(new Note(LocalDateTime.now(), "Another sample note"));
        };
    }
}
