INSERT INTO GOODLIFE.USERS
(USR_ID, EMAIL, USR_NM, PWD, ROLE_TYP_CD, RGSTRD, INVIT_CD, INVIT_BY, INVIT_DT, USR_STS_ID, FRST_NM, LST_NM, CITY, STATE, ABT_ME)
VALUES
(1, 'dhaval.raj@tsgforce.com','dhaval','password','S', 1, 12356, 'Dhaval Raj', CURRENT_DATE, 0, 'Dhaval','Raj','Chicago','IL','I AM DEVELOPER');

INSERT INTO GOODLIFE.CHAPTER
(CHAP_ID,CHAP_TITLE,CHAP_DESCR,ORDER_ID,PUBLISHED)
VALUES(1,'CHAPTER 1 TITLE','CHAPTER 1 DESCRIPTION',1,TRUE);

INSERT INTO GOODLIFE.CHAPTER
(CHAP_ID,CHAP_TITLE,CHAP_DESCR,ORDER_ID,PUBLISHED)
VALUES(2,'CHAPTER 2 TITLE','CHAPTER 2 DESCRIPTION',2,FALSE);

INSERT INTO GOODLIFE.SUBCHAPTER
(SUB_CHAP_ID,CHAPID,SUB_CHAP_DESCR,SUB_CHAP_TITLE,ORDER_ID)
VALUES(1,1,'Sub Chapter 1 Description','Sub Chapter 1 Title',1);

INSERT INTO GOODLIFE.SUBCHAPTER
(SUB_CHAP_ID,CHAPID,SUB_CHAP_DESCR,SUB_CHAP_TITLE,ORDER_ID)
VALUES(2,1,'Sub Chapter 2 Description','Sub Chapter 2 Title',2);

INSERT INTO GOODLIFE.USER_STATUS
(USR_STS_ID, USERID, STS_TYP_CD, STRT_DT, END_DT)
VALUES(1,1,'S',CURRENT_DATE,CURRENT_DATE);

INSERT INTO GOODLIFE.MULTI_CHOICE_Q
(MC_Q_ID,Q_TXT,HELP_TXT,CORR_ANS,SUBCHAPID,ORDER_ID)
VALUES(1,'MULTIPLE CHOICE QUESTION 1','THIS IS HELP TEXT',1,1,1);

INSERT INTO GOODLIFE.MULTI_CHOICE_Q
(MC_Q_ID,Q_TXT,HELP_TXT,CORR_ANS,SUBCHAPID,ORDER_ID)
VALUES(2,'MULTIPLE CHOICE QUESTION 1','THIS IS HELP TEXT',1,1,1);

INSERT INTO GOODLIFE.MULTI_CHOICE_OPTION
(OPTION_ID,MULTIQUESID,CHOICE_TXT)
VALUES(1,1,'OPTION 1');

INSERT INTO GOODLIFE.MULTI_CHOICE_OPTION
(OPTION_ID,MULTIQUESID,CHOICE_TXT)
VALUES(2,1,'OPTION 2');

INSERT INTO GOODLIFE.MC_USER_ANS
(USR_ID,MULTIQUESID,USR_ANS)
VALUES(1,1,1);