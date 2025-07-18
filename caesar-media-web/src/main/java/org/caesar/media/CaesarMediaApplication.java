package org.caesar.media;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author peng.guo
 */
@SpringBootApplication
@EnableScheduling
@EnableRetry
@ConfigurationPropertiesScan
public class CaesarMediaApplication {

    public static void main(String[] args) {
        SpringApplication.run(CaesarMediaApplication.class, args);
    }

}
