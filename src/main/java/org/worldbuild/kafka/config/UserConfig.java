package org.worldbuild.kafka.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;

@Data
@Slf4j
@Configuration
@ConfigurationProperties(prefix = "spring.user")
@PropertySource(value = {"classpath:user-${user.no}.yml"},factory = YamlPropertySourceFactory.class)
public class UserConfig {

    @Value("${user.no}")
    private String userNo;

    private String name;
    private int age;


    @PostConstruct
    private void init(){
        log.info("---------------------------------------");
        log.info(userNo+" ==> Name {} Age {}",this.name,this.age);
        log.info("---------------------------------------");
    }
}
