package com.changhong.devcenter.minaftp.config;

import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.usermanager.ClearTextPasswordEncryptor;
import org.apache.ftpserver.usermanager.DbUserManagerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * Bootstrap
 *
 * @author L.X <gugia@qq.com>
 */
@Component
public class Bootstrap implements CommandLineRunner {

    @Resource
    private DataSource dataSource;

    @Override
    public void run(String... args) throws Exception {
        FtpServerFactory serverFactory = new FtpServerFactory();
        FtpServer server = serverFactory.createServer();
        DbUserManagerFactory userManagerFactory = new DbUserManagerFactory();
        userManagerFactory.setDataSource(dataSource);
        userManagerFactory.setSqlUserInsert("INSERT INTO FTP_USER (userid, userpassword, homedirectory, enableflag, writepermission, idletime, uploadrate, downloadrate) VALUES ('{userid}', '{userpassword}', '{homedirectory}', {enableflag}, {writepermission}, {idletime}, {uploadrate},  {downloadrate})");
        userManagerFactory.setSqlUserUpdate("UPDATE FTP_USER SET userpassword='{userpassword}',homedirectory='{homedirectory}',enableflag={enableflag},writepermission={writepermission},idletime={idletime},uploadrate={uploadrate},downloadrate={downloadrate} WHERE userid='{userid}'");
        userManagerFactory.setSqlUserDelete("DELETE FROM FTP_USER WHERE userid = '{userid}'");
        userManagerFactory.setSqlUserSelect("SELECT userid, userpassword, homedirectory, enableflag, writepermission, idletime, uploadrate, downloadrate, maxloginnumber, maxloginperip FROM FTP_USER WHERE userid = '{userid}'");
        userManagerFactory.setSqlUserSelectAll("SELECT userid FROM FTP_USER ORDER BY userid");
        userManagerFactory.setSqlUserAdmin("SELECT userid FROM FTP_USER WHERE userid='{userid}' AND userid='admin'");
        userManagerFactory.setSqlUserAuthenticate("SELECT userpassword from FTP_USER WHERE userid='{userid}'");
        userManagerFactory.setPasswordEncryptor(new ClearTextPasswordEncryptor());
        serverFactory.setUserManager(userManagerFactory.createUserManager());
        server.start();
    }
}
