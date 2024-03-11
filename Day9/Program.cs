using Day9;

public class Program {
    public static void Main(string[] args)
    {
        Oasis myOasis = new Oasis(@"C:\Users\ericm\Desktop\AdventOfCode\Day9\files\puzzle_input.txt");
        Console.WriteLine(myOasis.GetExtrapolatedSum());
    }
}