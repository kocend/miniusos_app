package com.miniusos_app.controller;

import com.miniusos_app.model.Grupa;
import com.miniusos_app.model.Student;
import com.miniusos_app.model.Wyniki;
import com.miniusos_app.service.KoordynatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
public class KoordynatorController {

    @Autowired
    KoordynatorService koordynatorService;

    @RequestMapping(value = "/koordynator", method = RequestMethod.GET)
    public ModelAndView getKoordynatorForm(HttpServletRequest request) {

        ModelAndView m = new ModelAndView();
        m.setViewName("koordynator");
        String user = request.getUserPrincipal().getName();

        m.addObject("lista_grup",koordynatorService.getAllStudents( Integer.parseInt(user)));
        return m;
    }

    @RequestMapping(value = "/koordynator/magiczne5", method = RequestMethod.PUT)
    public ModelAndView magic5(HttpServletRequest request) {
        String user = request.getUserPrincipal().getName();
        //pobranie id_koordynatora
        koordynatorService.magic5(Integer.parseInt(user));

        return getKoordynatorForm(request);
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

        Grupa g = koordynatorService.getGroupByID(id_grupy);
        Student student = koordynatorService.getStudentByID(id_studenta);
        Wyniki wyniki = koordynatorService.getStudentsMarks(student,g.getNazwaGrupy());
        m.setViewName("ocena");

        m.addObject("student",koordynatorService.getStudentByID(id_studenta));
        m.addObject("id_group",id_grupy);
        m.addObject("wyniki",wyniki);
        return m;
    }

    @RequestMapping(value = "/koordynator/wystaw_oceny", method = RequestMethod.PUT)
    public ModelAndView markStudent(HttpServletRequest request,
                                    @RequestParam(value = "id_grupy1") Integer id_grupy,
                                    @RequestParam(value = "numer_USOS") Integer numerUSOS,
                                    @RequestParam(value = "kolokwium_1") Integer kolokwium_1,
                                    @RequestParam(value = "kolokwium_2") Integer kolokwium_2,
                                    @RequestParam(value = "ocena_koncowa") Double ocenaKoncowa) {

        koordynatorService.wystawOcene(id_grupy,numerUSOS,ocenaKoncowa,kolokwium_1,kolokwium_2);

        return getKoordynatorForm(request);
    }

}
