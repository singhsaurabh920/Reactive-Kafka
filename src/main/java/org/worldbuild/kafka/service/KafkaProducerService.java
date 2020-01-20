package org.worldbuild.kafka.service;

import org.worldbuild.kafka.modal.UserDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {
    private static final Logger logger = LogManager.getLogger(KafkaProducerService.class);
    public static final  String USER_TOPIC="USERTOPIC";

   // @Autowired
    //private KafkaTemplate kafkaTemplate;

    public void produceUser(UserDto userDto) {
            /*ListenableFuture<SendResult<String, UserDto>> future = kafkaTemplate.send(USER_TOPIC, userDto);
            future.addCallback( new ListenableFutureCallback<SendResult<String, UserDto>>() {
                @Override
                public void onSuccess(SendResult<String, UserDto> userSendResult) {
                    logger.info("SENT - " + userDto);
                }
                @Override
                public void onFailure(Throwable throwable) {
                    logger.info("SENT " + userDto + " FAILED DUE TO : " + throwable.getMessage());
                }
            });*/
    }

}
