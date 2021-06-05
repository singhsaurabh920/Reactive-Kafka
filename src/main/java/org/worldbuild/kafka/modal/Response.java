package org.worldbuild.kafka.modal;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {

    private HttpStatus status=HttpStatus.OK;
    private int code=HttpStatus.OK.value();
    private String msg;
    private T data;

    public Response(T data) {
        this.data = data;
    }

    public Response(HttpStatus status,String msg) {
        this.status = status;
        this.code=status.value();
        this.msg=msg;
    }
}
