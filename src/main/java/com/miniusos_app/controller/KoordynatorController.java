package com.miniusos_app.controller;

import com.miniusos_app.model.Student;
import com.miniusos_app.service.KoordynatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
        //jak zdobyć id zalogowanego koordynatora
        m.addObject("lista_grup",koordynatorService.getAllStudents( 1));
        return m;
    }

    @RequestMapping(value = "/koordynator/magiczne5", method = RequestMethod.POST)
    public ModelAndView magic5() {

        ModelAndView m = new ModelAndView();
        m.setViewName("koordynator");
        //jak zdobyć id zalogowanego koordynatora
        m.addObject("lista_grup",koordynatorService.getAllStudents( 1));
        return m;
    }

    @RequestMapping(value = "/koordynator/wystaw_oceny", method = RequestMethod.GET)
    public ModelAndView markStudent(@RequestParam(value = "choice")Integer id_studenta) {

        Student student = koordynatorService.getStudentByID(id_studenta);
        ModelAndView m = new ModelAndView();
        m.setViewName("ocena");
        m.addObject("student",koordynatorService.getStudentByID(id_studenta));
        return m;
    }

}
