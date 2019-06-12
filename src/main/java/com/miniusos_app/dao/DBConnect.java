package com.miniusos_app.dao;

import com.fasterxml.jackson.annotation.ObjectIdGenerator.IdKey;
import com.miniusos_app.dao.dataBaseServiceInterface;
import com.miniusos_app.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository("mysqlDB")
public class DBConnect implements dataBaseServiceInterface {
	private Connection con;
	private Statement st;
	private ResultSet rs;

	@Autowired
	private DataSource dataSource;

	public DBConnect() {
		try {
			//con = DriverManager.getConnection("jdbc:mysql://localhost/mini_usos?serverTimezone=Europe/Berlin","root","");
			con = DriverManager.getConnection("jdbc:mysql://localhost/testy?serverTimezone=Europe/Berlin","springboot","c9TrBmOzR3wsTYyd");
			//con = dataSource.getConnection();

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
					default:
						dzien=DZIEN_TYGODNIA.poniedziałek;
				}
				
				Koordynator koordynator = new Koordynator(imieKoordynatora, nazwiskoKoordynatora, idKoordynatora);
				Grupa grupa = new Grupa(nazwaGrupy, idGrupy, dzien, godzinaRozpoczecia, 
						godzinaZakonczenia, limitMiejsc, koordynator);
				lista.add(grupa);
				//st.close();
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
			rs = st.executeQuery("SELECT * FROM grupy g JOIN koordynatorzy k ON g.id_k=k.id_k WHERE g.id_gr="+id.toString());
			Grupa grupa =null;

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
				switch (dzienTygodnia.toLowerCase()) {
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
						default:
							dzien=DZIEN_TYGODNIA.poniedziałek;
				}

				Koordynator koordynator = new Koordynator(imieKoordynatora, nazwiskoKoordynatora, idKoordynatora);
				grupa = new Grupa(nazwaGrupy, idGrupy, dzien, godzinaRozpoczecia,
						godzinaZakonczenia, limitMiejsc, koordynator);

				//st.close();
				return grupa;
			}
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
			PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO grupy(id_gr, nazwa_grupy, dzien_tygodnia, godz_rozpoczecia," +
					"godz_zakonczenia, limit_miejsc, id_k) VALUES(?,?,?,?,?,?,?)");
			preparedStatement.setInt(1,idGrupy);
			preparedStatement.setString(2,nazwaGrupy);
			preparedStatement.setString(3,dzien.toString());
			preparedStatement.setString(4,godzinaRozpoczecia);
			preparedStatement.setString(5,godzinaZakonczenia);
			preparedStatement.setInt(6,limitMiejsc);
			preparedStatement.setInt(7,idKoordynatora);

			odp = preparedStatement.executeUpdate();
			//st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 5;
		}
		return odp;
	}
	
	public int usunGrupePoID(Integer id_grupy) {
		int odp = -1;
		try {
			st = con.createStatement();
			st.executeUpdate("DELETE FROM przynaleznosc WHERE id_gr="+id_grupy);
			odp = st.executeUpdate("DELETE FROM grupy WHERE id_gr="+id_grupy);
			//st.close();
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


			PreparedStatement preparedStatement = con.prepareStatement("UPDATE grupy SET "
					+" nazwa_grupy=?"
					+ ", dzien_tygodnia=?"
					+ ",godz_rozpoczecia=?"
					+ ",godz_zakonczenia=?"
					+ ",limit_miejsc=?"
					+ ", id_k=?"
					+" WHERE id_gr=?;");
			preparedStatement.setString(1,nazwaGrupy);
			preparedStatement.setString(2,dzien.toString());
			preparedStatement.setString(3,godzinaRozpoczecia);
			preparedStatement.setString(4,godzinaZakonczenia);
			preparedStatement.setInt(5,limitMiejsc);
			preparedStatement.setInt(6,idKoordynatora);
			preparedStatement.setInt(7,id_grupy);

			odp = preparedStatement.executeUpdate();
			//st.close();
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
			//st.close();
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

			//TODO zapytanie nie zwraca listy
			rs = st.executeQuery("SELECT g.nazwa_grupy, g.id_gr, s.imie, s.nazwisko, s.nrUSOS FROM studenci s\r\n" +
					"JOIN przynaleznosc p ON s.nrUSOS=p.nrUSOS JOIN grupy g ON p.id_gr=g.id_gr\r\n" +
					"WHERE g.id_gr IN\r\n" + 
					"(SELECT id_gr FROM koordynatorzy k JOIN grupy g ON k.id_k=g.id_k WHERE g.id_k="+id_koordynatora+")");
			while(rs.next()) {
				String nazwaGrupy = rs.getString("nazwa_grupy");
				Integer idGrupy = rs.getInt("id_gr");
				String imie = rs.getString("imie");
				String nazwisko = rs.getString("nazwisko");
				Integer nrUSOS = rs.getInt("nrUSOS");
				GrupaStudent gs = new GrupaStudent(nazwaGrupy, idGrupy, imie, nazwisko, nrUSOS);
				lista.add(gs);
			}
			//st.close();
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
//			Double ocenaKoncowa = rs.getDouble("ocena_koncowa");
//			Integer kolokwium1 = rs.getInt("kolokwium1_pkt");
//			Integer kolokwium2 = rs.getInt("kolokwium2_pkt");
//			Integer sumaPkt = rs.getInt("suma_pkt");
			Student s = new Student(imie, nazwisko, id);
//			s.setPunktyKolokwiumI(kolokwium1);
//			s.setPunktyKolokwiumII(kolokwium2);
//			s.setOcenaKoncowa(ocenaKoncowa);
//			s.setSumaPunktow(sumaPkt);
			//st.close();
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
			odp = st.executeUpdate("UPDATE przynaleznosc SET ocena_koncowa=5 WHERE ocena_koncowa IS NULL");
			//st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return odp;
	}
	
	 public int wystawOcene(Integer id_grupy, Integer id_studenta,
             Double ocenaKoncowa,
             Integer kolokwiumI, Integer kolokwiumII) {
		 int odp = -1;
			try {
				st = con.createStatement();
				odp = st.executeUpdate("UPDATE przynaleznosc SET ocena_koncowa="+ocenaKoncowa
						+", punkty_kolokwium1="+kolokwiumI
						+", punkty_kolokwium2"+kolokwiumII
						+" WHERE id_gr="+id_grupy
						+" AND nrUSOS="+id_studenta);
				//st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return odp;
	 }
	 
	 public int zapiszDoGrupy(Student s, Integer id_grupy) {
		 int odp = -1;
			try {
				st = con.createStatement();
				odp = st.executeUpdate("INSERT INTO przynaleznosc (nrUSOS, id_gr) VALUES ("
						+ s.getNumerUSOS()+","+id_grupy+")");
				//st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return odp;
	 }
	 
	 public List<Wyniki> pobierzMojeWyniki(Student s){
		 List<Wyniki> lista = new ArrayList<Wyniki>();
			try {
				st = con.createStatement();
				rs = st.executeQuery("SELECT g.nazwa_grupy, p.ocena_koncowa, p.punkty_kolokwium1, p.punkty_kolokwium2\r\n" +
						"FROM przynaleznosc p JOIN grupy g ON p.id_gr=g.id_gr\r\n" +
						"WHERE p.nrUSOS="+ s.getNumerUSOS());
				while(rs.next()) {
					String nazwaGrupy = rs.getString("nazwa_grupy");
					Double ocenaKoncowa = rs.getDouble("ocena_koncowa");
					Integer kolokwium1 = rs.getInt("punkty_kolokwium1");
					Integer kolokwium2 = rs.getInt("punkty_kolokwium2");
					Wyniki w = new Wyniki(nazwaGrupy);
					w.setKolokwium1(kolokwium1);
					w.setKolokwium2(kolokwium2);
					w.setOcenaKoncowa(ocenaKoncowa);
					lista.add(w);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return lista;
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
