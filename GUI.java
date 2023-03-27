import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUI extends JFrame {
	
	
	public GUI() {
		JFrame f1 = new JFrame("Machine Learning GUI");
		JPanel p1 = new JPanel();
		JLabel l1 = new JLabel("Welcome to my Machine Learning GUI");
		
		f1.setVisible(true);
		f1.setSize(700, 700);
		
		f1.add(p1);
		
		p1.add(l1);
	}
}
