package com.bktsh.practice.exampleapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created on 2017-Feb-21
 */
@Controller
public class HomeController extends BaseCaptchaController {

    @RequestMapping(value = "/showVisible", method = RequestMethod.GET)
    public ModelAndView visible(@RequestParam(value = "name", required = false) String name) {
        ModelAndView view = new ModelAndView("visible");
        view.addObject("name", name);
        return view;
    }


    @RequestMapping(value = "/showInvisible", method = RequestMethod.GET)
    public ModelAndView invisible(@RequestParam(value = "name", required = false) String name) {
        ModelAndView view = new ModelAndView("invisible");
        view.addObject("name", name);
        return view;
    }
}
