DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS roles;

CREATE TABLE users (
    id               INT             GENERATED ALWAYS AS IDENTITY,
    email            VARCHAR(255)    UNIQUE NOT NULL,
    password         VARCHAR(255)    NOT NULL,
    first_name       VARCHAR(255)    NOT NULL,
    last_name        VARCHAR(255)    NOT NULL,
    profile_image_id VARCHAR(36)     UNIQUE,

    CONSTRAINT pk_users
        PRIMARY KEY (id)
);

CREATE TABLE roles (
    id          INT             GENERATED ALWAYS AS IDENTITY,
    type        VARCHAR(64)     UNIQUE NOT NULL,

    CONSTRAINT pk_roles
        PRIMARY KEY (id)
);

CREATE TABLE user_roles (
    user_id     INT             NOT NULL,
    role_id     INT             NOT NULL,

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
    code    VARCHAR(9)     PRIMARY KEY
);