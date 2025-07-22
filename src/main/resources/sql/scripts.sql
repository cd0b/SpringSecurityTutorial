create table users(
    username varchar(50) not null primary key,
    password varchar(500) not null,
    enabled boolean not null
);

create table authorities(
    username varchar(50) not null,
    authority varchar(50) not null,
    constraint fk_authorities_users foreign key(username) references users(username)
);

create unique index ix_auth_username on authorities (username,authority);

INSERT INTO users VALUES ('user', '{noop}EazyBytes@12345', '1');
INSERT INTO authorities VALUES ('user', 'read');
INSERT INTO users VALUES ('admin', '{bcrypt}$2a$12$88.f6upbBvy0okEa7OfHFuorV29qeK.sVbB9VQ6J6dWM1bW6Qef8m', '1');
INSERT INTO authorities VALUES ('admin', 'admin');

create table customers(
    id serial primary key,
    username varchar(50) not null,
    password varchar(200) not null,
    role varchar(50) not null
);

INSERT INTO customers(email, password, role)
VALUES ('happy@example.com', '{noop}EazyBytes@12345', 'read');
INSERT INTO customers(email, password, role)
VALUES ('admin@example.com', '{bcrypt}$2a$12$88.f6upbBvy0okEa7OfHFuorV29qeK.sVbB9VQ6J6dWM1bW6Qef8m', 'admin');