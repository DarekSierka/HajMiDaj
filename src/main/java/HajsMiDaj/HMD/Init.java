package HajsMiDaj.HMD;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import HajsMiDaj.HMD.MysqlTransaction;



public class Init {

	MysqlTransaction transaction;
	private ArrayList<User> user = new ArrayList<User>();
	private ArrayList<Zaklady> zaklady = new ArrayList<Zaklady>();
	private ArrayList<Mecze> mecze = new ArrayList<Mecze>();
	
	public Init(){
		AddUser("Darek","Sierka","daras","darek1");
	}
	
	public void AddUser(String imie,String nazwisko,String nick,String haslo){
		System.out.println( "Takie tam teściki3!" );
		transaction = new MysqlTransaction();
		System.out.println( "Takie tam teściki4!" );
		user = (ArrayList<User>)transaction.getSession().createCriteria(User.class).list();
		zaklady= (ArrayList<Zaklady>)transaction.getSession().createCriteria(Zaklady.class).list();
		mecze = (ArrayList<Mecze>)transaction.getSession().createCriteria(Mecze.class).list();
	
		int id=0;
		for(User u:user){
			if(id<u.getIdUsers())
				id=u.getIdUsers();
			if(u.getNazwa().equals(nick)){
				JOptionPane.showMessageDialog(null,"Istnieje osoba o podanym nicku");
				transaction.finalizeSession();
				return;
			}				
		}
		
		User nowy = new User();
		id++;
		nowy.setIdUsers(id);
		nowy.setImie(imie);
		nowy.setNazwisko(nazwisko);
		nowy.setNazwa(nick);
		nowy.setHaslo(haslo);
		nowy.setKto(0);   // musimy ustalić 0-User 1-Premium 2-Admin nie da się ustawić boolean w bazie bo jest konwertowana na byte 
		nowy.setKasa(50);
		
		user.add(nowy);
		
		transaction.save(nowy);
		
		transaction.finalizeSession();
		
	}
}
