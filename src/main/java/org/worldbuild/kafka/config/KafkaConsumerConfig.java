package org.worldbuild.kafka.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.worldbuild.kafka.constnat.KafkaConstant;
import org.worldbuild.kafka.modal.UserDto;
import reactor.core.publisher.EmitterProcessor;
import reactor.core.publisher.Flux;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;
import reactor.kafka.receiver.ReceiverRecord;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Profile("ck")
@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    @Value(value = "${kafka.bootstrap.address}")
    private String bootstrapAddress;


    @Bean("userReceiverOptions")
    public ReceiverOptions<String, UserDto> userReceiverOptions() {
        Map<String, Object> consumerConfig = new HashMap<>();
        consumerConfig.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        consumerConfig.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        consumerConfig.put(ConsumerConfig.GROUP_ID_CONFIG, KafkaConstant.User.CG);
        consumerConfig.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, KafkaConstant.User.OFFSET);
        consumerConfig.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        consumerConfig.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        consumerConfig.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        ReceiverOptions<String, UserDto> receiverOptions = ReceiverOptions.create(consumerConfig);
        receiverOptions.commitBatchSize(1);
        receiverOptions.closeTimeout(Duration.ofSeconds(5));
        receiverOptions.commitInterval(Duration.ofSeconds(1));
        receiverOptions.subscription(Collections.singleton(KafkaConstant.User.TOPIC));
        receiverOptions.addAssignListener(partitions -> partitions.forEach(p -> p.seekToEnd()));
        receiverOptions.addRevokeListener(partitions -> partitions.forEach(p -> p.seekToEnd()));
        return receiverOptions;
    }

    @Bean("userKafkaReceiver")
    public Flux<ReceiverRecord<String, UserDto>> userKafkaReceiver() {
        KafkaReceiver<String, UserDto> kafkaReceiver = KafkaReceiver.create(userReceiverOptions());
        Flux<ReceiverRecord<String, UserDto>> kafkaFlux = kafkaReceiver.receive()
                .subscribeWith(EmitterProcessor.create(false)).doOnSubscribe(ss-> log.info("Subscribe ==> "+ss.toString()));
        return kafkaFlux;
    }

}
