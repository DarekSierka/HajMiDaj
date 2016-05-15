package HajsMiDaj.HMD;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTextField;

import HajsMiDaj.HMD.Okno.Lisener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Register {
	
	private JFrame okno = new JFrame();
	
	private JTextField nickname;
	private JTextField password;
	private JTextField password2;
	
	private JLabel nicknameLabel;
	private JLabel passwordLabel;
	private JLabel password2Label;
	private JLabel obraz = new JLabel(new ImageIcon("pictures/H.png"));
	
	private JButton potwierdz;
	private JButton anuluj;
	
	private int oknoX = 400;
	private int oknoY = 400;
	
	public Register(JFrame o){
		o.dispose();
		
		
		
		nickname = new JTextField();
		password = new JTextField();
		password2 = new JTextField();
		
		nicknameLabel= new JLabel();
		passwordLabel= new JLabel();
		password2Label= new JLabel();
		
		potwierdz = new JButton("Potwierdz");
		anuluj = new JButton("Anuluj");
		if(setParam()==true){
			System.out.println("Parametry ustawione.");
		}
		if(setForm()==true){
			System.out.println("Formularz załadowany.");
		}
		if(setListenners()==true){
			System.out.println("Listennery załadowane.");
		}
	
		okno.setLocationRelativeTo(null);
		okno.setVisible(true);
	
	}
	
	private boolean setParam(){
		okno.setSize(oknoX,oknoY);
		okno.setResizable(false);
		okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		obraz.setBounds(0, 0, oknoX, oknoY);
		obraz.setVisible(true);
		okno.add(obraz);
		
		return true;
	}
	
	private boolean setForm(){
		
		Font czcionka = new Font("Times New Roman", Font.BOLD, 24);
		int base=50, skok=75,height=25,width=oknoX/2,left=oknoX/4;
		String textColor= "#ffffff";
		
		System.out.println(width +" "+ left + " ");
		
		nicknameLabel.setText("<html><font color='" + textColor + "'> Podaj swój nick: </font></html>");
		nicknameLabel.setFont(czcionka);
		nicknameLabel.setBounds(left,base,width,height);
		
		passwordLabel.setText("<html><font color='" + textColor + "'> Podaj hasło: </font></html>");
		passwordLabel.setFont(czcionka);
		passwordLabel.setBounds(left,base+skok, width,height);
		
		password2Label.setText("<html><font color='" + textColor + "'> Potwierdz hasło: </font></html>");
		password2Label.setFont(czcionka);
		password2Label.setBounds(left,base+2*skok,width,height);
		
		nickname.setBounds(left,base+nicknameLabel.getHeight(),width,height);
		
		password.setBounds(left,base+skok+passwordLabel.getHeight(),width,height);
		
		password2.setBounds(left,base+2*skok+password2Label.getHeight(),width,height);

		potwierdz.setBounds(left-width/4, 4*skok, width/2,height);
		potwierdz.setVisible(true);
		
		anuluj.setBounds(left+width*3/4, 4*skok, width/2, height);
		anuluj.setVisible(true);
		
		obraz.add(anuluj);
		obraz.add(potwierdz);
		obraz.add(nicknameLabel);
		obraz.add(passwordLabel);
		obraz.add(password2Label);
		obraz.add(nickname);
		obraz.add(password);
		obraz.add(password2);
	
		return true;
	}
	
	private boolean checkData(String nick,String pass,String pass2){
		
		if((nick.length()==0) || (pass.length()==0) || (pass2.length()==0)){
			JOptionPane.showMessageDialog(null,"Wypełnij wszystkie pola!");
			return false;
		}
		if(nick.length()>15 || nick.length()<5){
			JOptionPane.showMessageDialog(null,"Długość nazwy użytkownika musi się zawierać w przedziale od 5 do 15 znaków!");
			return false;
		}
		String pattern = "^[a-zA-Z0-9]*$";
		if(!(nick.matches(pattern))){
			JOptionPane.showMessageDialog(null,"Nazwa użytkownika nie może zawierać znaków specjalnych ani polskich liter!");
			return false;
		}
		if(pass.length()>15 || pass.length()<5){
			JOptionPane.showMessageDialog(null,"Długość hasła musi się zawierać w przedziale od 5 do 15 znaków!");
			return false;
		}
		
		if(!(pass.equals(pass2))){
			JOptionPane.showMessageDialog(null,"Oba hasła muszą się zgadzać!");
			return false;
		}
		return true;
	}
	
	private void addUser(String nick,String pass){
		System.out.println("Kontrola wprowadzania danych zakończona powodzeniem!");
		long liczba = 0;
		
		MysqlTransaction transaction = new MysqlTransaction();
		
		String polecenie = "Select Count(*) from User where nick like '"+nick+"'";
		liczba = (long) transaction.getSession().createQuery(polecenie).uniqueResult();
		
		if(liczba > 0){
			JOptionPane.showMessageDialog(null,"Istnieje osoba o padanym niku!");
			transaction.finalizeSession();
			return;
		}
		
		polecenie = "Select MAX(id) from User";
		
		User nowy = new User();
		
		nowy.setImie("Imie");
		nowy.setNazwisko("Nazwisko");
		nowy.setHaslo(pass);
		nowy.setNazwa(nick);
		nowy.setKto(0);
		nowy.setKasa(50);
		nowy.setIdUsers((int)transaction.getSession().createQuery(polecenie).uniqueResult()+1);
		
		transaction.save(nowy);
		
		transaction.finalizeSession();
	}
	
	private void potwierdzClicked(){
		String nick=nickname.getText(), pass=password.getText(), pass2=password2.getText();
		if(checkData(nick,pass,pass2)==false)
			return;
		else
		{
			addUser(nick,pass);
			JOptionPane.showMessageDialog(null,"Konto zostało utworzone. Życzymy miłego użytkowania!");
			new Okno();
			okno.dispose();
		}
	}
	
	private void anulujClicked(){
		new Okno();
		okno.dispose();
	}
	
	private boolean setListenners(){
		potwierdz.addActionListener(new Listener());
		anuluj.addActionListener(new Listener());
		return true;
	}
		
	class Listener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
	      	
	      	if(e.getActionCommand().equals("Potwierdz")){
	      		potwierdzClicked();
	      	}
	      	else if(e.getActionCommand().equals("Anuluj")){
	      		anulujClicked();
	      	}
	      	      	
		} 
	}	
	
}
