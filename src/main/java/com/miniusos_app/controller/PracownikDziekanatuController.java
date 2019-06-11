package com.miniusos_app.controller;

import com.miniusos_app.model.DZIEN_TYGODNIA;
import com.miniusos_app.model.Grupa;
import com.miniusos_app.model.Koordynator;
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
        m.setViewName("grupaFormularz");
        return m;
    }


    ////TODO
    @RequestMapping(value = "/pracownik_dziekanatu/putordelete", method = RequestMethod.POST)
    public ModelAndView redirectToProperMethod(@RequestParam(value = "chosenButton") String chosenButton) {
        if(chosenButton.equals("edytuj zaznaczoną grupę"))
            return updateGroup();
        else if(chosenButton.equals("usuń zaznaczoną grupę"))
                return deleteGroup(1);

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
    public ModelAndView deleteGroup(@RequestParam(value = "option") Integer id_grupy) {
        pracownikDziekanatuService.deleteFromGroup(id_grupy);

        return getPracownikDziekanatuForm();
    }

    @RequestMapping(value = "/pracownik_dziekanatu/nowa_grupa", method = RequestMethod.POST)
    public ModelAndView newGroup(@RequestParam(value = "nazwa") String nazwaGrupy,
                                 @RequestParam(value = "id") Integer id,
                                 @RequestParam(value = "dzien_tygodnia") DZIEN_TYGODNIA dzienTygodnia,
                                 @RequestParam(value = "godzina_rozpoczecia") String godzinaRozpoczecia,
                                 @RequestParam(value = "godzina_zakonczenia") String godzinaZakonczenia,
                                 @RequestParam(value = "limit_miejsc") Integer limitMiejsc,
                                 @RequestParam(value = "imie_koordynatora") String imieKoordynatora,
                                 @RequestParam(value = "nazwisko_koordynatora") String nazwiskoKoordynatora) {

        Grupa g = new Grupa(nazwaGrupy,id,dzienTygodnia,
                godzinaRozpoczecia,godzinaZakonczenia,limitMiejsc,
                new Koordynator(imieKoordynatora,nazwiskoKoordynatora));

        pracownikDziekanatuService.addGroup(g);
        //processing new group

        return getPracownikDziekanatuForm();
    }

}
