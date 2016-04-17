package HajsMiDaj.HMD;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import HajsMiDaj.HMD.MysqlTransaction;

/**
 * Klasa zapewniająca wypełnienie bazy danych danymi. 
 * Init jest klasą, której zadaniem jest początkowe zapełnienie bazy danych informacjami o:
 * <ul>
 * <li> użytkownikach </li>
 * <li> zakładach </li>
 * <li> rozgrywanych meczach </li>
 * </ul>
 * <p>
 * Obiekt tej klasy wywoływany jest na samym początku działania programu i nie zaleca się używania go pózniejszej edycji bazy danych,
 * jako że nie zawiera ona funkcji umożliwiających swobodną edycję bazy danych (jak na przykład usuwanie użytkowników lub ich edycja).
 * Każda operacja wykonywana w obrębie klasy init jest realizowana w ramach transakcji, co zapewnia spójność 
 * bazy danych. Tym samym klasa zapewnia, że w bazie danych nie wystąpią żadne anomalie(dirty-read, dirty-write etc.).
 * <p>
 * 
 * @author Dariusz Sierka
 * @author Filip Borowski
 * @author Bartosz Dylewski
 *
 */
public class Init {

	MysqlTransaction transaction;
	private ArrayList<User> user = new ArrayList<User>();
	private ArrayList<Zaklady> zaklady = new ArrayList<Zaklady>();
	private ArrayList<Mecze> mecze = new ArrayList<Mecze>();
	
	
	/**
	 * Konstruktor tworzący instancję klasy Init.
	 */
	public Init(){
		AddUser("Darek","Sierka","daras","darek1");
	}
	
	/**
	 * AddUser jest metodą klasy Init, dodającą użytkownika do bazy danych. 
	 * Cała operacja realizowanaj jest w ramach pojedyncznej transakcji. W przypadku istnienia 
	 * osoby o podanym nicku w bazie danych, metoda wyświetla stosowny komunikat i nie dodaje 
	 * użytkownika o tej samej nazwie.
	 * 
	 * @param imie	imię nowego użytkownika
	 * @param nazwisko nazwisko nowego użytkownika
	 * @param nick nazwa nowego użytkownika 
	 * @param haslo hasło nowego użytkownika
	 * 
	 * @see User
	 */
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
