package org.worldbuild.kafka.domain.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@Document(collection = "user")
@TypeAlias("User")
public class User extends BaseEntity {

    @Field
    private String name;
    @Field
    private String age;
    @Field
    private String username;
    @Field
    private String password;
    @Field
    private String address;
    @Field
    private boolean enable;

}
