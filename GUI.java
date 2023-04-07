import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.*;
import java.io.File;
import java.awt.event.*;
import java.util.List;

public class GUI extends JFrame {

	public GUI() throws FileNotFoundException {
		// GUI Components
		JFrame f1 = new JFrame("Prince's Search Engine");
		JPanel p1 = new JPanel();
		JTextArea fileContents = new JTextArea(20, 40);
		JTextField searchTextField = new JTextField(20);
		JButton searchButton = new JButton("Search");
		JButton chooseFileButton = new JButton("Choose File and Search");

		// Menu bar
		JMenuBar menuBar = new JMenuBar();
		JMenu about = new JMenu("About");
		JMenu help = new JMenu("Help");
		JMenu exit = new JMenu("Exit");

		menuBar.add(about);
		menuBar.add(help);
		menuBar.add(exit);


		about.addMenuListener(new MenuListener() {
			public void menuSelected(MenuEvent e) {
				JOptionPane.showMessageDialog(null, "This is a search engine application created by Prince Ihekwereme that allows you to search for words contained within text files in the project or within your local machine");
			}
			public void menuDeselected(MenuEvent e) {}
			public void menuCanceled(MenuEvent e) {}
		}); // end about menu listener

		help.addMenuListener(new MenuListener() {
			public void menuSelected(MenuEvent e) {
				JOptionPane.showMessageDialog(null, "Type in a word or phrase then press search to search for that word or phrase within the text files included in the application, or press choose a file and search to search a text file on your local machine.");
			}
			public void menuDeselected(MenuEvent e) {}
			public void menuCanceled(MenuEvent e) {}
		}); // end help menu listener

		exit.addMenuListener(new MenuListener() {
			public void menuSelected(MenuEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Exit Confirmation", JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
			public void menuDeselected(MenuEvent e) {}
			public void menuCanceled(MenuEvent e) {}
		}); // end exit menu listener

		// GUI components
		f1.setJMenuBar(menuBar);
		p1.add(new JLabel("Search Term:"));
		p1.add(searchTextField);
		p1.add(searchButton);
		p1.add(chooseFileButton);
		p1.add(new JScrollPane(fileContents));

		// add the panel to the JFrame and show the window
		f1.add(p1);
		f1.setLayout(new GridLayout(2, 3));
		f1.pack();
		f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f1.setVisible(true);
		f1.setSize(500, 500);

		// read the files and search for the word
		chooseFileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				int returnValue = fileChooser.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					try {
						fileContents.setText("");
						Scanner scanner = new Scanner(selectedFile);
						int count = 0;
						while (scanner.hasNextLine()) {
							String target = scanner.next();
							if (target.equals(searchTextField.getText())) {
								count++;
							} // end if
						} // end while
						scanner.close();
						double percentage = ((double) count / getTotalWordsInFile(selectedFile)) * 100;
						fileContents.append("Selected file: " + selectedFile.getName() + "\n");
						fileContents.append(String.format("Percentage of words containing the word '%s': %.2f%%", searchTextField.getText(), percentage));
					} catch (FileNotFoundException ex) {
						ex.printStackTrace();
					} // end catch
				}
			}
		}); // end chooseFileButton action listener

		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] searchTerms = searchTextField.getText().split("\\s+");
				File directory = new File("files");
				File[] files = directory.listFiles();
				List<String[]> results = new ArrayList<>();
				for (File file : files) {
					try {
						// calling getPercentage function from PercentageCalculator Class to get percentage
						double percentage = PercentageCalculator.getPercentage(file.getAbsolutePath(), searchTerms);
						String[] result = new String[]{file.getName(), String.format("%.2f%%", percentage)};
						results.add(result);
					} catch (FileNotFoundException ex) {
						ex.printStackTrace();
					} // end catch
				}
				Collections.sort(results, new Comparator<String[]>() {
					public int compare(String[] a, String[] b) {
						return Double.compare(Double.parseDouble(b[1].replace("%", "")), Double.parseDouble(a[1].replace("%", "")));
					}
				});
				fileContents.setText("");
				for (String[] result : results) {
					fileContents.append(result[0] + ": " + result[1] + "\n");
				}
			}
		}); // end searchButton action listener
	}

	private int getTotalWordsInFile(File file) throws FileNotFoundException {
		Scanner scanner = new Scanner(file);
		int count = 0;
		while (scanner.hasNext()) {
			scanner.next();
			count++;
		} // end while
		scanner.close();
		return count;
	} // end getTotalWordsInFile
} // end GUI class
