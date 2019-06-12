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
    public ModelAndView getMarkStudentForm(@RequestParam(value = "choice") String choice) {

        ModelAndView m = new ModelAndView();

        String id_studentaSTR = choice.substring(0,choice.indexOf('!'));
        String id_grupySTR = choice.substring(choice.indexOf('!')+1);
        Integer id_studenta;
        Integer id_grupy;

        try{
            id_studenta = Integer.parseInt(id_studentaSTR);
            id_grupy = Integer.parseInt(id_grupySTR);
        }
        catch (NumberFormatException ex){
          ex.printStackTrace();
          m.setViewName("hello");
          return m;
        };

        Student student = koordynatorService.getStudentByID(id_studenta);
        m.setViewName("ocena");
        m.addObject("student",koordynatorService.getStudentByID(id_studenta));
        m.addObject("id_group",id_grupy);
        return m;
    }

    @RequestMapping(value = "/koordynator/wystaw_oceny", method = RequestMethod.PUT)
    public ModelAndView markStudent(@RequestParam(value = "id_grupy1") Integer id_grupy,
                                    @RequestParam(value = "numer_USOS") Integer numerUSOS,
                                    @RequestParam(value = "kolokwium_1") Integer kolokwium_1,
                                    @RequestParam(value = "kolokwium_2") Integer kolokwium_2,
                                    @RequestParam(value = "ocena_koncowa") Double ocenaKoncowa) {

        koordynatorService.wystawOcene(id_grupy,numerUSOS,ocenaKoncowa,kolokwium_1,kolokwium_2);

        return getKoordynatorForm();
    }

}
