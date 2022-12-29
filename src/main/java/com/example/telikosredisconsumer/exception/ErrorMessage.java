package com.example.telikosredisconsumer.exception;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
public class ErrorMessage {
    private int statusCode;
    private Date timestamp;
    private String message;
}