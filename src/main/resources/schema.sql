DROP TABLE IF EXISTS note;
DROP TABLE IF EXISTS task;
DROP TABLE IF EXISTS category;
DROP TABLE IF EXISTS priority;
DROP TABLE IF EXISTS users;

CREATE TABLE task
(
    id           SERIAL PRIMARY KEY,
    completed    bool      DEFAULT false,
    name         varchar   NOT NULL,
    until        timestamp,
    link         varchar,
    created      timestamp DEFAULT now(),
    edited       timestamp NOT NULL,
    category_id  integer   NOT NULL,
    priority_id  integer   NOT NULL,
    supertask_id integer,
    user_id      integer   NOT NULL
);

CREATE TABLE category
(
    id   SERIAL PRIMARY KEY,
    name varchar UNIQUE NOT NULL
);

CREATE TABLE priority
(
    id    SERIAL PRIMARY KEY,
    name  varchar UNIQUE NOT NULL,
    level integer        NOT NULL
);

CREATE TABLE note
(
    id        SERIAL PRIMARY KEY,
    task_id   integer   NOT NULL,
    edited    timestamp NOT NULL DEFAULT now(),
    note_text varchar   NOT NULL
);

CREATE TABLE users
(
    id         SERIAL PRIMARY KEY,
    name       varchar        NOT NULL,
    email      varchar UNIQUE NOT NULL,
    password   varchar        NOT NULL,
    registered timestamp      NOT NULL DEFAULT now(),
    enabled    bool                    DEFAULT true
);

CREATE INDEX main_index ON task (user_id, completed, category_id, priority_id, name);

CREATE INDEX name ON category (name);

CREATE INDEX level_name ON priority (level, name);

CREATE INDEX edited ON note (edited);

CREATE INDEX email ON users (email);

ALTER TABLE task
    ADD FOREIGN KEY (category_id) REFERENCES category (id);

ALTER TABLE task
    ADD FOREIGN KEY (priority_id) REFERENCES priority (id);

ALTER TABLE task
    ADD FOREIGN KEY (supertask_id) REFERENCES task (id) ON DELETE CASCADE;

ALTER TABLE task
    ADD FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE;

ALTER TABLE note
    ADD FOREIGN KEY (task_id) REFERENCES task (id) ON DELETE CASCADE;
