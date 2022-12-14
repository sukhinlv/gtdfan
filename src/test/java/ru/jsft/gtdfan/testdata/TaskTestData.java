package ru.jsft.gtdfan.testdata;

import ru.jsft.gtdfan.utils.MatcherFactory;
import ru.jsft.gtdfan.web.controller.dto.CategoryDto;
import ru.jsft.gtdfan.web.controller.dto.NoteDto;
import ru.jsft.gtdfan.web.controller.dto.PriorityDto;
import ru.jsft.gtdfan.web.controller.dto.TaskDto;

import java.time.LocalDateTime;
import java.util.List;

import static ru.jsft.gtdfan.testdata.CommonTestData.FIXED_DATE_TIME;
import static ru.jsft.gtdfan.testdata.UserTestData.ADMIN_DTO;
import static ru.jsft.gtdfan.testdata.UserTestData.USER_DTO;

public class TaskTestData {
    public static MatcherFactory.Matcher<TaskDto> TASK_DTO_MATCHER =
            MatcherFactory.usingIgnoringFieldsComparator(TaskDto.class);

    public static PriorityDto HIGH = new PriorityDto(1L, "High", 0);
    public static PriorityDto MIDDLE = new PriorityDto(2L, "Middle", 5);
    public static PriorityDto LOW = new PriorityDto(3L, "Low", 10);
    public static PriorityDto NONE = new PriorityDto(4L, "None", 1000);

    public static CategoryDto TODAY = new CategoryDto(1L, "1 - Today");
    public static CategoryDto WEEK = new CategoryDto(2L, "2 - Week");

    public static TaskDto TASK_DTO_1 = new TaskDto(1L, false, "Проверь обновление 1С (еженедельно)", null, null,
            FIXED_DATE_TIME, FIXED_DATE_TIME, TODAY, HIGH, null, ADMIN_DTO.getId(), List.of(
            new NoteDto(FIXED_DATE_TIME, "Note for 1C update"),
            new NoteDto(FIXED_DATE_TIME, "Another one note for update")));
    public static TaskDto TASK_DTO_2 = new TaskDto(2L, false, "Subtask for 1C update", null, null,
            FIXED_DATE_TIME, FIXED_DATE_TIME, TODAY, NONE, TASK_DTO_1.getId(), ADMIN_DTO.getId(), List.of());
    public static TaskDto TASK_DTO_3 = new TaskDto(3L, false, "Орион: сбой резервного копирования!", null, null,
            FIXED_DATE_TIME, FIXED_DATE_TIME, TODAY, MIDDLE, null, ADMIN_DTO.getId(), List.of());
    public static TaskDto TASK_DTO_4 = new TaskDto(4L, false, "Воронение стали", null, "https://youtu.be/1GKftAi4gXo",
            FIXED_DATE_TIME, FIXED_DATE_TIME, TODAY, HIGH, null, ADMIN_DTO.getId(), List.of());
    public static TaskDto TASK_DTO_5 = new TaskDto(5L, false, "Учебное видео: Sasgis, Ozi, распечатка. Полезные ресурсы.", null, null,
            FIXED_DATE_TIME, FIXED_DATE_TIME, TODAY, HIGH, null, ADMIN_DTO.getId(), List.of());
    public static TaskDto TASK_DTO_6 = new TaskDto(6L, false, "Флешка с музыкой", LocalDateTime.of(2022, 11, 25, 0, 0), null,
            FIXED_DATE_TIME, FIXED_DATE_TIME, TODAY, LOW, null, ADMIN_DTO.getId(), List.of());
    public static TaskDto TASK_DTO_7 = new TaskDto(7L, false, "Видео с конференции JPoint...", null, "https://www.youtube.com/playlist?list=PLVe-2wcL84b8OCdXV_tqP8YrMIlgB_BER",
            FIXED_DATE_TIME, FIXED_DATE_TIME, TODAY, NONE, null, ADMIN_DTO.getId(), List.of());
    public static TaskDto TASK_DTO_8 = new TaskDto(8L, false, "Some holdover task", null, null,
            FIXED_DATE_TIME, FIXED_DATE_TIME, WEEK, MIDDLE, null, ADMIN_DTO.getId(), List.of());
    public static TaskDto TASK_DTO_9 = new TaskDto(9L, false, "Some task by User", null, null,
            FIXED_DATE_TIME, FIXED_DATE_TIME, TODAY, MIDDLE, null, USER_DTO.getId(), List.of(
            new NoteDto(FIXED_DATE_TIME, "Note for User`s task")));
    public static TaskDto TASK_DTO_10 = new TaskDto(10L, false, "Some holdover task by User", null, null,
            FIXED_DATE_TIME, FIXED_DATE_TIME, WEEK, MIDDLE, null, USER_DTO.getId(), List.of());

    public static List<TaskDto> ADMIN_TASKS = List.of(TASK_DTO_1, TASK_DTO_2, TASK_DTO_3, TASK_DTO_4, TASK_DTO_5,
            TASK_DTO_6, TASK_DTO_7, TASK_DTO_8);
    public static List<TaskDto> ALL_TASKS = List.of(TASK_DTO_1, TASK_DTO_2, TASK_DTO_3, TASK_DTO_4, TASK_DTO_5,
            TASK_DTO_6, TASK_DTO_7, TASK_DTO_8, TASK_DTO_9, TASK_DTO_10);
    public static List<TaskDto> USER_TASKS = List.of(TASK_DTO_9, TASK_DTO_10);

    public static TaskDto getNewTaskDto() {
        return new TaskDto(null, false, "Some test task",
                LocalDateTime.of(2022, 12, 28, 12, 30),
                "https://www.yandex.ru", FIXED_DATE_TIME, FIXED_DATE_TIME,
                TODAY, MIDDLE, null, USER_DTO.getId(), List.of());
    }

    public static TaskDto getUpdatedTaskDto() {
        TaskDto updated = getNewTaskDto();
        updated.setId(TASK_DTO_10.getId());
        return updated;
    }
}
