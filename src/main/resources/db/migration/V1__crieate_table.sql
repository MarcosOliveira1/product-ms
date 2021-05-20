create table product
(
    id          bigint auto_increment
        primary key,
    description varchar(1000) not null,
    name        varchar(255) not null,
    price       double not null
);
