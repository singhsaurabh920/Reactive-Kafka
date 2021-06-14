package org.worldbuild.kafka.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.worldbuild.kafka.modal.UserDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.worldbuild.kafka.service.KafkaConsumerService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.kafka.receiver.ReceiverRecord;

@Log4j2
@Profile("ck")
@RestController
@RequestMapping("/kafka/suscribe")
public class KafkaSubscriberController {

    @Autowired
    private KafkaConsumerService kafkaConsumerService;

    @RequestMapping(value = "/user",method = RequestMethod.GET,produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<UserDto> userSubscriberHandler(){
        return kafkaConsumerService.consumeUser();
    }
}
