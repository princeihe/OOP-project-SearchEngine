import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.*;
import java.io.File;
import java.awt.event.*;
import java.util.List;

public class GUI extends JFrame {

	public GUI() throws FileNotFoundException {
		// create a new JFrame
		JFrame f1 = new JFrame("My search Engine");
		JPanel p1 = new JPanel();
		JTextArea fileContents = new JTextArea(20, 40);
		JTextField searchTextField = new JTextField(20);
		JButton searchButton = new JButton("Search");
		JButton chooseFileButton = new JButton("Choose File and Search");

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
							String line = scanner.nextLine();
							if (line.contains(searchTextField.getText())) {
								count++;
							}
						}
						scanner.close();
						double percentage = ((double) count / getTotalWordsInFile(selectedFile)) * 100;
						fileContents.append("Selected file: " + selectedFile.getName() + "\n");
						fileContents.append(String.format("Percentage of words containing the word '%s': %.2f%%", searchTextField.getText(), percentage));
					} catch (FileNotFoundException ex) {
						ex.printStackTrace();
					}
				}
			}
		});

		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] searchTerms = searchTextField.getText().split("\\s+");
				File directory = new File("files");
				File[] files = directory.listFiles();
				List<String[]> results = new ArrayList<>();
				for (File file : files) {
					try {
						double percentage = getPercentage(file.getAbsolutePath(), searchTerms);
						String[] result = new String[]{file.getName(), String.format("%.2f%%", percentage)};
						results.add(result);
					} catch (FileNotFoundException ex) {
						ex.printStackTrace();
					}
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
		});
	}


	private int getTotalWordsInFile(File file) throws FileNotFoundException {
		Scanner scanner = new Scanner(file);
		int count = 0;
		while (scanner.hasNext()) {
			scanner.next();
			count++;
		}
		scanner.close();
		return count;
	}

	private double getPercentage(String filePath, String[] searchTerms) throws FileNotFoundException {
		File file = new File(filePath);
		Scanner scanner = new Scanner(file);
		int totalWords = 0;
		int matchingWords = 0;
		while (scanner.hasNext()) {
			totalWords++;
			String word = scanner.next();
			for (String searchTerm : searchTerms) {
				if (word.equalsIgnoreCase(searchTerm)) {
					matchingWords++;
				}
			}
		}
		scanner.close();
		return ((double) matchingWords / totalWords) * 100;
	}

}
