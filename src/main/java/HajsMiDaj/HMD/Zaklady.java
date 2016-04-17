package HajsMiDaj.HMD;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Zaklady database table.
 * 
 */
@Entity
@NamedQuery(name="Zaklady.findAll", query="SELECT z FROM Zaklady z")
public class Zaklady implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idtable1;

	private int rezultat;

	private int stawka;

	private int typ;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="Users_idUsers")
	private User user;

	//bi-directional many-to-one association to Mecze
	@ManyToOne
	private Mecze mecze;

	public Zaklady() {
	}

	public int getIdtable1() {
		return this.idtable1;
	}

	public void setIdtable1(int idtable1) {
		this.idtable1 = idtable1;
	}

	public int getRezultat() {
		return this.rezultat;
	}

	public void setRezultat(int rezultat) {
		this.rezultat = rezultat;
	}

	public int getStawka() {
		return this.stawka;
	}

	public void setStawka(int stawka) {
		this.stawka = stawka;
	}

	public int getTyp() {
		return this.typ;
	}

	public void setTyp(int typ) {
		this.typ = typ;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Mecze getMecze() {
		return this.mecze;
	}

	public void setMecze(Mecze mecze) {
		this.mecze = mecze;
	}

}