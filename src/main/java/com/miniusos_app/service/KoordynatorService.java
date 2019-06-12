package com.miniusos_app.service;

import com.miniusos_app.dao.dataBaseServiceInterface;
import com.miniusos_app.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class KoordynatorService {

    private final dataBaseServiceInterface dbInterface;

    @Autowired
    public KoordynatorService(@Qualifier("mysqlDB") dataBaseServiceInterface db){
        dbInterface=db;
    }

    public Student getStudentByID(Integer id){
        return dbInterface.pobierzStudentaPoID(id);
    }

    public int wystawOcene(Integer id_grupy, Integer id_studenta,
                           Double ocenaKoncowa,
                           Integer kolokwiumI, Integer kolokwiumII){
        dbInterface.wystawOcene(id_grupy,id_studenta,ocenaKoncowa,kolokwiumI,kolokwiumII);

        return 1;
    }

    public Wyniki getStudentsMarks(Student s, String nazwaGrupy){
        List<Wyniki> wynikiList = getAllStudentsMarks(s);
        Wyniki wyniki = null;
        for (Wyniki w:wynikiList) {
            if(w.getNazwaGrupy().equals(nazwaGrupy))
                wyniki=w;
        }
        return wyniki;
    }

    public List<Wyniki> getAllStudentsMarks(Student s){

        return dbInterface.pobierzMojeWyniki(s);
    }

    public Grupa getGroupByID(Integer id_grupy){
        return dbInterface.pobierzGrupePoID(id_grupy);
    }


    public int magic5(Integer id_koordynatora){
        return dbInterface.magicznyGuzikWystaw5tymCoNieMajaOceny(id_koordynatora);
    }

    public List<GrupaStudent> getAllStudents(Integer id_koordynatora) {
        return dbInterface.pobierzGrupyIStudentowKoordynatora(id_koordynatora);
    }

}
