package HajsMiDaj.HMD;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Mecze database table.
 * 
 */
@Entity
@NamedQuery(name="Mecze.findAll", query="SELECT m FROM Mecze m")
public class Mecze implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idMecze;

	private int czyZagrany;

	private String nazwadruzyny1;

	private String nazwadruzyny2;

	private int wynik;

	//bi-directional many-to-one association to Zaklady
	@OneToMany(mappedBy="mecze")
	private List<Zaklady> zakladies;

	public Mecze() {
	}

	public int getIdMecze() {
		return this.idMecze;
	}

	public void setIdMecze(int idMecze) {
		this.idMecze = idMecze;
	}

	public int getCzyZagrany() {
		return this.czyZagrany;
	}

	public void setCzyZagrany(int czyZagrany) {
		this.czyZagrany = czyZagrany;
	}

	public String getNazwadruzyny1() {
		return this.nazwadruzyny1;
	}

	public void setNazwadruzyny1(String nazwadruzyny1) {
		this.nazwadruzyny1 = nazwadruzyny1;
	}

	public String getNazwadruzyny2() {
		return this.nazwadruzyny2;
	}

	public void setNazwadruzyny2(String nazwadruzyny2) {
		this.nazwadruzyny2 = nazwadruzyny2;
	}

	public int getWynik() {
		return this.wynik;
	}

	public void setWynik(int wynik) {
		this.wynik = wynik;
	}

	public List<Zaklady> getZakladies() {
		return this.zakladies;
	}

	public void setZakladies(List<Zaklady> zakladies) {
		this.zakladies = zakladies;
	}

	public Zaklady addZaklady(Zaklady zaklady) {
		getZakladies().add(zaklady);
		zaklady.setMecze(this);

		return zaklady;
	}

	public Zaklady removeZaklady(Zaklady zaklady) {
		getZakladies().remove(zaklady);
		zaklady.setMecze(null);

		return zaklady;
	}

}