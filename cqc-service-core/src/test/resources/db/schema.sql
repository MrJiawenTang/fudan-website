drop table if exists t_user;

/*==============================================================*/
/* Table: t_user                                                */
/*==============================================================*/
create table t_user
(
   id                   bigint not null auto_increment comment '用户ID',
   username             varchar(128) not null comment '用户名',
   password             varchar(128) not null comment '密码',
   salt                 varchar(128) not null comment '盐噪声',
   realname             varchar(128) comment '真实姓名',
   nickname             varchar(128) comment '昵称',
   mobile               varchar(32) comment '手机号码',
   sex                  int default 0 comment '性别(0-女,1-男,99-未知)',
   email                varchar(128) comment '邮箱',
   remark               varchar(512) comment '备注',
   create_by            bigint comment '创建人',
   create_time          char(20) comment '创建时间',
   last_update_by       bigint comment '更新人',
   last_update_time     char(20) comment '更新时间',
   deleted              tinyint(1) default 0 comment '是否删除',
   primary key (id),
   unique key AK_USER_NAME (username)
);
drop table if exists t_role;

/*==============================================================*/
/* Table: t_role                                                */
/*==============================================================*/
create table t_role
(
   id                   bigint not null auto_increment comment '角色ID',
   role_code            varchar(128) comment '角色code',
   role_name            varchar(128) comment '角色名称',
   role_description     varchar(512) comment '角色描述',
   create_by            bigint comment '创建人',
   create_time          char(20) comment '创建时间',
   last_update_by       bigint comment '更新人',
   last_update_time     char(20) comment '更新时间',
   deleted              tinyint(1) default 0 comment '是否删除',
   primary key (id),
   unique key UQ_ROLE_CODE (role_code)
);

drop table if exists t_resource;

/*==============================================================*/
/* Table: t_resource                                            */
/*==============================================================*/
create table t_resource
(
   id                   bigint not null auto_increment comment 'ID',
   parent_id            bigint comment '父节点',
   resource_name        varchar(128) comment '资源名称',
   resource_description varchar(256) comment '资源描述',
   resource_type        varchar(64) comment '资源类型',
   resource_url         varchar(512) comment '资源url',
   create_by            bigint comment '创建人',
   create_time          char(20) comment '创建时间',
   last_update_by       bigint comment '更新人',
   last_update_time     char(20) comment '更新时间',
   deleted              tinyint(1) default 0 comment '是否删除',
   primary key (id)
);

drop table if exists t_role_resource_mapping;

/*==============================================================*/
/* Table: t_role_resource_mapping                               */
/*==============================================================*/
create table t_role_resource_mapping
(
   id                   bigint not null comment 'ID',
   resource_id          bigint comment '资源ID',
   role_code            varchar(128) comment '角色code',
   primary key (id)
);

alter table t_role_resource_mapping add constraint FK_MP_RECOURSE_ROLE_CODE foreign key (role_code)
      references t_role (role_code) on delete restrict on update restrict;

alter table t_role_resource_mapping add constraint FK_MP_ROLE_RECOURSE_ID foreign key (resource_id)
      references t_resource (id) on delete restrict on update restrict;

drop table if exists t_user_role_mapping;

/*==============================================================*/
/* Table: t_user_role_mapping                                   */
/*==============================================================*/
create table t_user_role_mapping
(
   id                   bigint not null comment 'ID',
   user_id              bigint comment '用户ID',
   role_code            varchar(128) not null comment '角色code',
   primary key (id)
);

alter table t_user_role_mapping add constraint FK_MP_ROLE_USER_ID foreign key (user_id)
      references t_user (id) on delete restrict on update restrict;

alter table t_user_role_mapping add constraint FK_MP_USER_ROLE_CODE foreign key (role_code)
      references t_role (role_code) on delete restrict on update restrict;

drop table if exists t_cms_category;

/*==============================================================*/
/* Table: cms_category                                          */
/*==============================================================*/
create table t_cms_category
(
   id                   bigint not null auto_increment comment 'ID',
   code                 varchar(64) comment '编码',
   name                 varchar(64) not null comment '名称',
   parent_id            bigint comment '父分类ID',
   type                 int default 1 comment '类型(1:普通分类,2:a标签,3:_blank标签)',
   jump_url             varchar(255) comment '跳转地址',
   template_path        varchar(255) comment '模板路径',
   sort                 float default 0 comment '排序',
   disabled             tinyint(1) default 0 comment '是否禁用',
   create_by            bigint comment '创建人',
   create_time          char(20) comment '创建时间',
   last_update_by       bigint comment '更新人',
   last_update_time     char(20) comment '更新时间',
   deleted              tinyint(1) default 0 comment '是否删除',
   primary key (id)
);

drop table if exists t_cms_content;

/*==============================================================*/
/* Table: cms_content                                           */
/*==============================================================*/
create table t_cms_content
(
   id                   bigint not null auto_increment comment 'ID',
   title                varchar(256) not null comment '标题',
   category_code        varchar(64) comment '分类ID',
   copied               tinyint(1) default 0 comment '是否转载',
   author               varchar(64) comment '作者',
   editor               varchar(64) comment '编辑',
   description          varchar(256) comment '简介',
   tags                 varchar(256) comment '标签',
   cover                varchar(255) comment '封面',
   status               int default 0 comment '状态：0、草稿 1、已发布 2、待审核',
   comments             int default 0 comment '评论数',
   clicks               int default 0 comment '点击数',
   sort                 float default 0 comment '排序',
   publish_time         char(20) comment '发布时间',
   publish_user         varchar(64) comment '发布人',
   create_by            bigint comment '创建人',
   create_time          char(20) comment '创建时间',
   last_update_by       bigint comment '更新人',
   last_update_time     char(20) comment '更新时间',
   deleted              tinyint(1) default 0 comment '是否删除',
   primary key (id)
);

drop table if exists t_cms_content_attribute;

/*==============================================================*/
/* Table: cms_content_attribute                                 */
/*==============================================================*/
create table t_cms_content_attribute
(
   id                   bigint not null comment 'ID',
   content_id           bigint not null comment '内容ID',
   source               varchar(64) comment '内容来源',
   source_url           varchar(255) comment '来源地址',
   data                 longtext comment '内容',
   word_count           int default 0 comment '字数',
   primary key (id)
);

alter table t_cms_content_attribute add constraint FK_CONTENT_ID foreign key (content_id)
      references t_cms_content (id) on delete restrict on update restrict;
      
drop table if exists t_cms_site_parts;

/*==============================================================*/
/* Table: t_cms_site_parts                                      */
/*==============================================================*/
create table t_cms_site_parts
(
   id                   bigint not null auto_increment comment 'ID',
   name                 varchar(64) not null comment '名称',
   sort                 float default 0 comment '排序',
   content              text not null comment '内容(html)',
   position             int default 0 comment '位置(1-上,2-下)',
   disabled             tinyint(1) default 0 comment '是否禁用',
   create_by            bigint comment '创建人',
   create_time          char(20) comment '创建时间',
   last_update_by       bigint comment '更新人',
   last_update_time     char(20) comment '更新时间',
   deleted              tinyint(1) default 0 comment '是否删除',
   primary key (id)
);

drop table if exists t_cms_friendly_link;

/*==============================================================*/
/* Table: t_cms_friendly_link                                   */
/*==============================================================*/
create table t_cms_friendly_link
(
   id                   bigint not null auto_increment comment 'ID',
   name                 varchar(64) not null comment '名称',
   link                 varchar(255) not null comment '链接',
   sort                 float default 0 comment '排序',
   create_by            bigint comment '创建人',
   create_time          char(20) comment '创建时间',
   last_update_by       bigint comment '更新人',
   last_update_time     char(20) comment '更新时间',
   deleted              tinyint(1) default 0 comment '是否删除',
   primary key (id)
);

drop table if exists t_cms_slide;

/*==============================================================*/
/* Table: t_cms_slide                                           */
/*==============================================================*/
create table t_cms_slide
(
   id                   bigint not null auto_increment comment 'ID',
   name                 varchar(64) not null comment '名称',
   img                  varchar(255) not null comment '图片地址',
   title                varchar(128) comment '标题',
   link                 varchar(255) comment '链接',
   sort                 float default 0 comment '排序',
   create_by            bigint comment '创建人',
   create_time          char(20) comment '创建时间',
   last_update_by       bigint comment '更新人',
   last_update_time     char(20) comment '更新时间',
   deleted              tinyint(1) default 0 comment '是否删除',
   primary key (id)
);
