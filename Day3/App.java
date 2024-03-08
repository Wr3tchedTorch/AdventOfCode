// Day 4 - Objetivo: Encontrar todos os números que fazem parte do motor na esquemática e
// somar todos os números no final. 
// 
// Exemplo de esquemática:
// 
// ..35..633.
// ......#...
// 617*......
// .....+.58.
// ..592.....
// ......755.
// ...$.*....
// .664.598..
// 
// Os números adjacentes a um simbolo são os números que fazem parte do motor, esses números devem ser somados e o resultado deve ser exibido no final do programa

public class App {
    public static void main(String[] args) {
        Schematic engineSchematic = new Schematic(Utils.getFileLines("./files/first_example.txt"));
        System.out.println(engineSchematic.getPartNumbersProduct());
    }
}
