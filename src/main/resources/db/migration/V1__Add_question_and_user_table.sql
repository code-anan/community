create table QUESTION
(
    ID            INT auto_increment
        primary key,
    TITLE         VARCHAR(50),
    DESCRIPTION   TEXT,
    GMT_CREATE    BIGINT,
    GMT_MODIFY    BIGINT,
    AUTHOR        INT,
    COMMENT_COUNT INT default 0,
    VIEW_COUNT    INT default 0,
    LIKE_COUNT    INT default 0,
    TAG           VARCHAR(256)
);
create table USER
(
    ID         INT auto_increment
        primary key,
    ACCOUNT_ID VARCHAR(100),
    NAME       VARCHAR(50),
    TOKEN      VARCHAR(36),
    GMT_CREATE BIGINT,
    GMT_MODIFY BIGINT,
    BIO        VARCHAR(256),
    AVATOR_URL VARCHAR(256)
);
