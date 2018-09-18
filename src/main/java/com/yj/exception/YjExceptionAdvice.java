package com.yj.exception;

import com.yj.pojo.ReSult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.HttpStatus.FORBIDDEN;

@ResponseBody
@ControllerAdvice
public class YjExceptionAdvice {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(YjException.class)
    public ReSult handleInvalidInput(YjException ex){
        return ReSult.error(400L,ex.getMessage());
    }

    @ExceptionHandler(value = { NoHandlerFoundException.class })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ReSult handleInvalidInput(NoHandlerFoundException ex){
        return ReSult.error(404L,ex.getMessage());
    }
//
    @ExceptionHandler(value = {AccessDeniedException.class})
    @ResponseStatus(FORBIDDEN)
    public ReSult handleInvalidInput(AccessDeniedException ex){
        return ReSult.error(403L,ex.getMessage());
    }
//
    @ExceptionHandler(Exception.class)
    public ReSult handleInvalidInput( Exception ex){
        return ReSult.error(500L,ex.getMessage());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ReSult handleInvalidInput( BadCredentialsException ex){
        if(ex.getMessage().equals("Bad credentials")){
          return   ReSult.error(500L,"用户名密码错误");
        }
        return ReSult.error(500L,ex.getMessage());
    }





}