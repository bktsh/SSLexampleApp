package com.bktsh.practice.exampleapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created on 2017-Feb-21
 */
@Controller
public class HomeController {

    @RequestMapping("/hello")
    public ModelAndView welcomeMessage(@RequestParam(value = "name", required = false) String name) {
        // Name of your jsp file as parameter
        ModelAndView view = new ModelAndView("hello");
        view.addObject("name", name);
        return view;
    }
}
