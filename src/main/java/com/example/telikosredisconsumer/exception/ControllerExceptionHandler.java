package com.example.telikosredisconsumer.exception;

import com.example.telikosredislibrary.CacheException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler {
    @ExceptionHandler(value = {CacheException.class})
    public ResponseEntity<ErrorMessage> resourceNotFoundException(CacheException ex) {
        log.info("Exception in ControllerExceptionHandler");
        ErrorMessage message = ErrorMessage.builder().statusCode(HttpStatus.NOT_FOUND.value()).timestamp(new Date()).message(ex.getMessage()).build();
        return new ResponseEntity<ErrorMessage>(message, HttpStatus.NOT_FOUND);
    }

}