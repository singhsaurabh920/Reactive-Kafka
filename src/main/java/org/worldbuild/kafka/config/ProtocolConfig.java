package org.worldbuild.kafka.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Getter
@Setter
@Configuration
@PropertySource(value = {"classpath:protocol.yml"},factory = YamlPropertySourceFactory.class)
@ConfigurationProperties(prefix = "tcp")
public class ProtocolConfig {

    private Map<String, Protocol> protocols = new HashMap<String, Protocol>();

    @Getter
    @Setter
    @ToString
    public static class Protocol {
        private boolean active;
        //private List<Integer> ports;
        private int timeout;
    }

    @PostConstruct
    private void init(){
        log.info("-------------- PROTOCOLS ------------------");
        protocols.forEach((k,v)->{
            log.info(k+" ==> "+v);
        });
        log.info("-------------------------------------------");
    }
}
