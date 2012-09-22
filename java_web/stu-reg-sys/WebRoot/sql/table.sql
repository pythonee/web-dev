
create table WANGFEI_TEACHER
(
  TEACHER_ID         NUMBER(38) not null,
  TEACHER_FIRST_NAME VARCHAR2(25) not null,
  TEACHER_LAST_NAME  VARCHAR2(25) not null
)

alter table WANGFEI_TEACHER
  add constraint WANGFEI_TEACHER_PK primary key (TEACHER_ID)





create table WANGFEI_PROGRAMME
(
  PROGRAMME_ID    NUMBER(38) not null,
  PROGRAMME_TITLE VARCHAR2(100) not null,
  PROGRAMME_DESC  VARCHAR2(250) not null,
  PROGRAMME_COST  NUMBER(38) not null
)


alter table WANGFEI_PROGRAMME
  add constraint WANGFEI_PROGRAMME_PK primary key (PROGRAMME_ID)






create table WANGFEI_MODULE
(
  MODULE_ID      NUMBER(38) not null,
  MODULE_TITLE   VARCHAR2(100) not null,
  MODULE_DESC    VARCHAR2(250) not null,
  MODULE_TEACHER NUMBER(38) not null,
  MODULE_START   DATE not null,
  MODULE_LAST    NUMBER(38) not null
)


alter table WANGFEI_MODULE
  add constraint WANGFEI_MODULES_PK primary key (MODULE_ID)

alter table WANGFEI_MODULE
  add constraint WANGFEI_MODULE_WANGFEI_TE_FK1 foreign key (MODULE_TEACHER)
  references WANGFEI_TEACHER (TEACHER_ID) on delete cascade





create table WANGFEI_STUDENT
(
  STU_ID           NUMBER(38) not null,
  STU_FIRST_NAME   VARCHAR2(20) not null,
  STU_LAST_NAME    VARCHAR2(20) not null,
  STU_PASSWORD     VARCHAR2(25) not null,
  STU_SEX          CHAR(1 CHAR) not null,
  STU_EMAIL        VARCHAR2(100) not null,
  STU_PROGRAMME_ID NUMBER(38) not null,
  STU_ISPAID       CHAR(1 CHAR) not null,
  STU_COUNTRY      VARCHAR2(20) not null,
  STU_BIRTHDAY     DATE not null,
  STU_REGISTE_TIME TIMESTAMP(6) WITH LOCAL TIME ZONE default CURRENT_TIMESTAMP not null,
  STU_USERNAME     VARCHAR2(20) not null
)

alter table WANGFEI_STUDENT
  add constraint WANGFEI_STUDENT_PK primary key (STU_ID)

alter table WANGFEI_STUDENT
  add constraint WANGFEI_STUDENT_PROGRAMME_FK foreign key (STU_PROGRAMME_ID)
  references WANGFEI_PROGRAMME (PROGRAMME_ID)





create table WANGFEI_MODULE_PROGRAMME
(
  MODULE_ID    NUMBER(38) not null,
  PROGRAMME_ID NUMBER(38) not null
)

alter table WANGFEI_MODULE_PROGRAMME
  add constraint WANGFEI_MODULE_PROGRAMME_PK primary key (MODULE_ID, PROGRAMME_ID)

alter table WANGFEI_MODULE_PROGRAMME
  add constraint WANGFEI_MODULE_PROGRAMME__FK1 foreign key (MODULE_ID)
  references WANGFEI_MODULE (MODULE_ID) on delete cascade

alter table WANGFEI_MODULE_PROGRAMME
  add constraint WANGFEI_MODULE_PROGRAMME__FK2 foreign key (PROGRAMME_ID)
  references WANGFEI_PROGRAMME (PROGRAMME_ID) on delete cascade




create table WANGFEI_STUDENT_MODULE
(
  MODULE_ID NUMBER(38) not null,
  STU_ID    NUMBER(38) not null
)



alter table WANGFEI_STUDENT_MODULE
  add constraint WANGFEI_STUDENT_MODULE_PK primary key (MODULE_ID, STU_ID)

alter table WANGFEI_STUDENT_MODULE
  add constraint WANGFEI_STUDENT_MODULE_WA_FK1 foreign key (MODULE_ID)
  references WANGFEI_MODULE (MODULE_ID) on delete cascade

alter table WANGFEI_STUDENT_MODULE
  add constraint WANGFEI_STUDENT_MODULE_WA_FK2 foreign key (STU_ID)
  references WANGFEI_STUDENT (STU_ID) on delete cascade













