# 这是一个oj评判系统(Online Judge System)
现有网址如：[北大oj](http://poj.org/)
业务流程如下

![alt text](image.png)
用户服务：提供用户登录、用户的增删改查等管理功能
题目服务：提供题目的增删改查管理、题目提交功能
判题服务：提供判题功能，调用代码沙箱并比对判题结果
代码沙箱：提供编译执行代码、返回结果的功能
公共模块：提供公共代码，比如数据模型、全局请求响应封装、全局异常处理、工具类等
网关服务：提供统一的 API 转发、聚合文档、全局跨域解决等功能

### 数据库表设计

#### 用户表
```
-- 用户表
create table if not exists user
(
    id           bigint auto_increment comment 'id' primary key,
    userAccount  varchar(256)                           not null comment '账号',
    userPassword varchar(512)                           not null comment '密码',
    unionId      varchar(256)                           null comment '微信开放平台id',
    mpOpenId     varchar(256)                           null comment '公众号openId',
    userName     varchar(256)                           null comment '用户昵称',
    userAvatar   varchar(1024)                          null comment '用户头像',
    userProfile  varchar(512)                           null comment '用户简介',
    userRole     varchar(256) default 'user'            not null comment '用户角色：user/admin/ban',
    createTime   datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime   datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete     tinyint      default 0                 not null comment '是否删除',
    index idx_unionId (unionId)
) comment '用户' collate = utf8mb4_unicode_ci;
```
#### 题目表
```
-- 题目表
create table if not exists question
(
    id         bigint auto_increment comment 'id' primary key,
    title      varchar(512)                       null comment '标题',
    content    text                               null comment '内容',
    tags       varchar(1024)                      null comment '标签列表（json 数组）',
    answer     text                               null comment '题目答案',
    submitNum  int      default 0             not null  comment '题目提交数',
    acceptNum  int      default 0             not null  comment '题目通过数',
    judgeCase  text null comment '判题用例（json数组）',
    judgeConfig text null comment '判题配置(json对象)',
    thumbNum   int      default 0                 not null comment '点赞数',
    favourNum  int      default 0                 not null comment '收藏数',
    userId     bigint                             not null comment '创建用户 id',
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete   tinyint  default 0                 not null comment '是否删除',
    index idx_userId (userId)
    ) comment '题目' collate = utf8mb4_unicode_ci;
```

#### 题目提交表

```
-- 题目提交表
create table if not exists question_submit
(
    id         bigint auto_increment comment 'id' primary key,
    language   varchar(128)                       not null comment '编程语言',
    code       text                               not null comment '用户提交代码',
    judgeInfo  text                               not null comment '判题信息(json对象)',
    status     int     default 0                  not null comment '判题状态(0 - 带判题， 1-判题中， 2-成功， 3-失败)',
    questionId bigint                             not null comment '题目id',
    userId     bigint                             not null comment '创建用户 id',
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete   tinyint  default 0                 not null comment '是否删除',
    index idx_questionId (questionId),
    index idx_userId (userId)
    ) comment '题目提交表';
```