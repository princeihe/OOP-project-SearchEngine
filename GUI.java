import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUI extends JFrame {
	
	
	public GUI() {
		JFrame f1 = new JFrame("Machine Learning GUI");
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JLabel l1 = new JLabel("Welcome to my Machine Learning GUI");
		JButton submit = new JButton("Submit data");
		
		// text fields to receive user input
		
		JTextField gender = new JTextField("Male or Female?");
		gender.addFocusListener(new FocusListener() {
		    
		    public void focusGained(FocusEvent e) {
		        gender.setText("");
		    }
		    
		    public void focusLost(FocusEvent e) {
		        if (gender.getText().equals("")) {
		            gender.setText("Male or Female?");
		        }
		    }
		});
		
		JTextField parent_business = new JTextField("Parent / guardian has own business");
		parent_business.addFocusListener(new FocusListener() {

		    public void focusGained(FocusEvent e) {
		        parent_business.setText("");
		    }

		    public void focusLost(FocusEvent e) {
		        if (parent_business.getText().equals("")) {
		            parent_business.setText("Parent / guardian has own business");
		        }
		    }
		});
		
		JTextField part_time_job = new JTextField("Does the student have a part time job?");
		part_time_job.addFocusListener(new FocusListener() {

		    public void focusGained(FocusEvent e) {
		        part_time_job.setText("");
		    }

		    public void focusLost(FocusEvent e) {
		        if (part_time_job.getText().equals("")) {
		            part_time_job.setText("Does the student have a part time job?");
		        }
		    }
		});
		
		JTextField urban_or_rural = new JTextField("Does the student have an urban or rural address?");
		urban_or_rural.addFocusListener(new FocusListener() {
		    public void focusGained(FocusEvent e) {
		        urban_or_rural.setText("");
		    }

		    public void focusLost(FocusEvent e) {
		        if (urban_or_rural.getText().equals("")) {
		            urban_or_rural.setText("Does the student have an urban or rural address?");
		        }
		    }
		});
		
		JTextField studies_business = new JTextField("Does the student study a business subject?");
		studies_business.addFocusListener(new FocusListener() {

		    public void focusGained (FocusEvent e) {
		        studies_business.setText("");
		    }

		    public void focusLost (FocusEvent e) {
		    	if (studies_business.getText().equals("")) {
		            studies_business.setText("Does the student study a business subject?");
		        }
		    }
		});
		

		submit.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	String genderInput = gender.getText();
				String parentBusinessInput = parent_business.getText();
				String partTimeJobInput = part_time_job.getText();
				String urbanOrRuralInput = urban_or_rural.getText();
				String studiesBusinessInput = studies_business.getText();
				
				System.out.println("Male or Female: " + genderInput);
				System.out.println("Parent owns a business: " + parentBusinessInput);
				System.out.println("Student has a part time job: " + partTimeJobInput);
				System.out.println("Student has a rural or urban address: " + urbanOrRuralInput);
				System.out.println("Student studies a business subject: " + studiesBusinessInput);

		    }
		});

				
		f1.setLayout(new GridLayout(2, 3));
		
		f1.setVisible(true);
		f1.setSize(500, 500);
		
		f1.add(p1);
		f1.add(p2);
		
		p1.setBackground(Color.green);
		p2.setBackground(Color.green);
		p1.add(l1);
		p2.add(gender);
		p2.add(parent_business);
		p2.add(part_time_job);
		p2.add(urban_or_rural);
		p2.add(studies_business);
		p2.add(submit);

	}
}

