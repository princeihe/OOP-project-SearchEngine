import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class GUI extends JFrame{

	public GUI() {
		// create a new JFrame
		JFrame f1 = new JFrame("Search Engine");
		JPanel p1 = new JPanel();
		JTextArea fileContents = new JTextArea(20, 40);
		JTextField searchTextField = new JTextField(20);
		JButton searchButton = new JButton("Search");

		p1.add(new JLabel("Search Term:"));
		p1.add(searchTextField);
		p1.add(searchButton);
		p1.add(new JScrollPane(fileContents));

		// add the panel to the JFrame and show the window
		f1.add(p1);
		f1.setLayout(new GridLayout(2, 3));
		f1.pack();
		f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f1.setVisible(true);
		f1.setSize(500, 500);


		// create a new JFileChooser for selecting the search space

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setMultiSelectionEnabled(true);
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooser.setCurrentDirectory(new File("humpty.txt"));
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt");
		fileChooser.setFileFilter(filter);


		// add an ActionListener to the search button
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// get the search term from the text field
				String searchTerm = searchTextField.getText();

				// get the selected folder from the file chooser
				java.io.File[] selectedFiles = fileChooser.getSelectedFiles();

				// create a list to hold the matches
				List<FileMatch> matches = new ArrayList<>();

				// get a list of all the text files in the selected folder
				for (File selectedFile : selectedFiles) {
					if (selectedFile.isDirectory()) {
						File[] files = selectedFile.listFiles(new FilenameFilter() {
							public boolean accept(File dir, String name) {
								return name.toLowerCase().endsWith(".txt");
							}
						});

						// iterate through the list of files in the directory and search for the search term
						for (File file : files) {
							try {
								// read the contents of the file into a String
								String fileContent = new String(Files.readAllBytes(file.toPath()));
								// check if the file contains the search term
								if (fileContent.contains(searchTerm)) {
									// calculate the strength of the match
									int strength = calculateMatchStrength(fileContent, searchTerm);
									// add the match to the list
									matches.add(new FileMatch(file, strength));
								}
							} catch (IOException ex) {
								ex.printStackTrace();
							}
						}
					} else {
						try {
							// read the contents of the file into a String
							String fileContent = new String(Files.readAllBytes(selectedFile.toPath()));
							// check if the file contains the search term
							if (fileContent.contains(searchTerm)) {
								// calculate the strength of the match
								int strength = calculateMatchStrength(fileContent, searchTerm);
								// add the match to the list
								matches.add(new FileMatch(selectedFile, strength));
							}
						} catch (IOException ex) {
							ex.printStackTrace();
						}
					}
				}


				// sort the matches based on the strength of the match
				Collections.sort(matches, new Comparator<FileMatch>() {
					public int compare(FileMatch fm1, FileMatch fm2) {
						return Integer.compare(fm2.getStrength(), fm1.getStrength());
					}
				});

				// display the matches in the JTextArea
				for (FileMatch match : matches) {
					try {
						// read the contents of the file into a String
						String fileContent = new String(Files.readAllBytes(match.getFile().toPath()));
						// display the contents of the file in the JTextArea
						fileContents.append(match.getFile().getName() + " (Match Strength: " + match.getStrength() + "):\n" + fileContent + "\n");
					} catch (IOException ex) {
						ex.printStackTrace();
					}
				}
			}
		});
	}





	private void performSearch(String searchTerm, java.io.File[] selectedFiles) {

	}

	// helper method to calculate the strength of the match
	private int calculateMatchStrength(String fileContent, String searchTerm) {
		// implement your own algorithm to calculate the strength of the match
		// for example, you could count the number of occurrences of the search term
		// and divide by the length of the file
		System.out.println("Hello");
		return 0;
	}

		// inner class to hold a file match and its strength
		private class FileMatch {
			private File file;
			private int strength;

			public FileMatch(File file, int strength) {
				this.file = file;
				this.strength = strength;
			}

			public File getFile() {
				return file;
			}

			public int getStrength() {
				return strength;
			}
		}
	}

