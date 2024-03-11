namespace Day9;

public class OasisTest
{
    [Fact]
    public void TestFirstExample()
    {
        Oasis myOasis = new Oasis(@"C:\Users\ericm\Desktop\AdventOfCode\Day9\files\first_example.txt");
        Assert.Equal(114, myOasis.GetExtrapolatedSum());
    }
}