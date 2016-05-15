package HajsMiDaj.HMD;

import static org.junit.Assert.*;

import javax.swing.JFrame;

import org.junit.Test;

public class RegisterTest {

public RegisterT obiekt;
	
	@org.junit.Before
	public void Bef(){
		obiekt = new RegisterT(new JFrame());
	}
	
	
	@Test
	public void testCheckData(){
			
		String pass = "passs";
		String nick = "nickk";
		
		assertEquals(true, obiekt.checkData(nick, pass, pass));
		
	}
	@Test
	public void testCheckData1(){
		
		String pass = "passs";
		String nick = "nickk";
		
		assertEquals(false, obiekt.checkData(nick, pass, pass+"a"));
		
	}
	@Test
	public void testCheckData2(){
		
		String pass = "passs";
		String nick = "nick";
		
		assertEquals(false, obiekt.checkData(nick, pass, pass));
		
	}
	@Test
	public void testCheckData3(){
		
		String pass = "pass";
		String nick = "nickk";
		
		assertEquals(false, obiekt.checkData(nick, pass, pass));
		
	}
	@Test
	public void testCheckData4(){
		
		String pass = "";
		String nick = "";
		
		assertEquals(false, obiekt.checkData(nick, pass, pass));
		
	}

}
