package com.bktsh.practice.exampleapp.controller;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created on 7/11/17.
 */
@Controller
public class VisibleCaptchaController extends BaseCaptchaController {

    @RequestMapping(value = "/visible", method = RequestMethod.POST)
    public ModelAndView postComment(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        JSONObject jsonObject = performRecaptchaSiteVerify(req.getParameter(G_RECAPTCHA_RESPONSE));
        boolean success = jsonObject.getBoolean("success");
        System.out.println("Success = " + success);
        // Name of your jsp file as parameter
        ModelAndView view = new ModelAndView("visible");
        view.addObject("message", req.getParameter("message"));
        view.addObject("success", success);
        view.addObject("nameOfApp", nameOfApp);
        return view;
    }

}
