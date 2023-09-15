package com.springboot.springsecurity.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@Builder
@Getter
@ToString
public class ResponseModel<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private String message;
    private T data;
}
