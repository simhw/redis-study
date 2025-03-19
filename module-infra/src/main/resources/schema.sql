drop table if exists `movie`;
create table movie
(
    movie_id   bigint unsigned not null auto_increment,
    title      varchar(255)    not null,
    thumbnail  varchar(255),
    runtime    int,
    genre      varchar(255)    not null,
    rating     varchar(255)    not null,
    release_at datetime(6),
    created_at datetime(6),
    updated_at datetime(6),
    deleted_at datetime(6),
    created_ay bigint unsigned not null,
    updated_by bigint unsigned not null,
    primary key (movie_id)
);

drop table if exists `screening`;
create table screening
(
    screening_id bigint unsigned not null auto_increment,
    movie_id     bigint          not null,
    start_at     datetime(6),
    end_at       datetime(6),
    theater_id   bigint          not null,
    created_at   datetime(6),
    updated_at   datetime(6),
    deleted_at   datetime(6),
    created_ay   bigint unsigned not null,
    updated_by   bigint unsigned not null,
    primary key (screening_id)
);

drop table if exists `theater`;
create table theater
(
    theater_id bigint unsigned not null auto_increment,
    name       varchar(255),
    created_at datetime(6),
    updated_at datetime(6),
    deleted_at datetime(6),
    created_ay bigint unsigned not null,
    updated_by bigint unsigned not null,
    primary key (theater_id)
);

drop table if exists `seat`;
create table seat
(
    seat_id    bigint unsigned not null auto_increment,
    number     varchar(255),
    theater_id bigint          not null,
    created_at datetime(6),
    updated_at datetime(6),
    deleted_at datetime(6),
    created_ay bigint unsigned not null,
    updated_by bigint unsigned not null,
    primary key (seat_id)
);

drop table if exists `reservation`;
create table reservation
(
    reservation_id bigint unsigned not null auto_increment,
    user_id        bigint          not null,
    screening_id   bigint          not null,
    seat_id        bigint          not null,
    created_at     datetime(6),
    updated_at     datetime(6),
    deleted_at     datetime(6),
    created_ay     bigint unsigned not null,
    updated_by     bigint unsigned not null,
    primary key (reservation_id)
);

drop table if exists `user`;
create table user
(
    user_id    bigint unsigned not null auto_increment,
    username   varchar(255),
    created_at datetime(6),
    updated_at datetime(6),
    deleted_at datetime(6),
    created_ay bigint unsigned not null,
    updated_by bigint unsigned not null,
    primary key (user_id)
);

