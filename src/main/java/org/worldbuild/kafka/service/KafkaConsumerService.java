package org.worldbuild.kafka.service;

import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.worldbuild.kafka.constnat.KafkaConstant;
import org.worldbuild.kafka.modal.UserDto;
import reactor.core.publisher.Flux;
import reactor.kafka.receiver.ReceiverRecord;

@Log4j2
@Component
public class KafkaConsumerService {

    @Autowired
    @Qualifier("userKafkaReciver")
    public Flux<ReceiverRecord<String, UserDto>> userKafkaReciver;

    public Flux<UserDto> consumeUser() {
        return userKafkaReciver.map(receiverRecord -> {
            log.info(receiverRecord);
            return receiverRecord.value();
        });
    }
}
