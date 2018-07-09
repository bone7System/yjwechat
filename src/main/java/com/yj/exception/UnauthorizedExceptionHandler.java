package com.yj.exception;

import org.json.JSONObject;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

/**
 * Created by koh on 2016/7/7.
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class UnauthorizedExceptionHandler {

    @ResponseStatus(UNAUTHORIZED)
    @ResponseBody
    @ExceptionHandler(BadCredentialsException.class)
    public String methodSQLIntegrityConstraintViolationException(BadCredentialsException ex) {
        JSONObject jo = new JSONObject();
        try {
            jo.put("status", UNAUTHORIZED.value());
            jo.put("message", "密码错误");
        }catch (Exception e) {}

        return jo.toString();
    }


    @ResponseStatus(FORBIDDEN)
    @ResponseBody
    @ExceptionHandler(AccessDeniedException.class)
    public String methodSQLIntegrityConstraintViolationException(AccessDeniedException ex) {
        JSONObject jo = new JSONObject();
        try {
            jo.put("status", FORBIDDEN.value());
            jo.put("message", "权限不足！");
        }catch (Exception e) {}
        return jo.toString();
    }


}