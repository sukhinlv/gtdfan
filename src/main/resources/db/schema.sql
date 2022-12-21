DROP SCHEMA public CASCADE;
CREATE SCHEMA public;

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

CREATE TABLE users
(
    id         SERIAL PRIMARY KEY,
    email      varchar        NOT NULL,
    first_name varchar        NOT NULL,
    last_name  varchar        NOT NULL,
    password   varchar        NOT NULL,
    created    timestamp      NOT NULL,
    enabled    bool                    DEFAULT true,
    user_role  varchar        NOT NULL
);
CREATE UNIQUE INDEX users_email_unique_idx ON users (email);

CREATE TABLE task
(
    id           SERIAL PRIMARY KEY,
    completed    bool      DEFAULT false,
    name         varchar   NOT NULL,
    until        timestamp,
    link         varchar,
    created      timestamp NOT NULL,
    updated      timestamp NOT NULL,
    category_id  bigint    NOT NULL,
    priority_id  bigint    NOT NULL,
    supertask_id bigint,
    user_id      bigint    NOT NULL,
    FOREIGN KEY (category_id) REFERENCES category (id),
    FOREIGN KEY (priority_id) REFERENCES priority (id),
    FOREIGN KEY (supertask_id) REFERENCES task (id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX task_id_userid_idx ON task (id, user_id);

CREATE TABLE note
(
    task_id   bigint    NOT NULL,
    note_id   bigint    NOT NULL,
    updated   timestamp NOT NULL,
    note      varchar   NOT NULL,
    PRIMARY KEY ("task_id", "note_id"),
    FOREIGN KEY (task_id) REFERENCES task (id) ON DELETE CASCADE
);
CREATE INDEX note_updated_index ON note (updated);
