package org.worldbuild.kafka.modal;

import lombok.Data;

@Data
public class UserDto {

    private String username;
    private String  email;
    private long salary;
}
