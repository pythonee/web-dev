<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

		<title>SQL Statements</title>

	</head>

	<body>
		<jsp:include page="/header.jsp"></jsp:include>
		<div id="main">
			<div id="sql">
				<h3>
					Create WANGFEI_TEACHER Table
				</h3>
				<div class="sqlStatement">
					<p>
						create table WANGFEI_TEACHER ( TEACHER_ID NUMBER(38) not null,
						TEACHER_FIRST_NAME VARCHAR2(25) not null, TEACHER_LAST_NAME
						VARCHAR2(25) not null )

					</P>
					<P>

						alter table WANGFEI_TEACHER add constraint WANGFEI_TEACHER_PK
						primary key (TEACHER_ID)

					</p>
				</div>

				<h3>
					Create WANGFEI_PROGRAMME Table
				</h3>
				<div class="sqlStatement">
					<p>
						create table WANGFEI_PROGRAMME ( PROGRAMME_ID NUMBER(38) not null,
						PROGRAMME_TITLE VARCHAR2(100) not null, PROGRAMME_DESC
						VARCHAR2(250) not null, PROGRAMME_COST NUMBER(38) not null )
					</p>
					<p>
						alter table WANGFEI_PROGRAMME add constraint WANGFEI_PROGRAMME_PK
						primary key (PROGRAMME_ID)

					</p>
				</div>

				<h3>
					Create WANGFEI_MODULE Table
				</h3>
				<div class="sqlStatement">
					<p>
						create table WANGFEI_MODULE ( MODULE_ID NUMBER(38) not null,
						MODULE_TITLE VARCHAR2(100) not null, MODULE_DESC VARCHAR2(250) not
						null, MODULE_TEACHER NUMBER(38) not null, MODULE_START DATE not
						null, MODULE_LAST NUMBER(38) not null )
					</p>
					<p>
						alter table WANGFEI_MODULE add constraint WANGFEI_MODULES_PK
						primary key (MODULE_ID)
					</p>
					<p>
						alter table WANGFEI_MODULE add constraint
						WANGFEI_MODULE_WANGFEI_TE_FK1 foreign key (MODULE_TEACHER)
						references WANGFEI_TEACHER (TEACHER_ID) on delete cascade
					</p>
				</div>

				<h3>
					Create WANGFEI_STUDENT Table
				</h3>
				<div class="sqlStatement">
					<p>
						create table WANGFEI_STUDENT ( STU_ID NUMBER(38) not null,
						STU_FIRST_NAME VARCHAR2(20) not null, STU_LAST_NAME VARCHAR2(20)
						not null, STU_PASSWORD VARCHAR2(25) not null, STU_SEX CHAR(1 CHAR)
						not null, STU_EMAIL VARCHAR2(100) not null, STU_PROGRAMME_ID
						NUMBER(38) not null, STU_ISPAID CHAR(1 CHAR) not null, STU_COUNTRY
						VARCHAR2(20) not null, STU_BIRTHDAY DATE not null,
						STU_REGISTE_TIME TIMESTAMP(6) WITH LOCAL TIME ZONE default
						CURRENT_TIMESTAMP not null, STU_USERNAME VARCHAR2(20) not null )
					</p>
					<p>
						alter table WANGFEI_STUDENT add constraint WANGFEI_STUDENT_PK
						primary key (STU_ID)
					</p>
					<p>
						alter table WANGFEI_STUDENT add constraint
						WANGFEI_STUDENT_PROGRAMME_FK foreign key (STU_PROGRAMME_ID)
						references WANGFEI_PROGRAMME (PROGRAMME_ID)
					</p>
				</div>

				<h3>
					Create WANGFEI_MODULE_PROGRAMME Table
				</h3>
				<div class="sqlStatement">
					<p>
						create table WANGFEI_MODULE_PROGRAMME ( MODULE_ID NUMBER(38) not
						null, PROGRAMME_ID NUMBER(38) not null )
					</p>
					<p>
						alter table WANGFEI_MODULE_PROGRAMME add constraint
						WANGFEI_MODULE_PROGRAMME_PK primary key (MODULE_ID, PROGRAMME_ID)
					</p>
					<p>
						alter table WANGFEI_MODULE_PROGRAMME add constraint
						WANGFEI_MODULE_PROGRAMME__FK1 foreign key (MODULE_ID) references
						WANGFEI_MODULE (MODULE_ID) on delete cascade
					</p>
					<p>
						alter table WANGFEI_MODULE_PROGRAMME add constraint
						WANGFEI_MODULE_PROGRAMME__FK2 foreign key (PROGRAMME_ID)
						references WANGFEI_PROGRAMME (PROGRAMME_ID) on delete cascade
					</p>
				</div>


				<h3>
					Create WANGFEI_STUDENT_MODULE Table
				</h3>
				<div class="sqlStatement">
					<p>
						create table WANGFEI_STUDENT_MODULE ( MODULE_ID NUMBER(38) not
						null, STU_ID NUMBER(38) not null )
					</p>
					<p>
						alter table WANGFEI_STUDENT_MODULE add constraint
						WANGFEI_STUDENT_MODULE_PK primary key (MODULE_ID, STU_ID)
					</p>
					<p>
						alter table WANGFEI_STUDENT_MODULE add constraint
						WANGFEI_STUDENT_MODULE_WA_FK1 foreign key (MODULE_ID) references
						WANGFEI_MODULE (MODULE_ID) on delete cascade
					</p>
					<p>
						alter table WANGFEI_STUDENT_MODULE add constraint
						WANGFEI_STUDENT_MODULE_WA_FK2 foreign key (STU_ID) references
						WANGFEI_STUDENT (STU_ID) on delete cascade
					</p>
				</div>

				<h3>
					Create WANGFEI_SEQUENCE Sequence
				</h3>
				<div class="sqlStatement">
					<p>
						CREATE SEQUENCE WANGFEI_SEQUENCE MINVALUE 1 MAXVALUE
						999999999999999999999999999 INCREMENT BY 1 START WITH 301 CACHE 20
						NOORDER NOCYCLE
					</p>
				</div>

				<h3>
					Create WANGFEI_TEACHER_TRIGGER Trigger
				</h3>
				<div class="sqlStatement">
					<p>
						create or replace trigger WANGFEI_TEACHER_TRIGGER before insert on
						"WANGFEI_TEACHER" for each row begin if inserting then if

						:NEW."TEACHER_ID" is null then select WANGFEI_SEQUENCE.nextval
						into :NEW."TEACHER_ID" from dual; end if; end if; end;
					</p>
				</div>
				<h3>
					Create WANGFEI_PROGRAMME_TRIGGER Trigger
				</h3>
				<div class="sqlStatement">
					<p>
						create or replace trigger WANGFEI_PROGRAMME_TRIGGER before insert
						on "WANGFEI_PROGRAMME" for each row begin if inserting then if
						:NEW."PROGRAMME_ID" is null then select WANGFEI_SEQUENCE.nextval
						into :NEW."PROGRAMME_ID" from dual; end if; end if; end;
					</p>
				</div>

				<h3>
					Create WANGFEI_MODULE_TRIGGER Trigger
				</h3>
				<div class="sqlStatement">
					<p>
						create or replace trigger WANGFEI_MODULE_TRIGGER before insert on
						"WANGFEI_MODULE" for each row begin if inserting then if
						:NEW."MODULE_ID" is null then select WANGFEI_SEQUENCE.nextval into
						:NEW."MODULE_ID" from dual; end if; end if; end;
					</p>
				</div>

				<h3>
					Create WANGFEI_STUDENT_TRIGGER Trigger
				</h3>
				<div class="sqlStatement">
					<p>
						create or replace trigger WANGFEI_STUDENT_TRIGGER before insert on
						"WANGFEI_STUDENT" for each row begin if inserting then if
						:NEW."STU_ID" is null then select WANGFEI_SEQUENCE.nextval into
						:NEW."STU_ID" from dual; end if; end if; end;
					</p>
				</div>
			</div>
		</div>
		<jsp:include page="/footer.jsp"></jsp:include>
	</body>
</html>
