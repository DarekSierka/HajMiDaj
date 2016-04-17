package HajsMiDaj.HMD;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Online {

	private JFrame okno = new JFrame();
	
	public Online(JFrame o){
		
		o.dispose();
		
		okno.setSize(400, 400);
		
		JLabel obraz= new JLabel(new ImageIcon("pictures/H.png"));
		obraz.setVisible(true);
		
		okno.add(obraz);
		okno.setLocationRelativeTo(null);
		okno.setVisible(true);
	}
	
}
