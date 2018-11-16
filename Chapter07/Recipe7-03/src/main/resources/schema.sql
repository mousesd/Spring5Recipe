/*
CREATE TABLE TODO (
  ID          BIGINT AUTO_INCREMENT PRIMARY KEY,
  OWNER       VARCHAR(255) NOT NULL,
  DESCRIPTION VARCHAR(255) NOT NULL,
  COMPLETED   BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE USERS (
    USERNAME    VARCHAR(50)    NOT NULL,
    PASSWORD    VARCHAR(60)    NOT NULL,
    ENABLED     SMALLINT,
    PRIMARY KEY (USERNAME)
);

CREATE TABLE AUTHORITIES (
    USERNAME    VARCHAR(50)    NOT NULL,
    AUTHORITY   VARCHAR(50)    NOT NULL,
    FOREIGN KEY (USERNAME) REFERENCES USERS
);
*/

CREATE TABLE TODO (
  ID BIGINT AUTO_INCREMENT PRIMARY KEY ,
  OWNER VARCHAR(255) NOT NULL,
  DESCRIPTION VARCHAR(255) NOT NULL,
  COMPLETED BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE MEMBER (
    ID          BIGINT         NOT NULL,
    USERNAME    VARCHAR(50)    NOT NULL,
    PASSWORD    VARCHAR(32)    NOT NULL,
    PRIMARY KEY (ID)
);

CREATE TABLE MEMBER_ROLE (
    MEMBER_ID    BIGINT         NOT NULL,
    ROLE         VARCHAR(10)    NOT NULL,
    FOREIGN KEY (MEMBER_ID) REFERENCES MEMBER
);
