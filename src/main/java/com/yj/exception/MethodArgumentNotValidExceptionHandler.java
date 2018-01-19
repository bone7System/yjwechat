package com.yj.exception;

import org.json.JSONObject;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * Created by koh on 2016/7/7.
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class MethodArgumentNotValidExceptionHandler {

    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        return processFieldErrors(fieldErrors);
    }

    private String processFieldErrors(List<FieldError> fieldErrors) {
        JSONObject jo = new JSONObject();
        try {
            jo.put("message", "validation error");
            JSONObject fields = new JSONObject();
            jo.put("fields", fields);

            for (FieldError fieldError: fieldErrors) {
                fields.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
        } catch (Exception e) {}
        return jo.toString();
    }
}