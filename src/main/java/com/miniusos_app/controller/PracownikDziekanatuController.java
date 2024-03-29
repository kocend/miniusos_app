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
        m.addObject("numer_id",-2);
        m.addObject("koordynatorzy",pracownikDziekanatuService.getAllMasters());
        return m;
    }

    @RequestMapping(value = "/pracownik_dziekanatu/get_update", method = RequestMethod.GET)
    public ModelAndView updateGroup(@RequestParam(value = "option") Integer id_grupy) {
        ModelAndView m = new ModelAndView();
        m.setViewName("grupaFormularz");
        Grupa g = pracownikDziekanatuService.getGroupByID(id_grupy);
        if(g==null){
            return getPracownikDziekanatuForm();
        }
        m.addObject("nazwa",g.getNazwaGrupy());
        m.addObject("numer_id",g.getId_grupy());
        m.addObject("dzien",g.getDzienTygodnia());
        m.addObject("godzina_rozpoczecia",g.getGodzinaRozpoczecia());
        m.addObject("godzina_zakonczenia",g.getGodzinaZakonczenia());
        m.addObject("limit_miejsc",g.getLimitMiejsc());
        m.addObject("id_k",g.getKoordynator().getId());
        m.addObject("imie_koordynatora",g.getKoordynator().getImie());
        m.addObject("nazwisko_koordynatora",g.getKoordynator().getNazwisko());
        m.addObject("koordynatorzy",pracownikDziekanatuService.getAllMasters());
        return m;
    }

    @RequestMapping(value = "/pracownik_dziekanatu", method = RequestMethod.DELETE)
    public ModelAndView deleteGroup(@RequestParam(value = "option") Integer id_grupy) {
        pracownikDziekanatuService.deleteFromGroup(id_grupy);

        return getPracownikDziekanatuForm();
    }

    @RequestMapping(value = "/pracownik_dziekanatu/grupa", method = RequestMethod.POST)
    public ModelAndView newGroup(@RequestParam(value = "nazwa") String nazwaGrupy,
                                 @RequestParam(value = "id") Integer id,
                                 @RequestParam(value = "dzien_tygodnia") DZIEN_TYGODNIA dzienTygodnia,
                                 @RequestParam(value = "godzina_rozpoczecia") String godzinaRozpoczecia,
                                 @RequestParam(value = "godzina_zakonczenia") String godzinaZakonczenia,
                                 @RequestParam(value = "limit_miejsc") Integer limitMiejsc,
                                 @RequestParam(value = "id_k") Integer id_k,
                                 @RequestParam(value = "imie_koordynatora") String imieKoordynatora,
                                 @RequestParam(value = "nazwisko_koordynatora") String nazwiskoKoordynatora) {

       if(id==-2) {
           //tworz nową
           int idOfLastGroup = pracownikDziekanatuService.getIdOfLastGroup();
           if (idOfLastGroup == -1)
               return addNewGroup();
           Grupa g = new Grupa(nazwaGrupy, idOfLastGroup + 1, dzienTygodnia,
                   godzinaRozpoczecia, godzinaZakonczenia, limitMiejsc,
                   new Koordynator(imieKoordynatora, nazwiskoKoordynatora, id_k));

           if (pracownikDziekanatuService.addGroup(g) == 5)
               return addNewGroup();
       }
       else{
            //zaktualizuj istniejącą
            pracownikDziekanatuService.updateGroupByID(id,new Grupa(nazwaGrupy, id, dzienTygodnia,
                    godzinaRozpoczecia, godzinaZakonczenia, limitMiejsc,
                    new Koordynator(imieKoordynatora, nazwiskoKoordynatora,id_k)));
        }

        return getPracownikDziekanatuForm();
    }

}
