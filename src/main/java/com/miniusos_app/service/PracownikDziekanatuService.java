package com.miniusos_app.service;

import com.miniusos_app.dao.dataBaseServiceInterface;
import com.miniusos_app.model.Grupa;
import com.miniusos_app.model.Koordynator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PracownikDziekanatuService {

    private final dataBaseServiceInterface dbInterface;

    @Autowired
    public PracownikDziekanatuService(@Qualifier("mysqlDB") dataBaseServiceInterface db){
        dbInterface=db;
    }

    public List<Grupa> getAllGroups(){
        return dbInterface.pobierzWszystkieGrupy();
    }

    public int addGroup(Grupa g){
        dbInterface.dodajGrupe(g);
        return 1;
    }

    public int updateGroupByID(Integer id_grupy, Grupa g){
        dbInterface.zaktualizujGrupePoID(id_grupy,g);
        return 1;
    }

    public Grupa getGroupByID(Integer id_grupy){
        return dbInterface.pobierzGrupePoID(id_grupy);
    }

    public int getIdOfLastGroup(){
        return dbInterface.pobierzIDOstatniejGrupy();
    }

    public int deleteFromGroup(Integer id_grupy){
        dbInterface.usunGrupePoID(id_grupy);
        return 1;
    }

    public List<Koordynator> getAllMasters(){
        return dbInterface.pobierzWszystkichKoordynatorow();
    }
}
