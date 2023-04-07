import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PercentageCalculator {

    public static double getPercentage(String filePath, String[] searchTerms) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        int totalWords = 0;
        int matchingWords = 0;
        while (scanner.hasNext()) {
            totalWords++;
            String word = scanner.next();
            for (String searchTerm : searchTerms) {
                if (word.toLowerCase().contains(searchTerm.toLowerCase())) {
                    matchingWords++;
                    break;
                }
            }
        }
        scanner.close();
        return ((double) matchingWords / totalWords) * 100;
    }
}
