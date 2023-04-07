This is a simple search engine application that allows you to search for words contained within text files in the project or within your local machine.

Getting Started
To run the application, you need to have Java installed on your computer. You can download and install the latest version of Java from the official website: https://www.java.com/en/download/

You also need to download or clone the source code of this project from GitHub: https://github.com/prince-ihekwereme/Search-Engine-Application

After you have downloaded or cloned the source code, you can open it in your favorite Java IDE (Integrated Development Environment) and run the control.java file.

Alternatively, you can compile and run the code from the command line. Here's an example of how to do it on a Unix-based system (such as Linux or macOS):

ruby
Copy code
$ javac control.java
$ java control
This will launch the search engine application.

Using the Application:
The search engine application has a simple GUI (Graphical User Interface) that allows you to enter a search term and search for it within the text files included in the application or within a text file on your local machine.

Search Term:
To search for a term, simply type it into the "Search Term" text field and press the "Search" button. The application will search for the term within the text files included in the "files" directory and display the percentage of words containing the term in each file, sorted in descending order.

Choose File and Search:
To search for a term within a text file on your local machine, click the "Choose File and Search" button. This will open a file chooser dialog that allows you to select a text file. Once you have selected a file, the application will search for the term within the file and display the percentage of words containing the term in the file.


The application also has a menu bar with the following options:

About: displays information about the search engine application.
Help: displays instructions on how to use the search engine application.
Exit: exits the application.

The program contains two classes: control and GUI.

The control class contains the main method, which instantiates a new GUI object. It also contains a try-catch block that catches any FileNotFoundException exceptions and re-throws them as RuntimeExceptions.

The GUI class extends JFrame and creates the graphical user interface for the application. The interface includes a menu bar with three options: "About," "Help," and "Exit." The "About" and "Help" options display a message dialog box when clicked, while the "Exit" option displays a confirmation dialog box before exiting the application.

The interface also includes a panel with a text field to input search terms, a "Search" button to search for the terms within the text files included in the project, a "Choose File and Search" button to search for the terms within a text file on the user's local machine, and a text area to display the search results.

The GUI class also contains two action listeners: one for the "Choose File and Search" button and one for the "Search" button. The "Choose File and Search" button listener opens a file chooser dialog box to allow the user to select a text file to search within their local machine. The program then searches the file for the specified search term and calculates the percentage of words in the file that contain the term. The results are displayed in the text area.

The "Search" button listener searches for the specified term within the text files included in the project. The program calculates the percentage of words in each file that contain the term and displays the results in the text area, sorted by percentage in descending order.

Things I would add if I had more time: I would include spell checking and improve the GUI layout to improve user experience.
Acknowledgments:
This search engine application was created by Prince Ihekwereme as a Java programming assignment. It uses the Java Swing GUI toolkit and the Apache Commons IO library.
