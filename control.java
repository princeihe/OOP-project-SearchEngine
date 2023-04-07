import java.io.FileNotFoundException;

public class control {
	public static void main(String[] args) {

		try {
			GUI frame = new GUI();
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	} // end main
} // end control
