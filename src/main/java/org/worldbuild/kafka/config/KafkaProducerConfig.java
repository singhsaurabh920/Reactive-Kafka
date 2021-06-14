package org.worldbuild.kafka.config;

import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.worldbuild.kafka.modal.UserDto;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderOptions;

import java.util.HashMap;
import java.util.Map;

@Log4j2
@Profile("ck")
@Configuration
public class KafkaProducerConfig {

    @Value(value = "${kafka.bootstrap.address}")
    private String bootstrapAddress;

    @Bean
    public Map<String, Object> producerConfig() {
        final Map<String, Object> producerConfig = new HashMap<String, Object>();
        producerConfig.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        producerConfig.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        producerConfig.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return producerConfig;
    }

    @Bean("userKafkaSender")
    public KafkaSender<String, UserDto> userKafkaSender() {
        SenderOptions<String, UserDto> senderOptions = SenderOptions.create(producerConfig());
        KafkaSender<String, UserDto> kafkaSender = KafkaSender.create(senderOptions);
        return kafkaSender;
    }
}
