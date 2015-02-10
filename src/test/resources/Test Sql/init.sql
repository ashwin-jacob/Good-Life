CREATE SCHEMA goodlife;

CREATE TABLE CHAPTER
(
	CHAP_ID INTEGER AUTO_INCREMENT,
	CHAP_DESCR VARCHAR(255),
	CHAP_TITLE VARCHAR(255) NOT NULL,
	ORDER_ID INTEGER NOT NULL,
	PRIMARY KEY (CHAP_ID)
);

CREATE TABLE CHAPTER_PAGE
(
	PAGE_ID INTEGER AUTO_INCREMENT,
	CHAP_ID INTEGER NOT NULL,
	PAGE_NUM INTEGER,
	PAGE_URL VARCHAR(255),
	PRIMARY KEY (PAGE_ID)
);

CREATE TABLE SUB_CHAPTER
(
	SUB_CHAP_ID INTEGER AUTO_INCREMENT,
	CHAP_ID INTEGER NOT NULL,
	SUB_CHAP_DESCR VARCHAR(255),
	SUB_CHAP_TITLE VARCHAR(255) NOT NULL,
	ORDER_ID INTEGER NOT NULL,
	PRIMARY KEY (SUB_CHAP_ID)
);

CREATE TABLE MULTI_CHOICE_Q
(
	MC_Q_ID INTEGER AUTO_INCREMENT,
	Q_TXT VARCHAR(255),
	HELP_TXT VARCHAR(255),
	CORR_ANS INTEGER,
	SUB_CHAP_ID INTEGER NOT NULL,
	ORDER_ID INTEGER NOT NULL,
	PRIMARY KEY (MC_Q_ID)
);

CREATE TABLE MULTI_CHOICE_OPTION
(
	OPTION_ID INTEGER AUTO_INCREMENT,
	MC_Q_ID INTEGER NOT NULL,
	CHOICE_TXT VARCHAR(255),
	PRIMARY KEY (OPTION_ID)
);

CREATE TABLE MC_USER_ANS
(
	USR_ID INTEGER NOT NULL,
	MC_Q_ID INTEGER NOT NULL,
	USR_ANS INTEGER NOT NULL,
	PRIMARY KEY (USR_ID, MC_Q_ID)
);

CREATE TABLE SHORT_ANS_Q
(
	SA_Q_ID INTEGER AUTO_INCREMENT,
	SUB_CHAP_ID INTEGER NOT NULL,
	QUESTION VARCHAR(255),
	HELP_TXT VARCHAR(255),
	ORDER_ID INTEGER NOT NULL,
	PRIMARY KEY (SA_Q_ID)
);

CREATE TABLE SHORT_ANS_USER_ANS
(
	SA_Q_ID INTEGER NOT NULL,
	SUB_CHAP_ID INTEGER NOT NULL,
	USR_ANS INTEGER NOT NULL,
	USR_ID INTEGER NOT NULL,
	APRVD TINYINT(1) DEFAULT 0,
	PRIMARY KEY (SUB_CHAP_ID,SA_Q_ID)
);

CREATE TABLE UPLOAD_FILE_Q
(
	UP_Q_ID INTEGER AUTO_INCREMENT,
	QUESTION VARCHAR(255),
	SUB_CHAP_ID INTEGER NOT NULL,
	HELP_TXT VARCHAR(255),
	PRIMARY KEY (UP_Q_ID)
);

CREATE TABLE UPLOADED_ANS
(
	UP_ANS_ID INTEGER AUTO_INCREMENT,
	UP_Q_ID INTEGER NOT NULL,
	MEDIA_TYP_ID INTEGER NOT NULL,
	FILE_PATH VARCHAR(255),
	USR_ID INTEGER NOT NULL,
	APRVD TINYINT(1) DEFAULT 0,
	SHARED TINYINT(1) DEFAULT 0,
	PRIMARY KEY (UP_ANS_ID)
);

CREATE TABLE COMMENT_FLAG
(
	FLG_ID INTEGER AUTO_INCREMENT,
	FLGD_BY INTEGER NOT NULL,
	CMNT_ID INTEGER NOT NULL,
	PRIMARY KEY (FLG_ID)
);

CREATE TABLE COMMENT_LIKE
(
	LK_ID INTEGER AUTO_INCREMENT,
	LKD_BY INTEGER NOT NULL,
	CMNT_ID INTEGER NOT NULL,
	PRIMARY KEY (LK_ID)
);

CREATE TABLE UPLOAD_ANS_FLAG
(
	FLG_ID INTEGER AUTO_INCREMENT,
	FLGD_BY INTEGER NOT NULL,
	UP_ANS_ID INTEGER NOT NULL,
	PRIMARY KEY (FLG_ID)
);

CREATE TABLE UPLOAD_ANS_LIKE
(
	LK_ID INTEGER AUTO_INCREMENT,
	LKD_BY INTEGER NOT NULL,
	UP_ANS_ID INTEGER NOT NULL,
	PRIMARY KEY (LK_ID)
);

CREATE TABLE UPLOAD_POST_FLAG
(
	FLG_ID INTEGER AUTO_INCREMENT,
	FLGD_BY INTEGER NOT NULL,
	PST_ID INTEGER NOT NULL,
	PRIMARY KEY (FLG_ID)
);

CREATE TABLE UPLOAD_POST_LIKE
(
	LK_ID INTEGER AUTO_INCREMENT,
	LKD_BY INTEGER NOT NULL,
	PST_ID INTEGER NOT NULL,
	PRIMARY KEY (LK_ID)
);

CREATE TABLE INSTRUCTOR
(
	USR_ID INTEGER NOT NULL,
	ROSTER_ID INTEGER AUTO_INCREMENT,
	N_STDNT INTEGER,
	TOT_CAP INTEGER,
	START_DT DATE,
	PRIMARY KEY (USR_ID, ROSTER_ID)
);

CREATE TABLE STUDENT
(
	USR_ID INTEGER NOT NULL,
	ROSTER_ID INTEGER NOT NULL,
	CRNT_SUB_CHAP_ID INTEGER,
	START_DT DATE,
	PRIMARY KEY (USR_ID)
);

CREATE TABLE GOODLIFE.USERS
(
	USR_ID INTEGER AUTO_INCREMENT,
	EMAIL VARCHAR(50) NOT NULL,
	USR_NM VARCHAR(50),
	PWD VARCHAR(20),
	ROLE_TYP_CD CHAR(1),
	RGSTRD TINYINT(1) NOT NULL DEFAULT 0,
	INVIT_CD INTEGER NOT NULL,
	INVIT_BY VARCHAR(255) NOT NULL,
	INVIT_DT DATE,
	USR_STS_ID INTEGER,
	FRST_NM VARCHAR(255),
	LST_NM VARCHAR(255),
	CITY VARCHAR(255),
	STATE CHAR(2),
	ABT_ME VARCHAR(255),
	PRF_IMG_PATH VARCHAR(255),
	PROMO_DT DATE,
	PRIMARY KEY (USR_ID, EMAIL)
);

CREATE TABLE USER_STATUS
(
	USR_STS_ID INTEGER AUTO_INCREMENT,
	USR_ID INTEGER NOT NULL,
	STS_TYP_CD CHAR(1) NOT NULL,
	STRT_DT DATE,
	END_DT DATE,
	PRIMARY KEY (USR_STS_ID)
);

CREATE TABLE SUPER_ADMIN
(
	USR_ID INTEGER NOT NULL,
	PRIMARY KEY (USR_ID)
);

CREATE TABLE UPLOAD_POST
(
	POST_ID INTEGER AUTO_INCREMENT,
	SUBJ_TXT VARCHAR(255),
	DESCR_TXT VARCHAR(255),
	FILE_PATH VARCHAR(255) NOT NULL,
	MEDIA_TYP_ID INTEGER NOT NULL,
	USR_ID INTEGER NOT NULL,
	PRIMARY KEY (POST_ID)
);