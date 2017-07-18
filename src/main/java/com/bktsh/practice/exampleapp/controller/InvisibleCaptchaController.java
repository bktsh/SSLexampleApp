package com.bktsh.practice.exampleapp.controller;

import com.bktsh.practice.exampleapp.service.FreemarkerEmailSender;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.StringWriter;

/**
 * Created on 7/11/17.
 */
@Controller
public class InvisibleCaptchaController extends BaseCaptchaController {

    @Autowired
    FreemarkerEmailSender sender;

    @RequestMapping(value = "/invisible", method = RequestMethod.POST)
    public ModelAndView postCommentAgain(HttpServletRequest req, HttpServletResponse resp)
            throws Exception {
        JSONObject jsonObject = performRecaptchaSiteVerify(req.getParameter(G_RECAPTCHA_RESPONSE));
        if(req.getParameter("message") != null){
            if(req.getParameter("message").equalsIgnoreCase("ERROR")) {
                throw new RuntimeException("TEST RuntimeException");
            }else if(req.getParameter("message").equalsIgnoreCase("IO")){
                throw new IOException("TEST IOException");
            }else if(req.getParameter("message").equalsIgnoreCase("EXCEPTION")){
                throw new Exception("TEST EXCEPTION");
            }
        }
        boolean success = jsonObject.getBoolean("success");
        System.out.println("Success = " + success);
        // Name of your jsp file as parameter
        sender.sendFreemarkerEmail("Hashem_Baktash@mycompany.com", "Hashem_Baktash@mycompany.com", "hidden reCaptcha", req.getParameter("message"));
        ModelAndView view = new ModelAndView("invisible");
        view.addObject("message", req.getParameter("message"));
        view.addObject("success", success);
        view.addObject("nameOfApp", nameOfApp);
        return view;
    }

    @ExceptionHandler(RuntimeException.class)
    public ModelAndView handleRuntimeException(HttpServletRequest request, RuntimeException ex){
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
