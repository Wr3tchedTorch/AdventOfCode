import java.util.ArrayList;

public class CalibrationDocument {
    private ArrayList<String> inputLines = new ArrayList<>();

    CalibrationDocument(ArrayList<String> fileLines) {
        inputLines = fileLines;
    }

    private int[] getNumbers() {
        int[] numbers = new int[inputLines.size()];

        int i = 0;
        for (String line : inputLines) {
            char first_num = '-';
            char second_num = '-';
    
            for (int j = 0; j < line.length(); j++) {
                char letter = line.toCharArray()[j];
    
                if (Character.isDigit(letter)) {
                    if (first_num == '-') {
                        first_num = letter;
                    } 
                    else {
                        second_num = letter;
                    }
                }
    
            }
    
            if (second_num == '-') {
                second_num = first_num;
            }

            numbers[i] = Integer.parseInt(Character.toString(first_num) + Character.toString(second_num));
            i++;
        }

        return numbers;
    }

    public int getTotalSum() {
        int[] numbers = getNumbers();
        
        int total = 0;
        for (int num : numbers) {
            total += num;
        }
        return total;
    }
}
