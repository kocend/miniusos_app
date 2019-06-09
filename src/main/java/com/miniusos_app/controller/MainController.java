package com.miniusos_app.controller;


import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping(value = "")
@RestController
public class MainController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getMain() {
        ModelAndView m = new ModelAndView();
        m.setViewName("login");
        return m;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView loggedIn(@RequestParam(value = "username",required = true) String username,
                                 @RequestParam(value = "password",required = true) String password){
        //@RequestParam(value = "option",required = true)String option,
        ModelAndView m = new ModelAndView();
        if("kocend".equals(username)&&"abc".equals(password))
            m.setViewName("hello");
        else
            m.setViewName("login");
        return m;
    }
}