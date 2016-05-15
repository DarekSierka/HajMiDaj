package HajsMiDaj.HMD;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import HajsMiDaj.HMD.Register.Listener;

import javax.swing.JButton;

public class Online {

	private JFrame okno = new JFrame();
	
	private JLabel obraz;
	private JLabel logo;
	
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
		
		obraz.add(wyloguj);
		obraz.add(navbar1);
		obraz.add(navbar2);
		obraz.add(navbar3);
		obraz.add(logo);
		
		return true;
	}
	
	private void wylogujClicked(){
		
		JOptionPane.showMessageDialog(null,"Wylogowano!");
		new Okno();
		okno.dispose();
	}
	
	private void mojeZakladyClicked(){
		
	}
	
	private void postawZakladClicked(){
		
	}
	
	private void tabelaRozgrywekClicked(){
		
	}
	
	private boolean setListenners(){
		wyloguj.addActionListener(new Listenner());
		navbar1.addActionListener(new Listenner());
		navbar2.addActionListener(new Listenner());
		navbar3.addActionListener(new Listenner());
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
		} 
	}	
	
}
