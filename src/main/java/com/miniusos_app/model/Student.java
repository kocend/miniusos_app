package com.miniusos_app.model;



public class Student {

    private String imie;
    private String nazwisko;
    private Integer numerUSOS;

    public Student(String imie, String nazwisko, Integer numerUsos) {
        this.imie=imie;
        this.nazwisko=nazwisko;
        this.numerUSOS=numerUsos;
       // przeliczPunkty();
    }

   /* private void przeliczPunkty() {
        sumaPunktow=punktyKolokwiumI+punktyKolokwiumII;

        if(sumaPunktow<50)
            ocenaKoncowa=2.0;
        else if(sumaPunktow<=60)
            ocenaKoncowa=3.0;
        else if(sumaPunktow<=70)
            ocenaKoncowa=3.5;
        else if(sumaPunktow<=80)
            ocenaKoncowa=4.0;
        else if(sumaPunktow<=90)
            ocenaKoncowa=4.5;
        else if(sumaPunktow<=100)
            ocenaKoncowa=5.0;
    }*/

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
    public Integer getNumerUSOS() {
        return numerUSOS;
    }
    public void setNumerUSOS(Integer numerUSOS) {
        this.numerUSOS = numerUSOS;
    }

    @Override
    public String toString() {
        return imie + " " + nazwisko + " " + numerUSOS;
    }
}
