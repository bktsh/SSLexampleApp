package com.bktsh.practice.exampleapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InvalidClassException;

/**
 * Created on 7/18/17.
 */
@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(HttpServletRequest request, Exception ex){
        System.out.println("RuntimeException Occured:: URL="+request.getRequestURL());
        ModelAndView modelAndView =  new ModelAndView("generic_error");
        modelAndView.addObject("url", request.getRequestURL());
        modelAndView.addObject("exception", ex);
        return modelAndView;
    }

    @ResponseStatus(value= HttpStatus.NOT_FOUND, reason="IOException occurred")
    @ExceptionHandler(IOException.class)
    public void handleIOException(){
        System.out.println("IOException handler executed");
    }
}
