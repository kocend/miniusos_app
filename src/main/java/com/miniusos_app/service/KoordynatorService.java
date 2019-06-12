package com.miniusos_app.service;

import com.miniusos_app.model.*;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class KoordynatorService {

    //fake data
    List<GrupaStudent> listaGrup_I_studentow;
    List<Student> listaStudentow;

    public KoordynatorService(){
        listaGrup_I_studentow = new LinkedList<>();

        listaGrup_I_studentow.add(new GrupaStudent("PWJ",1,"Konrad",
                "CEndrowski",1));
        listaGrup_I_studentow.add(new GrupaStudent("PWc",2,"hsdacb",
                "gber",2));
        listaGrup_I_studentow.add(new GrupaStudent("PWp",3,"lvkpdz",
                "addca",3));
        listaGrup_I_studentow.add(new GrupaStudent("PWw",4,"Konrfoqjo38fad",
                "ecvw",4));
        listaGrup_I_studentow.add(new GrupaStudent("PWb",5,"Klvkpeonrad",
                "neneber",5));
        listaGrup_I_studentow.add(new GrupaStudent("PWC++",6,"Kov-3omovk0inrad",
                "afas",6));
        listaGrup_I_studentow.add(new GrupaStudent("PWA",7,"zijov",
                "bwrbgr",7));

        listaStudentow = new LinkedList<>();
        listaStudentow.add(new Student("Konrad",
                "CEndrowski",1));
        listaStudentow.add(new Student("Konrad",
                "CEndrsadfowski",2));
        listaStudentow.add(new Student("Konrad",
                "e1dCEndrowski",3));
        listaStudentow.add(new Student("Konrad",
                "vbrtwCEndrowski",4));
        listaStudentow.add(new Student("Konrad",
                "vqv3CEndrowski",5));
    }

    public Student getStudentByID(Integer id){
        return listaStudentow.get(id-1);
    }

    public int wystawOcene(Integer id_grupy, Integer id_studenta,
                           Double ocenaKoncowa,
                           Integer kolokwiumI, Integer kolokwiumII){
        Student s = listaStudentow.get(id_studenta-1);
        s.setPunktyKolokwiumI(kolokwiumI);
        s.setPunktyKolokwiumII(kolokwiumII);
        s.setOcenaKoncowa(ocenaKoncowa);

        return 1;
    }

    public List<GrupaStudent> getAllStudents(Integer id_koordynatora) {
        return listaGrup_I_studentow;
    }
}
