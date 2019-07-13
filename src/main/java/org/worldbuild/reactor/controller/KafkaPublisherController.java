package org.worldbuild.reactor.controller;

import org.worldbuild.reactor.modal.Response;
import org.worldbuild.reactor.modal.UserDto;
import org.worldbuild.reactor.service.KafkaProducerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/kafka/publish")
public class KafkaPublisherController {
    private static final Logger logger = LogManager.getLogger(KafkaPublisherController.class);

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @RequestMapping(value = "/user",method = RequestMethod.POST)
    public Response<String> userProduceHandler(@RequestBody UserDto userDto){
        logger.info("UserDto Request - "+userDto);
        kafkaProducerService.produceUser(userDto);
        return  new Response<>("User submitted");
    }

}
