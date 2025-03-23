package com.gualteros.weaponsStore.utils;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BaseApiResponse {

    private String message;
    private Object data;
    private String error;
    private Integer statusCode;

    public static BaseApiResponse success(String message, Object data) {
        return BaseApiResponse.builder()
                .message(message)
                .data(data)
                .error("n/a")
                .statusCode(HttpStatus.OK.value())
                .build();
    }

    public static BaseApiResponse error(HttpStatus status, String errorMessage) {
        return BaseApiResponse.builder()
                .statusCode(status.value())
                .error(status.getReasonPhrase())
                .message(errorMessage)
                .build();
    }

}
