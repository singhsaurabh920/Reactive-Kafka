/*
package com.starter.kafka.service;

import UserDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {
    private static final Logger logger = LogManager.getLogger(KafkaProducerService.class);

    @KafkaListener(topics ="TEXTTOPIC" , containerFactory="kafkaListenerContainerFactory", groupId = "group-id1")
    public void consumeString(String msg){
        logger.info("RECEVIED GROUP_ID1 - " + msg );
    }

    @KafkaListener(topics ="USERTOPIC" , containerFactory="userKafkaListenerContainerFactory", groupId = "group-id2")
    public void consumeUser(UserDto user){
        logger.info("RECEVIED GROUP-ID2- " + user );
    }
}
*/
