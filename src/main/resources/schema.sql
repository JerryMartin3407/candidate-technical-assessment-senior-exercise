DROP TABLE ACTOR IF EXISTS;
CREATE TABLE ACTOR(
    ACTOR_ID integer identity primary key,
    ACTOR_NAME varchar(255) not null,
);

DROP TABLE MOVIE IF EXISTS;
CREATE TABLE MOVIE(
    MOVIE_ID integer identity primary key,
    MOVIE_NAME varchar(255) not null,
);

DROP TABLE CREDITS IF EXISTS;
CREATE TABLE CREDITS(
    ACTOR_ID integer,
    MOVIE_ID integer,
);

