drop table IF EXISTS Account;
drop table IF EXISTS MeetingTemplateFields ;
drop table IF EXISTS SessionTemplateFields ;
drop table IF EXISTS MeetingTemplate ;
drop table IF EXISTS MeetingRegInfoFields ;
drop table IF EXISTS SessionRegInfoFields ;
drop table IF EXISTS MeetingRegInfo ;
drop table IF EXISTS MeetingTemplateMetaData ;
CREATE TABLE Account (
  userName             	varchar(12) NOT NULL,
  password 		varchar(32) NOT NULL,
  role     		varchar(32) NOT NULL,
  firstName 		varchar(32) NOT NULL default '',
  middleInitials 	varchar(16) default NULL,
  lastName 		varchar(32) NOT NULL default '',
  eMail 		varchar(32) NOT NULL default '',
  iEEENum		int(11)     NOT NULL default '0',
  PRIMARY KEY  (userName)
) TYPE=InnoDB;

CREATE TABLE MeetingTemplateMetaData (
  displayOrder		int(11) NOT NULL,
  name             	varchar(80) NOT NULL,
  value 		varchar(132) NOT NULL,
  type     		varchar(32) NOT NULL
) TYPE=InnoDB;

CREATE TABLE MeetingTemplateFields (
  mtNumber            	int(11) NOT NULL,
  displayOrder		int(11) NOT NULL,
  name             	varchar(80) NOT NULL,
  value 		varchar(132) NOT NULL,
  type     		varchar(32) NOT NULL,
  PRIMARY KEY  (mtNumber,name)
) TYPE=InnoDB;

CREATE TABLE SessionTemplateFields (
  mtNumber            	int(11) NOT NULL,
  displayOrder		int(11) NOT NULL,
  name             	varchar(80) NOT NULL,
  value 		varchar(132) NOT NULL,
  type     		varchar(32) NOT NULL,
  dateSpan           	varchar(32) NOT NULL,
  timeSpan           	varchar(32) NOT NULL,
  room           	varchar(32) NOT NULL,
  convener       	varchar(32) NOT NULL,
  PRIMARY KEY  (mtNumber,name)
) TYPE=InnoDB;

CREATE TABLE MeetingTemplate (
  mtNumber             	int(11) NOT NULL auto_increment,
  name			varchar(80),
  dateSpan			varchar(32),
  location			varchar(32),
  modified			varchar(32),
  header			varchar(255),
  trailer               varchar(255),
  PRIMARY KEY  		(mtnumber),
  UNIQUE xmeet 		(name,dateSpan)
) TYPE=InnoDB;

CREATE TABLE MeetingRegInfoFields (
  mtNumber            	int(11) NOT NULL,
  userName             	varchar(12) NOT NULL,
  name             	varchar(80) NOT NULL,
  value 		varchar(132) NOT NULL,
  PRIMARY KEY  (mtNumber,name,userName)
) TYPE=InnoDB;

CREATE TABLE SessionRegInfoFields (
  mtNumber            	int(11) NOT NULL,
  userName             	varchar(12) NOT NULL,
  name             	varchar(80) NOT NULL,
  value 		varchar(132) NOT NULL,
  PRIMARY KEY  (mtNumber,name,userName)
) TYPE=InnoDB;

CREATE TABLE MeetingRegInfo (
  mtNumber             	int(11) NOT NULL,
  userName             	varchar(12) NOT NULL,
  UNIQUE xmeetReg       (mtNumber,userName)
) TYPE=InnoDB;

show tables;