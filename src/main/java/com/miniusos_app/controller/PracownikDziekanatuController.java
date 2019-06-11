package com.miniusos_app.controller;

import com.miniusos_app.model.Grupa;
import com.miniusos_app.service.PracownikDziekanatuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class PracownikDziekanatuController {

    @Autowired
    PracownikDziekanatuService pracownikDziekanatuService;

    @RequestMapping(value = "/pracownik_dziekanatu", method = RequestMethod.GET)
    public ModelAndView getPracownikDziekanatuForm() {
        ModelAndView m = new ModelAndView();
        m.setViewName("pracownik_dziekanatu");
        m.addObject("grupy",pracownikDziekanatuService.getAllGroups());
        return m;
    }

    @RequestMapping(value = "/pracownik_dziekanatu", method = RequestMethod.POST)
    public ModelAndView addNewGroup() {
        ModelAndView m = new ModelAndView();
        m.setViewName("pracownik_dziekanatu");
        return m;
    }

    @RequestMapping(value = "/pracownik_dziekanatu", method = RequestMethod.PUT)
    public ModelAndView updateGroup() {
        ModelAndView m = new ModelAndView();
        m.setViewName("pracownik_dziekanatu");
        return m;
    }

    @RequestMapping(value = "/pracownik_dziekanatu", method = RequestMethod.DELETE)
    public ModelAndView deleteGroup(@RequestParam(value = "option") Integer id) {
        pracownikDziekanatuService.deleteFromGroup(id);

        return getPracownikDziekanatuForm();
    }

}
