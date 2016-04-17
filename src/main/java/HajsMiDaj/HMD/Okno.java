package HajsMiDaj.HMD;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import HajsMiDaj.HMD.MysqlTransaction;


/**
 * Klasa zarządzająca głównym oknem programu. Głównym zastosowaniem klasy jest ustawienie parametrów okna, tworzenie początkowego 
 * układu pól, przycisków oraz innych elementów, a także obsługa logowania użytkowników. Posiada dostęp do możliwości klasy JFrame, z której
 * stanowi rozszerzenie. Na premierę wersji "Sprint #2" przewiduje się znaczną rozbudowę klasy.
 * 
 * @see JFrame
 * @author Darek Sierka, Filip Borowski, Bartosz Dylewski
 */
public class Okno extends JFrame{

	private JFrame okno=null;
	
	MysqlTransaction transaction;
	
	private JButton login;
	private JButton createaccount;

	private JTextField name;
	private JTextField pas;
	
	private JLabel obraz = new JLabel(new ImageIcon("pictures/Fox.png"));
	
	private int sizeX=700;
	private int sizeY=400;
	
	/**
	 * Podstawowy konstruktor klasy.
	 * Konstruktor odpowiedzialny jest za utworzenie obiektu JFrame (okna programu), a także przycisków i pól tekstowych, widocznych na oknie
	 * od początku pracy programu.
	 */
	public Okno(){
		
		okno = new JFrame("Zaloguj się!");
		
		login=new JButton("Log in");
		
		name = new JTextField("Nick");
		pas = new JTextField("Password");
		
		
		//loadOkno();
		setParameters();
		setElements();
		setLisenners();
		
		okno.setLocationRelativeTo(null);
		okno.setVisible(true);
	}
	
	/**
	 * Metoda zarządzająca ustawieniami okna, między innymi jego rozmiarami oraz możliwością ich edycji. Jest ona odpowiedzialna za 
	 * dodawanie nowych elementów do okna programu.
	 * 
	 */
	public void setParameters(){
		okno.setSize(sizeX, sizeY);
		okno.setResizable(false);
        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
		obraz.setBounds(0, 0, sizeX, sizeY);
		obraz.setVisible(true);
		okno.add(obraz);
        
	}
	
	/**
	 * Metoda zarządzająca parametrami elementów obecnych na oknie programu,
	 * między innymi pól tekstowych, przycisków oraz grafik.
	 */
	public void setElements(){
		
		int width=120;
		int height = 30;
		int Y=100;
		sizeX=sizeX-500;
		name.setBounds((sizeX/2)-width/2,Y , width,height);
		pas.setBounds((sizeX/2)-width/2,Y+50 , width,height);
		login.setBounds((sizeX/2)-width/2,Y+100 , width,height);
		
		JLabel o= new JLabel();
		o.setVisible(false);
		
		obraz.add(name);
		obraz.add(pas);
		obraz.add(login);
		obraz.add(o);
		
	}
	
	public void setLisenners(){
		login.addActionListener(new Lisener());
	}
	
/*
	public void loadOkno(){
		setParameters();
		setElements();
		setLisenners();
		
		okno.setLocationRelativeTo(null);
		okno.setVisible(true);
	}*/
	
	/**
	 * Metoda zarządzająca logowaniem do systemu. Korzysta ona z pojedynczej transakcji w celu sprawdzenia czy 
	 * podany użytkownik istnieje w bazie danych oraz czy podane hasło jest prawidłowe.
	 */
	public void logging(){
		String nazwa=name.getText();
		String haslo=pas.getText();
		if(nazwa.length()==0||haslo.length()==0){
			JOptionPane.showMessageDialog(null,"Brak wymaganych danych!");
			return;
		}
		System.out.println(nazwa);
		System.out.println(haslo);
		
		transaction = new MysqlTransaction();
		
		ArrayList<User> user = new ArrayList<User>();
		user= (ArrayList<User>)transaction.getSession().createCriteria(User.class).list();
		
		User logUser=null;
		
		for(User u:user){
			if(nazwa.equals(u.getNazwa())&&haslo.equals(u.getHaslo())){
				logUser=u;
				break;
			}
		}
		if(logUser==null){
			JOptionPane.showMessageDialog(null,"Błędne hasło lub nazwa użytkownika");
			return;
		}
		
		new Online(okno);
		
	}
	
	
	
class Lisener implements ActionListener{
	public void actionPerformed(ActionEvent e) {
      	
      	if(e.getActionCommand().equals("Log in")){
      		logging();
      	}
      	      	
	} 
}
	
}