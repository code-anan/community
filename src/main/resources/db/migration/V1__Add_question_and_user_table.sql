create table question
(
    id            int auto_increment
        primary key,
    title         varchar(50),
    description   text,
    gmt_create    bigint,
    gmt_modify    bigint,
    author        int,
    comment_count int default 0,
    view_count    int default 0,
    like_count    int default 0,
    tag           varchar(256)
);
create table user
(
    id         int auto_increment
        primary key,
    account_id varchar(100),
    name       varchar(50),
    token      varchar(36),
    gmt_create bigint,
    gmt_modify bigint,
    bio        varchar(256),
    avator_url varchar(256)
);
create table comment
(
    id            int auto_increment,
    parentid      int,
    type          int,
    commentauthor int not null,
    gmtcreate     bigint,
    gmtmodify     bigint,
    likecount     int default 0,
    content       varchar(1024),
    constraint comment_pk
        primary key (id)
);
comment on column comment.parentid is '回复的对象id';

comment on column comment.type is '问题回复1评论回复2';

comment on column comment.commentauthor is '评论人';


create table notification
(
    id        int not null,
    notifier  int not null,
    receiver  int not null,
    outerid   int not null,
    type      int not null,
    gmtcreate bigint,
    status    int default 0,
    constraint notification_pk
        primary key (id)
);

comment on column notification.notifier is '发起人';

comment on column notification.receiver is '接收人';

comment on column notification.outerid is '外键id';

comment on column notification.status is '0表示未读  1表示已读';


