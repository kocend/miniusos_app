package com.miniusos_app.service;

import com.miniusos_app.dao.dataBaseServiceInterface;
import com.miniusos_app.model.Grupa;
import com.miniusos_app.model.Student;
import com.miniusos_app.model.Wyniki;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentService {

    private final dataBaseServiceInterface dbInterface;

    @Autowired
    public StudentService(@Qualifier("mysqlDB") dataBaseServiceInterface db){
        dbInterface=db;
    }

    public Student getStudentByID(Integer id){
        return dbInterface.pobierzStudentaPoID(id);
    }

    public List<Grupa> getAllGroups(){
        return dbInterface.pobierzWszystkieGrupy();
    }

    public List<Wyniki> getAllStudentsMarks(Student s){
        return dbInterface.pobierzMojeWyniki(s);
    }

    public int registerStudentToGroup(Student s, Integer id_grupy){
        return dbInterface.zapiszDoGrupy(s,id_grupy);
    }


}
