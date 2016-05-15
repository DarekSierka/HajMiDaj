package HajsMiDaj.HMD;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Users database table.
 * 
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

	public User() {
	}

	public int getIdUsers() {
		return this.idUsers;
	}

	public void setIdUsers(int idUsers) {
		this.idUsers = idUsers;
	}

	public String getHaslo() {
		return this.haslo;
	}

	public void setHaslo(String haslo) {
		this.haslo = haslo;
	}

	public String getImie() {
		return this.imie;
	}

	public void setImie(String imie) {
		this.imie = imie;
	}

	public int getKasa() {
		return this.kasa;
	}

	public void setKasa(int kasa) {
		this.kasa = kasa;
	}

	public int getKto() {
		return this.kto;
	}

	public void setKto(int kto) {
		this.kto = kto;
	}

	public String getNazwa() {
		return this.nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	public String getNazwisko() {
		return this.nazwisko;
	}

	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}

	public List<Zaklady> getZakladies() {
		return this.zakladies;
	}

	public void setZakladies(List<Zaklady> zakladies) {
		this.zakladies = zakladies;
	}

	public Zaklady addZaklady(Zaklady zaklady) {
		getZakladies().add(zaklady);
		zaklady.setUser(this);

		return zaklady;
	}

	public Zaklady removeZaklady(Zaklady zaklady) {
		getZakladies().remove(zaklady);
		zaklady.setUser(null);

		return zaklady;
	}

}