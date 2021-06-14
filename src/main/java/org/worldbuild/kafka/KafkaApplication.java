package org.worldbuild.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.stream.Stream;

@Slf4j
@SpringBootApplication
@EnableReactiveMongoRepositories(basePackages = "org.worldbuild.kafka.domain.repository")
public class KafkaApplication implements ApplicationRunner {

	@Autowired
	private StandardEnvironment env;

	public static void main(String[] args) {
		log.info("-------------- CMD ARGS ------------------");
		Stream.of(args).forEach(log::info);
		log.info("------------------------------------------");
		SpringApplication.run(KafkaApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		log.info("------------- SystemEnvironment -----------------");
		env.getSystemEnvironment().forEach((k,v)->{
			log.info(k +" --> "+v);
		});
		log.info("-------------------------------------------------");
		log.info("-------------- SystemProperties -----------------");
		env.getSystemProperties().forEach((k,v)->{
			log.info(k +" --> "+v);
		});
		log.info("-------------------------------------------------");
	}
}
