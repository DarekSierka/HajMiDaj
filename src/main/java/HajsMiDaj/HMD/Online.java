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
	
	private int oknoX = 1024;
	private int oknoY = 600;
	
	public Online(JFrame o){
		
		o.dispose();
		
		obraz= new JLabel(new ImageIcon("pictures/ball.jpg"));
		
		logo= new JLabel(new ImageIcon("pictures/Fox-rounded-small.png"));
		
		wyloguj = new JButton("Wyloguj");
		
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
		int buttonsWidth = 100, buttonsHeight = 30;
		
		logo.setBounds(10, 10, 180, 100);
		logo.setVisible(true);
		
		wyloguj.setBounds(oknoX - buttonsWidth - 50, 10, buttonsWidth,buttonsHeight);
		wyloguj.setVisible(true);
		
		obraz.add(wyloguj);
		obraz.add(logo);
		
		return true;
	}
	
	private void wylogujClicked(){
		
		JOptionPane.showMessageDialog(null,"Wylogowano!");
		new Okno();
		okno.dispose();
	}
	
	private boolean setListenners(){
		wyloguj.addActionListener(new Listener());
		return true;
	}
		
	class Listener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
	      	
	      	if(e.getActionCommand().equals("Wyloguj")){
	      		wylogujClicked();
	      	}
	      	      	
		} 
	}	
	
}
