package com.miniusos_app.dao;

import com.miniusos_app.model.Grupa;
import com.miniusos_app.model.GrupaStudent;
import com.miniusos_app.model.Koordynator;
import com.miniusos_app.model.Student;


import javax.persistence.criteria.CriteriaBuilder;
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
    public List<GrupaStudent> pobierzGrupyIStudentowKoordynatora(Integer id_koordynatora);
    public Student pobierzStudentaPoID(Integer id);
    public int magicznyGuzikWystaw5tymCoNieMajaOceny(Integer id_grupy);
    public int wystawOcene(Integer id_grupy, Integer id_studenta,
                           Double ocenaKoncowa,
                           Integer kolokwiumI, Integer kolokwiumII);

    //dla studenta
    public int zapiszDoGrupy(Student s, Integer id_grupy);
    public int pobierzMojeWyniki(Student s);

}
