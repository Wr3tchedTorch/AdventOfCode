import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Utils {
    public static ArrayList<String> getFileLines(String filePath) {
        ArrayList<String> fileLines = new ArrayList<>();
        try {
            Scanner readFile = new Scanner(new File(filePath));
            
            while (readFile.hasNextLine()) {
                String line = readFile.nextLine();

                if (line != "") { fileLines.add(line); }
            }
            
            readFile.close();

        } catch(FileNotFoundException e) {
            e.getStackTrace();
        }
        return fileLines;
    }
}
