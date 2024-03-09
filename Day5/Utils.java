import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Utils {
    private static ArrayList<String> getFileLines(String filePath) {      
        ArrayList<String> fileLines = new ArrayList<>();
        try {  
            Scanner readFile = new Scanner(new File(filePath));            
            
            while (readFile.hasNextLine()) {
                fileLines.add(readFile.nextLine());
            }

            readFile.close();
        } catch(FileNotFoundException e) {
            e.getStackTrace();
        }
        return fileLines;
    }

    public static String getFileText(String filePath) {
        ArrayList<String> linesList = getFileLines(filePath);
        
        String fullText = "";
        for (String line : linesList) {
            fullText += line + "\n";
        }

        return fullText;
    }
}