package com.changhong.devcenter.minaftp.config;

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

    }
}
