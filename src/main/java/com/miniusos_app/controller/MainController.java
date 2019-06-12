package com.miniusos_app.controller;

import com.miniusos_app.service.Registration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;



@RestController
public class MainController {

    @Autowired
    Registration registration;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getHomePage() {
        ModelAndView m = new ModelAndView();
        m.setViewName("hello");
        return m;
    }

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public ModelAndView getHello(){
        ModelAndView m = new ModelAndView();
        m.setViewName("hello");
        return m;
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public ModelAndView getLoginForm(){
        ModelAndView m = new ModelAndView();
        m.setViewName("login");
        return m;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView getRegistrationForm(){
        ModelAndView m = new ModelAndView();
        m.setViewName("registration");
        return m;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String Register(@RequestParam(value = "account-type",required = true) String accountType,
                           @RequestParam(value = "name",required = true) String name,
                           @RequestParam(value = "last-name",required = true) String lastName,
                           @RequestParam(value = "password",required = true) String password,
                           @RequestParam(value = "password-repeat",required = true) String passwordRepeat){

        int returnedValue;

        if(password.equals(passwordRepeat))
           returnedValue = registration.register(accountType,name,lastName,password);
        else
            return "passwords are not the same";

        if(returnedValue==2)
            return "account already exists";
        else
            return "everything is allright your index: "+returnedValue;
    }

}