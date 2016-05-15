package HajsMiDaj.HMD;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import HajsMiDaj.HMD.Register.Listener;

import javax.swing.JButton;

public class Online {

	private JFrame okno = new JFrame();
	
	private JLabel obraz;
	private JLabel logo;
	
	private JTable tabelaRozgrywek;
	private JTable zakladyUzytkownika;

	private JComboBox mecz;
	private JComboBox typ;
	private JLabel kurs;
	private JTextField stawka;
	private JButton zatwierdz;
	
	private JButton wyloguj;
	private JButton navbar1;
	private JButton navbar2;
	private JButton navbar3;
	
	private int oknoX = 1024;
	private int oknoY = 600;
	
	public Online(JFrame o){
		
		o.dispose();
		
		obraz= new JLabel(new ImageIcon("pictures/ball.jpg"));
		
		logo= new JLabel(new ImageIcon("pictures/Fox-rounded-small.png"));
		
		wyloguj = new JButton("Wyloguj");
		navbar1 = new JButton("Moje zakłady");
		navbar2 = new JButton("Postaw zakład");
		navbar3 = new JButton("Tabela rozgrywek");
		
		
		if(setParams()){
			System.out.println("Ustaweinie parametrów zakończone powodzeniem!");
		}
		if(setElements()){
			System.out.println("Ustawienie elementów zakończone powodzeniem!");
		}
		if(setListenners()){
			System.out.println("Ustawianie listennerów zakończone powodzeniem!");
		}
		
		
	}
	
	private boolean setParams(){
		okno.setSize(oknoX,oknoY);
		okno.setLocationRelativeTo(null);
		okno.setVisible(true);
		
		obraz.setBounds(0, 0, oknoX, oknoY);
		obraz.setVisible(true);
		okno.add(obraz);
		
		return true;
	}
	
	private boolean setElements(){
		int buttonsWidth = 150, buttonsHeight = 30;
		int navbarX=30, navbarY=120;
		logo.setBounds(10, 10, 180, 100);
		logo.setVisible(true);
		
		wyloguj.setBounds(oknoX - buttonsWidth - 50, 10, buttonsWidth,buttonsHeight);
		wyloguj.setVisible(true);
		
		navbar1.setBounds(navbarX, navbarY, buttonsWidth, buttonsHeight);
		navbar2.setBounds(navbarX,navbarY+buttonsHeight,buttonsWidth,buttonsHeight);
		navbar3.setBounds(navbarX, navbarY+ 2*buttonsHeight, buttonsWidth, buttonsHeight);
		
		tabelaRozgrywek = new JTable();
		tabelaRozgrywek.setVisible(false);
		obraz.add(tabelaRozgrywek);
		
		zakladyUzytkownika = new JTable();
		zakladyUzytkownika.setVisible(false);
		obraz.add(zakladyUzytkownika);
		
		kurs = new JLabel();
		kurs.setVisible(false);
		kurs.setBounds(255, 355, 150, 30);
		obraz.add(kurs);
		
		stawka = new JTextField();
		stawka.setBounds(255, 415, 150, 30);
		stawka.setVisible(false);
		obraz.add(typ);
		
		zatwierdz = new JButton();
		zatwierdz.setBounds(255,475,450,30);
		zatwierdz.setVisible(false);
		obraz.add(zatwierdz);
		
		obraz.add(wyloguj);
		obraz.add(navbar1);
		obraz.add(navbar2);
		obraz.add(navbar3);
		obraz.add(logo);
		
		return true;
	}
		
	
	private void wyczyscContent(){
		zakladyUzytkownika.setVisible(false);
		tabelaRozgrywek.setVisible(false);
		mecz.setVisible(false);
		typ.setVisible(false);
		kurs.setVisible(false);
		stawka.setVisible(false);
		zatwierdz.setVisible(false);
	}
	
	private void wylogujClicked(){
		
		JOptionPane.showMessageDialog(null,"Wylogowano!");
		new Okno();
		okno.dispose();
	}
	
	private void mojeZakladyClicked(){
		wyczyscContent();
		String[] headers = {
				"Gospodarze",
				"Goscie",
				"Typ",
				"Stawka",
				"Kurs"
		};
		//do tablicy data trzeba pobrać dane z bazy danych
		Object[][] data = {
				{"GKS Tarnovia","Warta Międzychód", "1","14.00","1.72"},
				{"Sparta Oborniki","Iskra Szydłowo", "1X","2.00", "1.81"},
				{"Lubuszanin Trzcianka","GKS Dopiewo", "X2","4.50","2.16"},
				{"Mieszko Gniezno","Pelikan Niechanowo", "2","35.00", "1.32"}
		};
		zakladyUzytkownika = new JTable(data,headers);
		zakladyUzytkownika.setBounds(250,120, 700, 400);
		zakladyUzytkownika.setVisible(true);
	}
	
	private void postawZakladClicked(){
		wyczyscContent();
		//do tablicy data trzeba pobrać dane z bazy danych
		String[] data = {
				"GKS Tarnovia - Warta Międzychód",
				 "Sparta Oborniki - Iskra Szydłowo",
				 "Lubuszanin Trzcianka - GKS Dopiewo",
				 "Mieszko Gniezno - Pelikan Niechanowo"
		}; 
		mecz = new JComboBox<String>(data);
		mecz.setBounds(255,255,150,30);
		mecz.setVisible(true);
		
		String[] data2 = {
				"1",
				"1X",
				"X",
				"X2",
				"2"
		};
		typ = new JComboBox<String>(data2);
		typ.setBounds(255,295,150,30);
		typ.setVisible(false);
		
		kurs.setText("");
		kurs.setVisible(true);
		stawka.setVisible(true);
		zatwierdz.setVisible(true);
		obraz.add(mecz);
		obraz.add(typ);
	}
	
	private void tabelaRozgrywekClicked(){
		wyczyscContent();
		String[] headers = {
				"LP",
				"Nazwa",
				"Punkty",
				"Zwyciestwa",
				"Remisy",
				"Porazki",
				"Bilans bramek"
		};
		//do tablicy data trzeba dodać dane z bazy danych
		Object[][] data = {
				{"1","GKS Tarnovia", "56","17","5","2","+31"},
				{"2","Warta Międzychód", "49","14","7","3","+19"},
				{"3","Iskra Szydłowo", "48","15","3","6","+24"},
				{"4","Lubuszanin Trzcianka", "45","13","6","5","+12"},
				{"5","GKS Dopiewo", "44","14","2","8","+7"},
				{"6","Sparta Oborniki", "42","12","8","4","+4"}
		};
		tabelaRozgrywek = new JTable(data,headers);
		tabelaRozgrywek.setBounds(250,120, 700, 400);
		tabelaRozgrywek.setVisible(true);
	}
	
	private void zatwierdzClicked(){
		//sprawdzenie hajsów
		//sprawdzanie czy nie obstawił już tego meczu
		//dodanie zakładu
		JOptionPane.showMessageDialog(null,"Dodano nowy zakład!");
	}
	
	private boolean setListenners(){
		wyloguj.addActionListener(new Listenner());
		navbar1.addActionListener(new Listenner());
		navbar2.addActionListener(new Listenner());
		navbar3.addActionListener(new Listenner());
		zatwierdz.addActionListener(new Listenner());
		return true;
	}
		
	class Listenner implements ActionListener{
		public void actionPerformed(ActionEvent e) {
	      	
	      	if(e.getActionCommand().equals("Wyloguj"))
	      		wylogujClicked();
	      	else if(e.getActionCommand().equals("Moje zakłady"))
	      		mojeZakladyClicked();
	      	else if(e.getActionCommand().equals("Postaw zaklad"))
	      		postawZakladClicked();
	      	else if(e.getActionCommand().equals("Tabela rozgrywek"))
	      		tabelaRozgrywekClicked();
	      	else if(e.getActionCommand().equals("Zatwierdz"))
	      		zatwierdzClicked();
		} 
	}	
	
}
