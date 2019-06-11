package com.miniusos_app.dao;

import com.miniusos_app.model.Grupa;
import com.miniusos_app.model.Koordynator;
import com.miniusos_app.model.Student;


import java.util.List;

public interface dataBaseServiceInterface {

    //dla wszystkich
    public List<Grupa> pobierzWszystkieGrupy();
    public Grupa pobierzGrupePoID(Integer id_grupy);

    //dla pracownika_dziekanatu
    public int dodajGrupe(Grupa g);
    public int usunGrupePoID(Integer id_grupy);
    public int zaktualizujGrupePoID(Integer id_grupy, Grupa g);
    public int pobierzWszystkichKoordynatorow();

    //dla koordynatora
    public List<Grupa> pobierzGrupyKoordynatora(Koordynator k);
    public int magicznyGuzikWystaw5tymCoNieMajaOceny(Integer id_grupy);
    public int wystawOcene(Integer id_grupy, Student s, Integer ocena);
    public int ocenKolokwiumI(Integer id_grupy,Student s,Integer punkty);
    public int ocenKolokwiumII(Integer id_grupy,Student s,Integer punkty);

    //dla studenta
    public int zapiszDoGrupy(Student s, Grupa g);
    public int pobierzMojeWyniki(Student s);

}
