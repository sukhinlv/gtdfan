package ru.jsft.gtdfan.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;
import ru.jsft.gtdfan.model.*;
import ru.jsft.gtdfan.repository.CategoryRepository;
import ru.jsft.gtdfan.repository.PriorityRepository;
import ru.jsft.gtdfan.repository.TaskRepository;
import ru.jsft.gtdfan.repository.UserRepository;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.data.jdbc.core.mapping.AggregateReference.to;

@Configuration
public class InitializeData {

    private final UserRepository userRepository;
    private final PriorityRepository priorityRepository;
    private final CategoryRepository categoryRepository;
    private final TaskRepository taskRepository;

    public InitializeData(UserRepository userRepository,
                          PriorityRepository priorityRepository,
                          CategoryRepository categoryRepository,
                          TaskRepository taskRepository) {

        this.userRepository = userRepository;
        this.priorityRepository = priorityRepository;
        this.categoryRepository = categoryRepository;
        this.taskRepository = taskRepository;
    }

    @Bean
    @Transactional
    CommandLineRunner commandLineRunner(Clock clock) {
        return args -> {
            User userLeonid = new User("admin@ya.ru", "admin", "admin", "{noop}admin",
                    LocalDateTime.now(clock), true, Role.ADMIN);
            User userNatasha = new User("user@ya.ru", "user", "user", "{noop}user",
                    LocalDateTime.now(clock), true, Role.USER);
            userRepository.saveAll(List.of(userLeonid, userNatasha));

            Priority high = new Priority("High", 0);
            Priority middle = new Priority("Middle", 5);
            Priority low = new Priority("Low", 10);
            Priority none = new Priority("None", 1000);
            priorityRepository.saveAll(List.of(high, middle, low, none));

            Category today = new Category("1 - Today");
            Category week = new Category("2 - Week");
            categoryRepository.saveAll(List.of(today, week));

            taskRepository.saveAll(List.of(
                    Task.builder().name("Проверь обновление 1С (еженедельно)")
                            .categoryId(to(today.getId())).priorityId(to(high.getId())).userId(to(userLeonid.getId()))
                            .notes(List.of(
                                    Note.builder().updated(LocalDateTime.now(clock)).note("Note for 1C update").build(),
                                    Note.builder().updated(LocalDateTime.now(clock)).note("Another one note for update").build()))
                            .build(),
                    Task.builder().name("Subtask for 1C update")
                            .supertaskId(to(1L))
                            .categoryId(to(today.getId())).priorityId(to(none.getId())).userId(to(userLeonid.getId())).build(),
                    Task.builder().name("Орион: сбой резервного копирования!")
                            .categoryId(to(today.getId())).priorityId(to(middle.getId())).userId(to(userLeonid.getId())).build(),
                    Task.builder().name("Воронение стали")
                            .link("https://youtu.be/1GKftAi4gXo")
                            .categoryId(to(today.getId())).priorityId(to(high.getId())).userId(to(userLeonid.getId())).build(),
                    Task.builder().name("Учебное видео: Sasgis, Ozi, распечатка. Полезные ресурсы.")
                            .categoryId(to(today.getId())).priorityId(to(high.getId())).userId(to(userLeonid.getId())).build(),
                    Task.builder().name("Флешка с музыкой")
                            .until(LocalDateTime.of(2022, 11, 15, 0, 0))
                            .categoryId(to(today.getId())).priorityId(to(low.getId())).userId(to(userLeonid.getId())).build(),
                    Task.builder().name("Видео с конференции JPoint...")
                            .link("https://www.youtube.com/playlist?list=PLVe-2wcL84b8OCdXV_tqP8YrMIlgB_BER")
                            .categoryId(to(today.getId())).priorityId(to(none.getId())).userId(to(userLeonid.getId())).build(),
                    Task.builder().name("Some holdover task")
                            .categoryId(to(week.getId())).priorityId(to(middle.getId())).userId(to(userLeonid.getId())).build(),
                    Task.builder().name("Some task by Natasha")
                            .categoryId(to(today.getId())).priorityId(to(middle.getId())).userId(to(userNatasha.getId()))
                            .notes(List.of(
                                    Note.builder().updated(LocalDateTime.now(clock)).note("Note for Natasha`s task").build()))
                            .build(),
                    Task.builder().name("Some holdover task by Natasha")
                            .until(LocalDateTime.of(2021, 1, 1, 0, 0))
                            .categoryId(to(week.getId())).priorityId(to(middle.getId())).userId(to(userLeonid.getId())).build()
            ));
        };
    }
}
