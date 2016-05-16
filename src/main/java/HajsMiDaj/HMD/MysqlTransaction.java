package HajsMiDaj.HMD;
import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class MysqlTransaction {
	
	private  SessionFactory sessionFactory; 
	private Session session;
	
	public MysqlTransaction(){
		this.sessionFactory =  new AnnotationConfiguration().configure().buildSessionFactory();
		this.session = sessionFactory.openSession();
		this.session.beginTransaction();
	}
	public void finalizeSession(){
		this.session.getTransaction().commit();
		this.session.close();
	}
	public void save(Object object){
		this.session.save(object);
	}
	public Session getSession(){
		return this.session;
	}
	
	public boolean isUser(String nick) throws Throwable{
		long liczba=0;

		String polecenie = "Select Count(*) from User where nazwa like '"+nick+"'";
		liczba = (long) getSession().createQuery(polecenie).uniqueResult();
		
		if(liczba>0)
			return true;
		else
			return false;
		
	}
}
/*
 * Tą klasą możemy pobrać dane z bazy danych jak to zrobić a następująco 
 *  
 * 
 * 				private ArrayList<User> user = new ArrayList<User>();
 * 					user= (ArrayList<User>)transaction.getSession().createCriteria(User.class).list();
 * 
 * i w tym momencie posiadamy w zmiennej user wszystkie dane z tabeli USERS (w bazie wiem klasa się nazywa inaczej niż w bazie, ciekawa anomalia )
 * 
 * MysqlTransaction transaction; załużmy że to zmienna w klasie
 * 
 * jak otworzyć połączenie ? 
 * 
 * public void start(){
 * 		this.transaction = new MysqlTransaction();
		System.out.println("Inicjuję");
		
		user= (ArrayList<User>)transaction.getSession().createCriteria(User.class).list();
		// pobieram tabele do list
		
		this.transaction.finalizeSession();
		System.out.println("I'm done");
		}
 * 
 * 
 * jak dodać coś do tabeli ?
 * 
 * public void adduser(){
 * 		...
 * 
 * 		this.transaction = new MysqlTransaction();
 * 		user= (ArrayList<User>)transaction.getSession().createCriteria(User.class).list();
 * 		
 * 
 * 		...
 * 
 * 
 * 		User nowy = new User();
		id++;
		
		nowy.setHaslo(haslo);
		nowy.setIdUsers(id);
		nowy.setImie(imie);
		nowy.setNazwisko(nazwisko);
		nowy.setNick(nick);
 * 
 * 		user.add(nowy);           ----- linikja w której dodajemy 
 * 
 *  
 *  	transaction.save(nowy);   ----- i wysyłamy do bazy delikwenta
 * 
 * 		this.transaction.finalizeSession();
 * 
 * }
 * 
 * */
 