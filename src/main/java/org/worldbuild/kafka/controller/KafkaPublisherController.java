package org.worldbuild.kafka.controller;

import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.context.annotation.Profile;
import org.worldbuild.kafka.modal.Response;
import org.worldbuild.kafka.service.KafkaProducerService;
import org.worldbuild.kafka.modal.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.kafka.sender.SenderResult;


@Log4j2
@Profile("ck")
@RestController
@RequestMapping("/kafka/publish")
public class KafkaPublisherController {
    @Autowired
    private KafkaProducerService kafkaProducerService;

    @RequestMapping(value = "/user",method = RequestMethod.POST)
    public Flux<Response<?>> userProduceHandler(@RequestBody UserDto userDto){
        log.info("UserDto Request - "+userDto);
        return kafkaProducerService.produceUser(userDto).map(senderResult -> new Response<>(senderResult.correlationMetadata()));
    }

}
