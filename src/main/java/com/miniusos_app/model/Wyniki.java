package com.miniusos_app.model;

public class Wyniki {
	private String nazwaGrupy;
	private Integer punktyKolokwium1;
	private Integer punktyKolokwium2;
	private Double ocenaKoncowa;
	
	public Wyniki(String nazwaGrupy) {
		this.nazwaGrupy = nazwaGrupy;
	}
	
	public String getNazwaGrupy() {
		return nazwaGrupy;
	}
	
	public void setKolokwium1(Integer punktyKolokwium1) {
		this.punktyKolokwium1=punktyKolokwium1;
	}
	
	public Integer getKolokwium1() {
		return punktyKolokwium1;
	}
	
	public void setKolokwium2(Integer punktyKolokwium2) {
		this.punktyKolokwium2=punktyKolokwium2;
	}
	
	public Integer getKolokwium2() {
		return punktyKolokwium2;
	}
	
	public void setOcenaKoncowa(Double ocenaKoncowa) {
		this.ocenaKoncowa = ocenaKoncowa;
	}
	
	public Double getOcenaKoncowa() {
		return ocenaKoncowa;
	}
}
