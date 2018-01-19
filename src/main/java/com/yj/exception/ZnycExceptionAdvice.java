package com.yj.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ZnycExceptionAdvice {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(ZnycException.class)
    public ResponseEntity<String> handleInvalidInput(ZnycException ex){
        logger.error(ex.getMessage());
        return  ResponseEntity
                .badRequest()
                .body("{\"result\": " + false + ",\"message\": \"" + ex.getMessage() + "\"}");
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<String> handleInvalidInput(Exception  ex){
//        logger.error(ex.getMessage());
//        return  ResponseEntity
//                .badRequest()
//                .body("{\"result\": " + false+ ",\"message\": \"系统错误\"}");
//    }

}