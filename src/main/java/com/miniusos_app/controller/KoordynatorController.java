package com.miniusos_app.controller;

import com.miniusos_app.service.KoordynatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class KoordynatorController {

    @Autowired
    KoordynatorService koordynatorService;

    @RequestMapping(value = "/koordynator", method = RequestMethod.GET)
    public ModelAndView getKoordynatorForm() {
        ModelAndView m = new ModelAndView();
        m.setViewName("koordynator");
        return m;
    }

}
