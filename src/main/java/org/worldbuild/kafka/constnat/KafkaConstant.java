package org.worldbuild.kafka.constnat;

public interface KafkaConstant {

    interface Text{
        String CG = "tg";
        String OFFSET = "earliest";
        String TOPIC = "TEXTTOPIC";
    }

    interface User{
        String CG = "ug";
        String OFFSET = "earliest";
        String TOPIC = "USERTOPIC";
    }
}
