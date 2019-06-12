package com.miniusos_app.dao;

import com.fasterxml.jackson.annotation.ObjectIdGenerator.IdKey;
import com.miniusos_app.dao.dataBaseServiceInterface;
import com.miniusos_app.model.*;

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
				Integer idKoordynatora = new Integer(rs.getInt("id_k"));
				
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
				
				Koordynator koordynator = new Koordynator(imieKoordynatora, nazwiskoKoordynatora, idKoordynatora);
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

    public Grupa pobierzGrupePoID(Integer id) {
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
			Integer idKoordynatora = new Integer(rs.getInt("id_k"));
			
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
			
			Koordynator koordynator = new Koordynator(imieKoordynatora, nazwiskoKoordynatora, idKoordynatora);
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
		int odp = -1;
		try {
			st = con.createStatement();
			String nazwaGrupy = g.getNazwaGrupy();
			Integer idGrupy = g.getId_grupy();
			DZIEN_TYGODNIA dzien = g.getDzienTygodnia();
			String godzinaRozpoczecia = g.getGodzinaRozpoczecia();
			String godzinaZakonczenia = g.getGodzinaZakonczenia();
			Integer limitMiejsc = g.getLimitMiejsc();
			Koordynator koordynator = g.getKoordynator();
			Integer idKoordynatora = koordynator.getId();
//			String imie = koordynator.getImie();
//			String nazwisko = koordynator.getNazwisko();
			
			odp = st.executeUpdate("INSERT INTO grupy(id_gr, nazwa_grupy, dzien_tygodnia, godz_rozpoczecia,"
					+ "godz_zakonczenia, limit_miejsc, id_k) VALUES("
					+idGrupy+","+nazwaGrupy+","+dzien+","+godzinaRozpoczecia+","+godzinaZakonczenia+","+limitMiejsc+","
					+idKoordynatora+")");
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return odp;
	}
	
	public int usunGrupePoID(Integer id_grupy) {
		int odp = -1;
		try {
			st = con.createStatement();
			st.executeUpdate("DELETE FROM przynaleznosc WHERE id_gr="+id_grupy);
			odp = st.executeUpdate("DELETE FROM grupy WHERE id_gr="+id_grupy);
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return odp;
	}
	
	public int zaktualizujGrupePoID(Integer id_grupy, Grupa g) {
		int odp = -1;
		try {
			st = con.createStatement();
			String nazwaGrupy = g.getNazwaGrupy();
			DZIEN_TYGODNIA dzien = g.getDzienTygodnia();
			String godzinaRozpoczecia = g.getGodzinaRozpoczecia();
			String godzinaZakonczenia = g.getGodzinaZakonczenia();
			Integer limitMiejsc = g.getLimitMiejsc();
			Koordynator koordynator = g.getKoordynator();
			Integer idKoordynatora = koordynator.getId();
			
			odp = st.executeUpdate("UPDATE grupy SET "
					+" nazwa_grupy=\""+nazwaGrupy+"\" "
					+ " dzien_tygodnia=\""+dzien.toString()+"\" "
					+ "godz_rozpoczecia=\""+godzinaRozpoczecia+"\" "
					+ "godz_zakonczenia=\""+godzinaZakonczenia+"\" "
					+ "limit_miejsc="+limitMiejsc.toString()
					+ " id_k="+idKoordynatora.toString()
					+" WHERE id_gr="+id_grupy);
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return odp;
	}
	
	public List<Koordynator> pobierzWszystkichKoordynatorow(){
		List<Koordynator> lista = new ArrayList<Koordynator>();
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM koordynatorzy");
			while(rs.next()) {
				Integer idKoordynatora = rs.getInt("id_k");
				String imie = rs.getString("imie");
				String nazwisko = rs.getString("nazwisko");
				 Koordynator k = new Koordynator(imie, nazwisko, idKoordynatora);
				 lista.add(k);
			}
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<GrupaStudent> pobierzGrupyIStudentowKoordynatora(Integer id_koordynatora){
		List<GrupaStudent> lista = new ArrayList<GrupaStudent>();
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT g.nazwa_grupy, g.id_gr, s.imię, s.nazwisko, s.nrUSOS FROM studenci s \r\n" + 
					"JOIN przynależność p ON s.id_s=p.id_s JOIN grupy g ON p.id_gr=g.id_gr\r\n" + 
					"WHERE g.id_gr IN \r\n" + 
					"(SELECT id_gr FROM koordynatorzy k JOIN grupy g ON k.id_k=g.id_k WHERE g.id_k="+id_koordynatora+")");
			while(rs.next()) {
				String nazwaGrupy = rs.getString("nazwa_grupy");
				Integer idGrupy = rs.getInt("id_gr");
				String imie = rs.getString("imie");
				String nazwisko = rs.getString("nazwisko");
				Integer nrUSOS = rs.getInt("nrUSOS");
				GrupaStudent gs = new GrupaStudent(nazwaGrupy, idGrupy, imie, nazwisko, nrUSOS);
				lista.add(gs);
				st.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}
	
	public Student pobierzStudentaPoID(Integer id) {
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM studenci WHERE nrUSOS="+id);
			String imie = rs.getString("imie");
			String nazwisko = rs.getString("nazwisko");
//			Integer nrUSOS = rs.getInt("nrUSOS");
			Double ocenaKoncowa = rs.getDouble("ocena_koncowa");
			Integer kolokwium1 = rs.getInt("kolokwium1_pkt");
			Integer kolokwium2 = rs.getInt("kolokwium2_pkt");
			Integer sumaPkt = rs.getInt("suma_pkt");
			Student s = new Student(imie, nazwisko, id);
			s.setPunktyKolokwiumI(kolokwium1);
			s.setPunktyKolokwiumII(kolokwium2);
			s.setOcenaKoncowa(ocenaKoncowa);
			s.setSumaPunktow(sumaPkt);
			st.close();
			return s;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public int magicznyGuzikWystaw5tymCoNieMajaOceny(Integer id_grupy) {
		int odp = -1;
		try {
			st = con.createStatement();
			odp = st.executeUpdate("UPDATE studenci SET ocena_koncowa=5 WHERE ocena_koncowa IS NULL");
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return odp;
	}

//    @Override
//    public List<Koordynator> pobierzWszystkichKoordynatorow() {
//        return null;
//    }
//
//    @Override
//    public List<GrupaStudent> pobierzGrupyIStudentowKoordynatora(Integer id_koordynatora) {
//        return null;
//    }
//
//    @Override
//    public Student pobierzStudentaPoID(Integer id) {
//        return null;
//    }
//
//    @Override
//    public int magicznyGuzikWystaw5tymCoNieMajaOceny(Integer id_grupy) {
//        return 0;
//    }
//
//    @Override
//    public int wystawOcene(Integer id_grupy, Integer id_studenta, Double ocenaKoncowa, Integer kolokwiumI, Integer kolokwiumII) {
//        return 0;
//    }
//
//    @Override
//    public int zapiszDoGrupy(Student s, Integer id_grupy) {
//        return 0;
//    }
//
//    @Override
//    public int pobierzMojeWyniki(Student s) {
//        return 0;
//    }


}
