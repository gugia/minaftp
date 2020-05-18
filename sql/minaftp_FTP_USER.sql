create table FTP_USER
(
    userid          varchar(64)          not null
        primary key,
    userpassword    varchar(64)          null,
    homedirectory   varchar(128)         not null,
    enableflag      tinyint(1) default 1 null,
    writepermission tinyint(1) default 0 null,
    idletime        int        default 0 null,
    uploadrate      int        default 0 null,
    downloadrate    int        default 0 null,
    maxloginnumber  int        default 0 null,
    maxloginperip   int        default 0 null
);

INSERT INTO minaftp.FTP_USER (userid, userpassword, homedirectory, enableflag, writepermission, idletime, uploadrate, downloadrate, maxloginnumber, maxloginperip) VALUES ('admin', 'admin', 'D:/FTP', 1, 1, 0, 0, 0, 0, 0);