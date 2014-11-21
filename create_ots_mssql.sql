if not exists(select * from sys.databases where name = 'ots')
    create database ots;
GO
 use ots;

--DROP TABLE LEVEL;
CREATE TABLE LEVEL (
 lid int identity(1,1), -- 1=Gold and 2=Silver
 levelname varchar(25) NOT NULL,
 commissionrate float NOT NULL,--store value in percent
 CONSTRAINT pk_level primary key (lid)
);


--DROP TABLE USERTYPE;
CREATE TABLE USERTYPE(
 usertypeid int identity(1,1), --1=client, 2=trader and 3=manager
 name varchar(50) NOT NULL,
 CONSTRAINT pk_usertype primary key (usertypeid)
);


--DROP TABLE USERS;
CREATE TABLE USERS(
 uid int identity(1,1),
 username varchar(50) NOT NULL,
 password varchar(50) NOT NULL,
 usertypeid int,
 CONSTRAINT pk_user primary key (uid),
 CONSTRAINT fk_user_usertype foreign key (usertypeid) references USERTYPE (usertypeid)
);


--DROP TABLE OILPRICE;
CREATE TABLE OILPRICE(
 time  datetime,
 price float NOT NULL,
 CONSTRAINT pk_oilprice primary key (time)
);


--DROP TABLE CLIENT;
CREATE TABLE CLIENT (
 cid int,
 fname varchar(25) NOT NULL,
 lname varchar(25),
 phone varchar(20),
 cellphone varchar(20) NOT NULL,
 email varchar(25) NOT NULL,
 password varchar(50) NOT NULL,
 oilbalance float NOT NULL,
 lid int,
 CONSTRAINT pk_client primary key (cid),
 CONSTRAINT fk_client_level foreign key (lid) references LEVEL (lid),
 CONSTRAINT fk_client_users foreign key(cid) references USERS (uid)

);


--DROP TABLE ADDRESS;
CREATE TABLE ADDRESS(
 cid int,
 zip int NOT NULL,
 street varchar(50) NOT NULL,
 city varchar(25) NOT NULL,
 state varchar(15) NOT NULL,
 CONSTRAINT pk_address primary key(cid, zip),
 CONSTRAINT fk_addr_client foreign key (cid) references CLIENT (cid) ON DELETE CASCADE
);


--DROP TABLE COMMISSIONFEETYPE;
CREATE TABLE COMMISSIONFEETYPE(
 commtypeid int identity(1,1), -- 1= oil and 2 = cash
 commname varchar(50) NOT NULL,
 CONSTRAINT pk_commissionfeetype primary key (commtypeid)
);


--DROP TABLE OILTRANSACTION
CREATE TABLE OILTRANSACTION(
 trxid int identity(1,1),
 cid   int,
 trxtime  datetime NOT NULL DEFAULT(getdate()),
 quantity float NOT NULL,
 trxtype bit  NOT NULL default 0, --0 = sell and 1= buy
 trxamount float, --will only come in picture at the time of buying oil
 feeamount float, --will only come in picture if the commission is paid in cash
 feeinoil  float,--will only come in picture if the commission is paid in oil
 commtype int, --1 = oil and 2=cash
 isCancelled bit default 0, --if 1 then trx cancelled
 totaltrxamount float NOT NULL,
 trxbalance float NOT NULL,
 uid int,
 CONSTRAINT pk_oiltransaction primary key (trxid),
 CONSTRAINT fk_oiltrx_feetype foreign key (commtype) references COMMISSIONFEETYPE (commtypeid),
 CONSTRAINT fk_oiltrx_client foreign key (cid) references CLIENT (cid),
 CONSTRAINT fk_oiltrx_user foreign key (uid) references USERS (uid),
);


--DROP TABLE PAYMENT;
CREATE TABLE PAYMENT(
  pid int identity(1,1),
  paymenttime datetime NOT NULL DEFAULT(getdate()),
  paymentamt float,
  isCancelled bit default 0, --if 1 then trx cancelled
  cid int,
  traderid int,
  trxid int,
  CONSTRAINT pk_payment primary key (pid),
  CONSTRAINT fk_payment_client foreign key (cid) references CLIENT (cid),
  CONSTRAINT fk_payment_user foreign key (traderid) references USERS (uid),
  CONSTRAINT fk_payment_oiltrx foreign key (trxid) references OILTRANSACTION(trxid)
);

--Insertion to some pre defined tables
INSERT INTO LEVEL VALUES('Gold',4);
INSERT INTO LEVEL VALUES('Silver',2);
INSERT INTO USERTYPE VALUES('client');
INSERT INTO USERTYPE VALUES('trader');
INSERT INTO USERTYPE VALUES('manager');
INSERT INTO COMMISSIONFEETYPE VALUES('oil');
INSERT INTO COMMISSIONFEETYPE VALUES('cash');
