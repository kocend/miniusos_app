package com.miniusos_app.model;

public class Koordynator {
    private String imie;
    private String nazwisko;

    public Koordynator(String imie,String nazwisko){
        this.imie=imie;
        this.nazwisko=nazwisko;
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
}
