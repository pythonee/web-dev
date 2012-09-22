CREATE SEQUENCE  WANGFEI_SEQUENCE  MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 START WITH 301 CACHE 20 NOORDER  NOCYCLE 


create or replace trigger WANGFEI_TEACHER_TRIGGER     before insert on "WANGFEI_TEACHER"    for each row begin     if inserting then       if :NEW."TEACHER_ID" is null then          select WANGFEI_SEQUENCE.nextval into :NEW."TEACHER_ID" from dual;       end if;    end if; end;


create or replace trigger WANGFEI_PROGRAMME_TRIGGER     before insert on "WANGFEI_PROGRAMME"    for each row begin     if inserting then       if :NEW."PROGRAMME_ID" is null then          select WANGFEI_SEQUENCE.nextval into :NEW."PROGRAMME_ID" from dual;       end if;    end if; end;


create or replace trigger WANGFEI_MODULE_TRIGGER     before insert on "WANGFEI_MODULE"    for each row begin     if inserting then       if :NEW."MODULE_ID" is null then          select WANGFEI_SEQUENCE.nextval into :NEW."MODULE_ID" from dual;       end if;    end if; end;


create or replace trigger WANGFEI_STUDENT_TRIGGER     before insert on "WANGFEI_STUDENT"    for each row begin     if inserting then       if :NEW."STU_ID" is null then          select WANGFEI_SEQUENCE.nextval into :NEW."STU_ID" from dual;       end if;    end if; end;





