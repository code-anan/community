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
create table COMMENT
(
    ID            INT auto_increment,
    PARENTID      INT,
    TYPE          INT,
    COMMENTAUTHOR INT not null,
    GMTCREATE     BIGINT,
    GMTMODIFY     BIGINT,
    LIKECOUNT     INT default 0,
    CONTENT       VARCHAR(1024),
    constraint COMMENT_PK
        primary key (ID)
);
comment on column COMMENT.PARENTID is '回复的对象id';

comment on column COMMENT.TYPE is '问题回复1评论回复2';

comment on column COMMENT.COMMENTAUTHOR is '评论人';


create table NOTIFICATION
(
    ID        INT not null,
    NOTIFIER  INT not null,
    RECEIVER  INT not null,
    OUTERID   INT not null,
    TYPE      INT not null,
    GMTCREATE BIGINT,
    STATUS    INT default 0,
    constraint NOTIFICATION_PK
        primary key (ID)
);

comment on column NOTIFICATION.NOTIFIER is '发起人';

comment on column NOTIFICATION.RECEIVER is '接收人';

comment on column NOTIFICATION.OUTERID is '外键id';

comment on column NOTIFICATION.STATUS is '0表示未读  1表示已读';


