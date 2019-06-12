package com.miniusos_app.service;

import com.miniusos_app.model.DZIEN_TYGODNIA;
import com.miniusos_app.model.Grupa;
import com.miniusos_app.model.Koordynator;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class PracownikDziekanatuService {

    //fake data
    private List<Grupa> grupy;
    //fake data
    private List<Koordynator> koordynatorzy;

    public PracownikDziekanatuService(){
        setGrupy(new LinkedList<>());
        getGrupy().add(new Grupa("PWJ",1, DZIEN_TYGODNIA.poniedziałek,
                "12:30","24:30",50,
                new Koordynator("MArcel","nieznany")));
        getGrupy().add(new Grupa("PWC",2, DZIEN_TYGODNIA.wtorek,
                "12:30","14:30",76,
                new Koordynator("Mironrcel","nbrakny")));
        getGrupy().add(new Grupa("PW Pythonie",3, DZIEN_TYGODNIA.piątek,
                "10:30","24:30",10,
                new Koordynator("Andrzej","Biały")));
        getGrupy().add(new Grupa("PW C++",4, DZIEN_TYGODNIA.sobota,
                "7:30","22:30",500,
                new Koordynator("Alex","ABCD")));

        koordynatorzy = new LinkedList<>();
        koordynatorzy.add(new Koordynator("Andrzej","Paczos"));
        koordynatorzy.add(new Koordynator("Jakub","Panek"));
        koordynatorzy.add(new Koordynator("Marcin", "Prokopiuk"));
        koordynatorzy.add(new Koordynator("Dan","Cederholm"));
    }

    public List<Grupa> getAllGroups(){
        return getGrupy();
    }

    public int addGroup(Grupa g){
        getGrupy().add(g);
        return 1;
    }

    public int updateGroupByID(Integer id_grupy, Grupa g){
        //WARNING tylko przykład normalnie powoduje błędy
        getGrupy().set(id_grupy-1,g);
        return 1;
    }

    public Grupa getGroupByID(Integer id_grupy){
        //WARNING tylko przykład normalnie powoduje błędy
        Grupa g;
        try {
            g = getGrupy().get(id_grupy - 1);
        }
        catch (IndexOutOfBoundsException ex){
            return null;
        }
        return g;
    }

    public int deleteFromGroup(Integer id_grupy){

        getGrupy().remove(id_grupy-1);
        return 1;
    }

    public List<Koordynator> getAllMasters(){
        return koordynatorzy;
    }

    public List<Grupa> getGrupy() {
        return grupy;
    }

    public void setGrupy(List<Grupa> grupy) {
        this.grupy = grupy;
    }
}
