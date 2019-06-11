package com.miniusos_app.service;

import com.miniusos_app.model.DZIEN_TYGODNIA;
import com.miniusos_app.model.Grupa;
import com.miniusos_app.model.Koordynator;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class PracownikDziekanatuService {



    public List<Grupa> getAllGroups(){
        List<Grupa> grupy = new LinkedList<>();
        grupy.add(new Grupa("PWJ",1, DZIEN_TYGODNIA.poniedziałek,
                "12:30","24:30",50,
                new Koordynator("MArcel","nieznany")));
        grupy.add(new Grupa("PWC",1, DZIEN_TYGODNIA.wtorek,
                "12:30","14:30",76,
                new Koordynator("Mironrcel","nbrakny")));
        grupy.add(new Grupa("PW Pythonie",1, DZIEN_TYGODNIA.piątek,
                "10:30","24:30",10,
                new Koordynator("Andrzej","Biały")));
        grupy.add(new Grupa("PW C++",1, DZIEN_TYGODNIA.sobota,
                "7:30","22:30",500,
                new Koordynator("Alex","ABCD")));
        return grupy;
    }

}
