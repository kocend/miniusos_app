package com.miniusos_app.model;

public class GrupaStudent {

    private String nazwaGrupy;
    private Integer idGrupy;
    private String imie;
    private String nazwisko;
    private Integer index;

    public GrupaStudent(String nazwaGrupy, Integer idGrupy, String imie, String nazwisko, Integer index) {
        this.nazwaGrupy = nazwaGrupy;
        this.idGrupy = idGrupy;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.index = index;
    }

    public String getNazwaGrupy() {
        return nazwaGrupy;
    }

    public void setNazwaGrupy(String nazwaGrupy) {
        this.nazwaGrupy = nazwaGrupy;
    }

    public Integer getIdGrupy() {
        return idGrupy;
    }

    public void setIdGrupy(Integer idGrupy) {
        this.idGrupy = idGrupy;
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

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }
}
