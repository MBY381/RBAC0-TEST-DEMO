/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2021/10/28 23:46:18                          */
/*==============================================================*/


drop table if exists Permission;

drop table if exists Role;

drop table if exists Role_Permission;

drop table if exists User;

drop table if exists User_Role;

/*==============================================================*/
/* Table: Permission                                            */
/*==============================================================*/
create table Permission
(
   p_id                 varchar(50) not null,
   p_name               varchar(50),
   primary key (p_id)
);

/*==============================================================*/
/* Table: Role                                                  */
/*==============================================================*/
create table Role
(
   role_id              varchar(50) not null,
   role_name            varchar(50),
   primary key (role_id)
);

/*==============================================================*/
/* Table: Role_Permission                                       */
/*==============================================================*/
create table Role_Permission
(
   role_id              varchar(1),
   p_id                 varchar(1)
);

/*==============================================================*/
/* Table: User                                                  */
/*==============================================================*/
create table User
(
   u_id                 varchar(50) not null,
   u_name               varchar(50),
   u_pwd                varchar(50),
   primary key (u_id)
);

/*==============================================================*/
/* Table: User_Role                                             */
/*==============================================================*/
create table User_Role
(
   u_id                 varchar(1),
   role_id              varchar(1)
);

alter table Role_Permission add constraint FK_Reference_3 foreign key (role_id)
      references Role (role_id) on delete restrict on update restrict;

alter table Role_Permission add constraint FK_Reference_4 foreign key (p_id)
      references Permission (p_id) on delete restrict on update restrict;

alter table User_Role add constraint FK_Reference_1 foreign key (u_id)
      references User (u_id) on delete restrict on update restrict;

alter table User_Role add constraint FK_Reference_5 foreign key (role_id)
      references Role (role_id) on delete restrict on update restrict;

