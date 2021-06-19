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
@ConfigurationProperties(prefix = "server.protocol")
@PropertySource(value = {"classpath:server-${server.config}-config.yml"},factory = YamlPropertySourceFactory.class)
public class ServerConfig {

    @Value("${server.config}")
    private String serverConfig;

    private String name;
    private int port;


    @PostConstruct
    private void init(){
        log.info("---------------------------------------");
        log.info(serverConfig+" ==> Name {} Age {}",this.name,this.port);
        log.info("---------------------------------------");
    }
}
