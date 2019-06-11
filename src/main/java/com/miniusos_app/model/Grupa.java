package com.miniusos_app.model;


public class Grupa{

    private String nazwaGrupy;
    private Integer id_grupy;
    private DZIEN_TYGODNIA dzienTygodnia;
    private String godzinaRozpoczecia;
    private String godzinaZakonczenia;
    private Integer limitMiejsc;
    private Koordynator koordynator;


    public Grupa(String nazwaGrupy, Integer id, DZIEN_TYGODNIA dzienTygodnia,
                 String godzinaRozpoczecia, String godzinaZakonczenia,
                 Integer limitMiejsc, Koordynator koordynator) {

        this.setNazwaGrupy(nazwaGrupy);
        this.setId_grupy(id);
        this.dzienTygodnia = dzienTygodnia;
        this.godzinaRozpoczecia = godzinaRozpoczecia;
        this.godzinaZakonczenia = godzinaZakonczenia;
        this.setLimitMiejsc(limitMiejsc);
        this.setKoordynator(koordynator);
    }

    public Integer getNumer() {
        return getId_grupy();
    }

    public void setNumer(Integer id) {
        this.setId_grupy(id);
    }

    public DZIEN_TYGODNIA getDzienTygodnia() {
        return dzienTygodnia;
    }

    public void setDzienTygodnia(DZIEN_TYGODNIA dzienTygodnia) {
        this.dzienTygodnia = dzienTygodnia;
    }

    public String getGodzinaRozpoczecia() {
        return godzinaRozpoczecia;
    }

    public void setGodzinaRozpoczecia(String godzinaRozpoczecia) {
        this.godzinaRozpoczecia = godzinaRozpoczecia;
    }

    public String getGodzinaZakonczenia() {
        return godzinaZakonczenia;
    }

    public void setGodzinaZakonczenia(String godzinaZakonczenia) {
        this.godzinaZakonczenia = godzinaZakonczenia;
    }

    public String getNazwaGrupy() {
        return nazwaGrupy;
    }

    public void setNazwaGrupy(String nazwaGrupy) {
        this.nazwaGrupy = nazwaGrupy;
    }

    public Integer getId_grupy() {
        return id_grupy;
    }

    public void setId_grupy(Integer id_grupy) {
        this.id_grupy = id_grupy;
    }

    public Integer getLimitMiejsc() {
        return limitMiejsc;
    }

    public void setLimitMiejsc(Integer limitMiejsc) {
        this.limitMiejsc = limitMiejsc;
    }

    public Koordynator getKoordynator() {
        return koordynator;
    }

    public void setKoordynator(Koordynator koordynator) {
        this.koordynator = koordynator;
    }

    @Override
    public String toString() {
        return "Grupa " + getId_grupy() + " " + dzienTygodnia.toString()+" "+godzinaRozpoczecia+"-"+godzinaZakonczenia;
    }

}
