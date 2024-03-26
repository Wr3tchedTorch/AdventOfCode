import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class PipeSketchTest {
    @Test
    public void TestSmallSketch() {
        PipeSketch subject = new PipeSketch(Utils.getTextFromFile("./TestFiles/TestSmallSketch.txt"));
        assertEquals(4, subject.getFarthestDistanceInLoop());
    }
}
