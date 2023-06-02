create schema if not exists dataset_tagger;
create table if not exists role
(
    id      int auto_increment
        primary key,
    name    varchar(16)             not null comment '权限名',
    name_zh varchar(8) charset utf8 not null comment '中文权限名'
)
    comment '权限信息';

create table if not exists user
(
    id         int auto_increment
        primary key,
    username   varchar(32)  not null comment '用户名',
    password   varchar(128) not null comment '密码',
    enabled    tinyint(1)   null,
    locked     tinyint(1)   null,
    phone      varchar(11)  null,
    email      varchar(32)  null,
    gmt_create datetime     not null,
    constraint username_uk
        unique (username)
)
    comment '用户登录信息';

create table if not exists data_set
(
    id             int auto_increment comment '主键id'
        primary key,
    pub_time       datetime                         not null,
    `desc`         varchar(128) charset utf8        null comment '数据集描述',
    sample_type    varchar(16)                      not null,
    tag_type       varchar(16)                      not null,
    gmt_create     datetime                         not null,
    publisher_id   int                              not null,
    dataset_id     varchar(64)                      not null comment '使用UUID生成的全局唯一的id',
    name           varchar(32) default '默认数据集' null,
    publisher_name varchar(32)                      null,
    constraint data_set_pk
        unique (dataset_id),
    constraint data_set_user_id_fk
        foreign key (publisher_id) references user (id)
)
    comment '数据集';

create index dataset_publisher_id_combined_index
    on data_set (publisher_id, dataset_id, `desc`, pub_time, tag_type, sample_type);

create table if not exists sample
(
    id          int auto_increment
        primary key,
    dataset_id  varchar(64) not null,
    content     text        null,
    sample_type varchar(16) null,
    tag_type    varchar(16) null,
    sample_id   varchar(64) not null,
    constraint sample_uk_sample_id
        unique (sample_id),
    constraint sample_data_set_dataset_id_fk
        foreign key (dataset_id) references data_set (dataset_id)
)
    comment '样本';

create table if not exists tag
(
    id        int auto_increment
        primary key,
    tag_id    varchar(64)  not null,
    sample_id varchar(64)  null,
    tagger_id varchar(64)  null,
    tag_time  datetime     null,
    begin_pos varchar(128) not null,
    end_pos   varchar(128) null,
    tag       varchar(64)  null,
    constraint tag_sample_sample_id_fk
        foreign key (sample_id) references sample (sample_id)
            on delete cascade
)
    comment '样本的标记';

create table if not exists user_role
(
    id  int auto_increment
        primary key,
    uid int not null,
    rid int not null,
    constraint user_role_role_id_fk
        foreign key (rid) references role (id),
    constraint user_role_user_id_fk
        foreign key (uid) references user (id)
            on delete cascade
);


