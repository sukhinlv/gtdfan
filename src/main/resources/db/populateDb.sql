DELETE FROM note;
DELETE FROM task;
DELETE FROM category;
DELETE FROM priority;
DELETE FROM users;

INSERT INTO users(id, name, email, password)
VALUES (1, 'Leonid', 'leva1981@yandex.ru', 'admin'),
       (2, 'Natasha', 'natasuhina@yandex.ru', 'user');

INSERT INTO priority(id, name, level)
VALUES (1, 'High', 0),
       (2, 'Middle', 5),
       (3, 'Low', 10),
       (4, 'none', 1000);

INSERT INTO category(id, name)
VALUES (1, '1 - Today'),
       (2, '2 - Week');

INSERT INTO task(id, name, until, link, edited, category_id, priority_id, supertask_id, user_id)
VALUES (1, 'Проверь обновление 1С (еженедельно)', null, null, now(), 1, 1, null, 1),
       (2, 'Орион: сбой резервного копирования!', null, null, now(), 1, 1, null, 1),
       (3, 'Воронение стали', null, 'https://youtu.be/1GKftAi4gXo', now(), 1, 2, null, 1),
       (4, 'Учебное видео: Sasgis, Ozi, распечатка. Полезные ресурсы.', null, null, now(), 1, 2, null, 1),
       (5, 'Флешка с музыкой', '2022-11-15', null, now(), 1, 3, null, 1),
       (6, 'Видео с конференции JPoint...', null, 'https://www.youtube.com/playlist?list=PLVe-2wcL84b8OCdXV_tqP8YrMIlgB_BER', now(), 1, 4, null, 1),
       (7, 'Some holdover task', '2023-06-01', null, now(), 2, 2, null, 1),
       (8, 'Subtask for 1C update', null, null, now(), 1, 4, 1, 1),
       (9, 'Some task by Natasha', null, null, now(), 1, 2, null, 2),
       (10, 'Some holdover task by Natasha', '2024-01-01', null, now(), 2, 2, null, 2);

INSERT INTO note(id, task_id, note_text)
VALUES (1, 1, 'Note for 1C update'),
       (2, 2, 'Another one note for update ')