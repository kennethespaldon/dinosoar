INSERT INTO users (email, password, first_name, last_name)
VALUES (
        'example@email.com',
        '{bcrypt}$2a$12$1Pnrw98/VPR.gbu3Xr87Qe5TV1r2P.Y341UMJM4Y8rAv8wulCqFVa',
        'John',
        'Doe'
);

INSERT INTO roles (type) VALUES ('ADMIN');
INSERT INTO roles (type) VALUES ('INSTRUCTOR');
INSERT INTO roles (type) VALUES ('STUDENT');

INSERT INTO user_roles (user_id, role_id) VALUES (1, 3);