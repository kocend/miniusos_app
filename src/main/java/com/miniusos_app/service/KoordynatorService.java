package com.miniusos_app.service;

import com.miniusos_app.model.*;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class KoordynatorService {

    //fake data
    List<GrupaStudent> listaGrup_I_studentow;

    public KoordynatorService(){
        listaGrup_I_studentow = new LinkedList<>();

        listaGrup_I_studentow.add(new GrupaStudent("PWJ",1,"Konrad",
                "CEndrowski",8273));
        listaGrup_I_studentow.add(new GrupaStudent("PWc",1,"hsdacb",
                "gber",83473));
        listaGrup_I_studentow.add(new GrupaStudent("PWp",1,"lvkpdz",
                "addca",273));
        listaGrup_I_studentow.add(new GrupaStudent("PWw",1,"Konrfoqjo38fad",
                "ecvw",8223));
        listaGrup_I_studentow.add(new GrupaStudent("PWb",1,"Klvkpeonrad",
                "neneber",4773));
        listaGrup_I_studentow.add(new GrupaStudent("PWC++",1,"Kov-3omovk0inrad",
                "afas",5280));
        listaGrup_I_studentow.add(new GrupaStudent("PWA",1,"zijov",
                "bwrbgr",3273));
    }

    public List<GrupaStudent> getAllStudents(Integer id_koordynatora) {
        return listaGrup_I_studentow;
    }
}
