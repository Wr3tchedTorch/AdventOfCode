import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.*;

public class CalibrationDocumentTest {
    @Test
    public void whenInputHasOneLine() {
        ArrayList<String> myLine = new ArrayList<>();
        myLine.add("1abc2");

        CalibrationDocument subject = new CalibrationDocument(myLine);
        assertEquals(12, subject.getTotalSum());
    }

    @Test
    public void whenInputHasFourLines() {
        CalibrationDocument subject = new CalibrationDocument(Utils.getFileLines("./files/first_test.txt"));
        assertEquals(142, subject.getTotalSum());
    }

    @Test
    public void whenInputHasMultipleLines() {
        CalibrationDocument subject = new CalibrationDocument(Utils.getFileLines("./files/puzzle_input.txt"));
        assertEquals(56049, subject.getTotalSum());
    }
}
