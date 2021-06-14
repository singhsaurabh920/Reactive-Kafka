package org.worldbuild.kafka.service;

import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.worldbuild.kafka.constnat.KafkaConstant;
import org.worldbuild.kafka.modal.UserDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderRecord;
import reactor.kafka.sender.SenderResult;

import java.time.Instant;
import java.util.Date;

@Log4j2
@Profile("ck")
@Component
public class KafkaProducerService {

    @Autowired
    @Qualifier("userKafkaSender")
    private KafkaSender kafkaSender;

    public Flux<SenderResult<?>> produceUser(UserDto userDto) {
        Flux<SenderResult<?>> flux = kafkaSender.send(Mono.just(SenderRecord.create(KafkaConstant.User.TOPIC, 0, Date.from(Instant.EPOCH).getTime(), userDto.getUsername(), userDto, null)));
        return flux.doOnNext((sr) -> {
            log.info(sr);
        }).doOnError((e) -> {
            log.error("SENT FAILED : ", e);
        });
    }


}
