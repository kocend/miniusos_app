package com.miniusos_app.dao;

import com.miniusos_app.dao.dataBaseServiceInterface;
import com.miniusos_app.model.DZIEN_TYGODNIA;
import com.miniusos_app.model.Grupa;
import com.miniusos_app.model.Koordynator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBConnect implements dataBaseServiceInterface {
	private Connection con;
	private Statement st;
	private ResultSet rs;

	public DBConnect() {
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/mini_usos?serverTimezone=Europe/Berlin","root","");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public List<Grupa> pobierzWszystkieGrupy(){
		List<Grupa> lista = new ArrayList<Grupa>();
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM grupy g JOIN koordynatorzy k ON g.id_k=k.id_k");
			while(rs.next()) {
				Integer idGrupy = new Integer(rs.getInt("id_gr"));
				String nazwaGrupy = new String(rs.getString("nazwa_grupy"));
				String dzienTygodnia = new String(rs.getString("dzien_tygodnia"));
				String godzinaRozpoczecia = new String(rs.getString("godz_rozpoczecia"));
				String godzinaZakonczenia = new String(rs.getString("godz_zakonczenia"));
				Integer limitMiejsc = new Integer(rs.getInt("limit_miejsc"));
				String imieKoordynatora = new String(rs.getString("imie"));
				String nazwiskoKoordynatora = new String(rs.getString("nazwisko"));
				
				DZIEN_TYGODNIA dzien = DZIEN_TYGODNIA.poniedziałek;
				switch(dzienTygodnia.toLowerCase()) {
				case "poniedzialek":
					dzien = DZIEN_TYGODNIA.poniedziałek;
					break;
				case "wtorek":
					dzien = DZIEN_TYGODNIA.wtorek;
					break;
				case "sroda":
					dzien = DZIEN_TYGODNIA.środa;
					break;
				case "czwartek":
					dzien = DZIEN_TYGODNIA.czwartek;
					break;
				case "piatek":
					dzien = DZIEN_TYGODNIA.piątek;
					break;
				case "sobota":
					dzien = DZIEN_TYGODNIA.sobota;
					break;
				case "niedziela":
					dzien = DZIEN_TYGODNIA.niedziela;
					break;
				}
				
				Koordynator koordynator = new Koordynator(imieKoordynatora, nazwiskoKoordynatora);
				Grupa grupa = new Grupa(nazwaGrupy, idGrupy, dzien, godzinaRozpoczecia, 
						godzinaZakonczenia, limitMiejsc, koordynator);
				lista.add(grupa);
				st.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}
	
	public Grupa pobbierzGrupePoId(Integer id) {
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM grupy g JOIN koordynatorzy k ON g.id_k=k.id_k WHERE g.id_k="+id.toString());
			
			Integer idGrupy = new Integer(rs.getInt("id_gr"));
			String nazwaGrupy = new String(rs.getString("nazwa_grupy"));
			String dzienTygodnia = new String(rs.getString("dzien_tygodnia"));
			String godzinaRozpoczecia = new String(rs.getString("godz_rozpoczecia"));
			String godzinaZakonczenia = new String(rs.getString("godz_zakonczenia"));
			Integer limitMiejsc = new Integer(rs.getInt("limit_miejsc"));
			String imieKoordynatora = new String(rs.getString("imie"));
			String nazwiskoKoordynatora = new String(rs.getString("nazwisko"));
			
			DZIEN_TYGODNIA dzien = DZIEN_TYGODNIA.poniedziałek;
			switch(dzienTygodnia.toLowerCase()) {
			case "poniedzialek":
				dzien = DZIEN_TYGODNIA.poniedziałek;
				break;
			case "wtorek":
				dzien = DZIEN_TYGODNIA.wtorek;
				break;
			case "sroda":
				dzien = DZIEN_TYGODNIA.środa;
				break;
			case "czwartek":
				dzien = DZIEN_TYGODNIA.czwartek;
				break;
			case "piatek":
				dzien = DZIEN_TYGODNIA.piątek;
				break;
			case "sobota":
				dzien = DZIEN_TYGODNIA.sobota;
				break;
			case "niedziela":
				dzien = DZIEN_TYGODNIA.niedziela;
				break;
			}
			
			Koordynator koordynator = new Koordynator(imieKoordynatora, nazwiskoKoordynatora);
			Grupa grupa = new Grupa(nazwaGrupy, idGrupy, dzien, godzinaRozpoczecia, 
					godzinaZakonczenia, limitMiejsc, koordynator);
			st.close();
			return grupa;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public int dodajGrupe(Grupa g) {
		try {
			st = con.createStatement();
			String nazwaGrupy = g.getNazwaGrupy();
			Integer idGrupy = g.getId_grupy();
			DZIEN_TYGODNIA dzien = g.getDzienTygodnia();
			String godzinaRozpoczecia = g.getGodzinaRozpoczecia();
			String godzinaZakonczenia = g.getGodzinaZakonczenia();
			Integer limitMiejsc = g.getLimitMiejsc();
			Koordynator koordynator = g.getKoordynator();
//			String imie = koordynator.getImie();
//			String nazwisko = koordynator.getNazwisko();
			
			rs = st.executeUpdate("");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
