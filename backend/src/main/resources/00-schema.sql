DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS access_codes;
DROP TABLE IF EXISTS resources;
DROP TABLE IF EXISTS aircraft;
DROP TABLE IF EXISTS flight;
DROP TABLE IF EXISTS notes;
DROP TABLE IF EXISTS action_items;
DROP TABLE IF EXISTS notes_resources;

CREATE TABLE users (
    id               BIGINT          GENERATED ALWAYS AS IDENTITY,
    email            VARCHAR(255)    UNIQUE NOT NULL,
    password         VARCHAR(255)    NOT NULL,
    first_name       VARCHAR(255)    NOT NULL,
    last_name        VARCHAR(255)    NOT NULL,
    profile_image_id VARCHAR(36)     UNIQUE,

    CONSTRAINT pk_users
        PRIMARY KEY (id)
);

CREATE TABLE roles (
    id          BIGINT          GENERATED ALWAYS AS IDENTITY,
    type        VARCHAR(64)     UNIQUE NOT NULL,

    CONSTRAINT pk_roles
        PRIMARY KEY (id)
);

CREATE TABLE user_roles (
    user_id     BIGINT             NOT NULL,
    role_id     BIGINT             NOT NULL,

    CONSTRAINT fk_user_roles_user_id
        FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_user_roles_role_id
        FOREIGN KEY (role_id)
        REFERENCES roles(id)
        ON DELETE CASCADE,

    CONSTRAINT pk_user_roles
        PRIMARY KEY (user_id, role_id)
);

CREATE TABLE access_codes (
    code    VARCHAR(9),

    CONSTRAINT pk_code
        PRIMARY KEY (code)
);

CREATE TABLE resources (
    id              BIGINT    GENERATED ALWAYS AS IDENTITY,
    storage_key     BIGINT,

    CONSTRAINT pk_resources
       PRIMARY KEY (id)
);

CREATE TABLE aircraft (
    id            BIGINT        GENERATED ALWAYS AS IDENTITY,
    tail_number   VARCHAR(6)    UNIQUE,

    CONSTRAINT pk_aircraft_id
      PRIMARY KEY (id)
);

CREATE TABLE flights (
    id                      BIGINT          GENERATED ALWAYS AS IDENTITY,
    date                    DATE            NOT NULL,
    aircraft_id             BIGINT      NOT NULL,
    duration                DECIMAL(3, 1)   NOT NULL,
    cross_country           BOOLEAN         NOT NULL,
    night_flight            BOOLEAN         NOT NULL,
    objective               TEXT,
    things_done_well        TEXT,
    things_to_improve       TEXT,
    next_flight_id          BIGINT,
    student_id              BIGINT             NOT NULL,
    instructor_id           BIGINT             NOT NULL,
    feedback                TEXT,

    CONSTRAINT pk_flights
        PRIMARY KEY (id),

    CONSTRAINT fk_flights_aircraft_id
        FOREIGN KEY (aircraft_id)
        REFERENCES aircraft(id),

    CONSTRAINT fk_flights_next_flight_id
        FOREIGN KEY (next_flight_id)
        REFERENCES flights(id),

    CONSTRAINT fk_instructor_id
        FOREIGN KEY (instructor_id)
        REFERENCES users(id),

    CONSTRAINT fk_student_id
        FOREIGN KEY (student_id)
        REFERENCES users(id)
);

CREATE TABLE notes (
    id      BIGINT    GENERATED ALWAYS AS IDENTITY,
    content TEXT,

    CONSTRAINT pk_notes_id
       PRIMARY KEY (id)
);

CREATE TABLE action_items (
    id          BIGINT    GENERATED ALWAYS AS IDENTITY,
    action      TEXT,
    flight_id   BIGINT    NOT NULL,
    notes_id    BIGINT,

    CONSTRAINT pk_action_items_id
        PRIMARY KEY (id),

    CONSTRAINT fk_action_items_flight_id
        FOREIGN KEY (flight_id)
        REFERENCES flights(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_action_items_notes_id
        FOREIGN KEY (notes_id)
        REFERENCES notes(id)
);

CREATE TABLE notes_resources (
    resource_id     BIGINT,
    notes_id        BIGINT,

    CONSTRAINT pk_notes_resources
        PRIMARY KEY (resource_id, notes_id),

    CONSTRAINT fk_notes_resources_resource_id
        FOREIGN KEY (resource_id)
        REFERENCES resources(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_notes_resources_note_id
        FOREIGN KEY (notes_id)
        REFERENCES notes(id)
        ON DELETE CASCADE
);