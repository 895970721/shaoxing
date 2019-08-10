package com.nhxy.sxs.demo.config;

import com.maxmind.geoip2.DatabaseReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;

/**
 * <p>Class: IpDatabaseConfig</p>
 *
 * @author GodDai
 * @version 1.0.0
 * @since 2019/8/10 22:31
 */
@Configuration
public class IpDatabaseConfig {

    @Value("${systemParam.ip-database-path}")
    private String path;

    @Bean(destroyMethod = "close")
    public DatabaseReader databaseReader() {
        try {
            return new DatabaseReader.Builder(new File(path)).build();
        } catch (IOException e) {
            System.out.println("IPDatabase error:" + e.getMessage());
            return null;
        }
    }
}
