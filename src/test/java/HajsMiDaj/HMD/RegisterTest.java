package HajsMiDaj.HMD;


import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import javax.swing.JFrame;

import org.junit.Test;


public class RegisterTest {

	RegisterT obiekt;
	
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
	
	@Test
	public void testAddUser() throws Throwable{
		MysqlTransaction mock = mock(MysqlTransaction.class);
		when(mock.isUser("daras")).thenReturn(true);
		
		assertEquals(true,mock.isUser("daras"));
	}
	
	@Test
	public void testAddUser2() throws Throwable{
		
		MysqlTransaction mock = mock(MysqlTransaction.class);
		when(mock.isUser(anyString())).thenReturn(false);
		
		assertEquals(false,mock.isUser("cokolwiek"));
		
	}
}
