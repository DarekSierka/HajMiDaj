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

	private String druzynawygywajaca;

	private int stawka;

	//bi-directional many-to-one association to Mecze
	@ManyToOne
	private Mecze mecze;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="Users_idUsers")
	private User user;

	public Zaklady() {
	}

	public int getIdtable1() {
		return this.idtable1;
	}

	public void setIdtable1(int idtable1) {
		this.idtable1 = idtable1;
	}

	public String getDruzynawygywajaca() {
		return this.druzynawygywajaca;
	}

	public void setDruzynawygywajaca(String druzynawygywajaca) {
		this.druzynawygywajaca = druzynawygywajaca;
	}

	public int getStawka() {
		return this.stawka;
	}

	public void setStawka(int stawka) {
		this.stawka = stawka;
	}

	public Mecze getMecze() {
		return this.mecze;
	}

	public void setMecze(Mecze mecze) {
		this.mecze = mecze;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}