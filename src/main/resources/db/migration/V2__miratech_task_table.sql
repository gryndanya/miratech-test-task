create table miratech_task
(
    id          BIGSERIAL    not null
        constraint miratech_task_pk
            primary key,
    title       VARCHAR(255) not null,
    description VARCHAR(255) not null,
    status      task_status  not null
);