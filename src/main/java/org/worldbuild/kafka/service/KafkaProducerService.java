package org.worldbuild.kafka.service;

import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.worldbuild.kafka.constnat.KafkaConstant;
import org.worldbuild.kafka.modal.UserDto;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderRecord;
import reactor.kafka.sender.SenderResult;

import java.time.Instant;
import java.util.Date;

@Log4j2
@Service
public class KafkaProducerService {

    @Autowired
    @Qualifier("userKafkaSender")
    private KafkaSender kafkaSender;

    public void produceUser(UserDto userDto) {
        Flux<SenderResult<?>> flux = kafkaSender.send(Mono.just(SenderRecord.create(KafkaConstant.User.TOPIC, 0, Date.from(Instant.EPOCH).getTime(), null, userDto, null)));
        flux.doOnNext((sr) -> {
            RecordMetadata meta = sr.recordMetadata();
            log.info("SENT SUCCESSFULL : ####### " + meta.offset() + " ######");
        }).doOnError((e) -> {
            log.error("SENT FAILED : ", e);
        }).subscribe();
    }


}
