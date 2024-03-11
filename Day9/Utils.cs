using System.Collections;
using Microsoft.Win32.SafeHandles;

namespace Day9;

public class Utils {
    public static ArrayList GetLinesFromFile(string filePath) {
        ArrayList fileLines = new ArrayList();
        try {            
            StreamReader readFile = new StreamReader(filePath);
            string ?line = readFile.ReadLine();
            while (line != null)
            {
                fileLines.Add(line);
                line = readFile.ReadLine();
            }
            readFile.Close();
        } catch(FileNotFoundException e) {
            Console.WriteLine($"Exception: {e.Message}");
        }

        return fileLines;
    }
}