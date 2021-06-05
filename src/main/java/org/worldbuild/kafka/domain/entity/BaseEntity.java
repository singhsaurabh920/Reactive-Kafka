package org.worldbuild.kafka.domain.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Data
public class BaseEntity {

    @Id
    private String id;
    @Field("a_ed")
    private Date added;
    @Field("m_ed")
    private Date updated;
}
