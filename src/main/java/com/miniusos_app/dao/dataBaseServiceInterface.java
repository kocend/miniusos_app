package com.miniusos_app.dao;

import com.miniusos_app.model.Grupa;
import com.miniusos_app.model.Koordynator;
import com.miniusos_app.model.Student;

public interface dataBaseServiceInterface {

    //dla wszystkich
    public Grupa pobierzWszystkieGrupy();
    public Grupa pobierzGrupePoID(Integer id_grupy);

    //dla pracownika_dziekanatu
    public int dodajGrupe(Grupa g);
    public int usunGrupePoID(Integer id_grupy);
    public int zaktualizujGrupePoID(Integer id_grupy, Grupa g);
    public int pobierzWszystkichKoordynatorow();

    //dla koordynatora
    public int pobierzGrupyKoordynatora(Koordynator k);
    public int magicznyGuzikWystaw5tymCoNieMajaOceny();
    public int wystawOcene(Student s,Integer ocena);
    public int ocenKolokwiumI(Student s,Integer punkty);
    public int ocenKolokwiumII(Student s,Integer punkty);

    //dla studenta
    public int zapiszDoGrupy(Student s, Grupa g);
    public int pobierzMojeWyniki(Student s);

}
