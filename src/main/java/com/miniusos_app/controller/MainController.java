package com.miniusos_app.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class MainController {
    @GetMapping("/")
    public ModelAndView getMain() {
        ModelAndView m = new ModelAndView();
        m.setViewName("login");
        return m;
    }

    @PostMapping("/login")
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