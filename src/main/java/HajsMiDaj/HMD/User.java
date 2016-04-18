package HajsMiDaj.HMD;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * Klasa reprezentująca użytkownika znajdującego się w tabeli Users w bazie danych.
 * Zawiera informacje o:
 * <ul>
 * <li> Numerze identyfikacyjnym w bazie danych </li>
 * <li> Nazwie(login) Użytkownika </li>
 * <li> Haśle użytkownika</li>
 * <li> Imieniu użytkownika</li>
 * <li> Nazwisku Użytkownika </li>
 * <li> Stanie konta użytkownika </li>
 * <li> Typie użytkownika </li>
 * <li> Zakładach użytkownika </li>
 * </ul>
 * BLAHABLAHLABLAHLHA
 * @see Init
 * @author Darek Sierka, Filip Borowski, Bartosz Dylewski  
 */
@Entity
@Table(name="Users")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idUsers;

	private String haslo;

	private String imie;

	private int kasa;

	private int kto;

	private String nazwa;

	private String nazwisko;

	//bi-directional many-to-one association to Zaklady
	@OneToMany(mappedBy="user")
	private List<Zaklady> zakladies;

	/**
	 * Konstruktor tworzący obiekt klasy User.
	 */
	public User() {
	}

	/**
	 * Metoda zwracająca numer identyfikujący użytkownika w bazie danych.
	 * @return numer ID użytkownika.
	 */
	public int getIdUsers() {
		return this.idUsers;
	}

	/**
	 * Metoda ustawiająca numer identyfikujący użytkownika w bazie danych.
	 * @param idUsers nowy numer ID użytkownika.
	 */
	public void setIdUsers(int idUsers) {
		this.idUsers = idUsers;
	}

	/**
	 * Metoda zwracająca hasło użytkownika.
	 * @return hasło użytkownika
	 */
	public String getHaslo() {
		return this.haslo;
	}

	/**
	 * Metoda ustawiająca hasło użytkownika.
	 * @param haslo nowe hasło użytkownika
	 */
	public void setHaslo(String haslo) {
		this.haslo = haslo;
	}

	/**
	 * Metoda zwracająca imię użytkownika.
	 * @return imię użytkownika
	 */
	public String getImie() {
		return this.imie;
	}

	/**
	 * Metoda ustawiająca imię użytkownika.
	 * @param imie nowe imię użytkownika
	 */
	public void setImie(String imie) {
		this.imie = imie;
	}

	/**
	 * Metoda zwracająca stan konta użytkownika.
	 * @return stan konta użytkownika
	 */
	public int getKasa() {
		return this.kasa;
	}

	/**
	 * Metoda ustawiająca stan konta użytkownika.
	 * @param kasa nowy stan konta użytkownika.
	 */
	public void setKasa(int kasa) {
		this.kasa = kasa;
	}

	/**
	 * Metoda zwracająca typ użytkownika.
	 * @return typ użytkownika
	 */
	public int getKto() {
		return this.kto;
	}

	/**
	 * Metoda ustawiająca typ użytkownika.
	 * @param kto nowy typ użytkownika
	 */
	public void setKto(int kto) {
		this.kto = kto;
	}

	/**
	 * Metoda zwracająca nazwę użytkownika.
	 * @return nazwa użytkownika
	 */
	public String getNazwa() {
		return this.nazwa;
	}

	/**
	 * Metoda ustawiająca nazwe użytkownika.
	 * @param nazwa nowa nazwa użytkownika
	 */
	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	/**
	 * Metoda zwracająca nazwisko użytkownika.
	 * @return nazwisko użytkownika
	 */
	public String getNazwisko() {
		return this.nazwisko;
	}

	/**
	 * Metoda ustawiająca nazwisko użytkownika.
	 * @param nazwisko nowe nazwisko użytkownika
	 */
	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}

	/**
	 * Metoda zwracająca listę zakładów użytkownika.
	 * @return lista zakładów użytkownika
	 */
	public List<Zaklady> getZakladies() {
		return this.zakladies;
	}

	/**
	 * Metoda ustawiająca listę zakładów użytkownika.
	 * @param zakladies nowa lista zakładów
	 */
	public void setZakladies(List<Zaklady> zakladies) {
		this.zakladies = zakladies;
	}

	/**
	 * Metoda dodająca nowy zakład do listy zakładów użytkownika.
	 * @param zaklady nowy zakład
	 * @return zaaktualizowana lista zakładów użytkownika
	 */
	public Zaklady addZaklady(Zaklady zaklady) {
		getZakladies().add(zaklady);
		zaklady.setUser(this);

		return zaklady;
	}

	/**
	 * Metoda usuwająca zakład z listy zakładów użytkownika.
	 * @param zaklady zakład do usunięcia
	 * @return zaaktualizowana lista zakładów użytkownika
	 */
	public Zaklady removeZaklady(Zaklady zaklady) {
		getZakladies().remove(zaklady);
		zaklady.setUser(null);

		return zaklady;
	}

}