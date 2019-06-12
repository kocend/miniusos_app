package com.miniusos_app.model;

public class Koordynator {
    private String imie;
    private String nazwisko;
    private Integer id;

    public Koordynator(String imie,String nazwisko, Integer id){
        this.imie=imie;
        this.nazwisko=nazwisko;
        this.id=id;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }
    
    public Integer getId() {
    	return id;
    }
    
    public void setId(Integer id) {
    	this.id=id;
    }
}
