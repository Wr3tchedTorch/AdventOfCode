/*

Day 2 - Objetivo do exercicio: A cada jogo um elfo irá mostrar cubos de diferentes cores em 3 rounds diferentes, o objetivo desse exercicio é verificar se os cubos exibidos pelo elfo seriam possiveis naquele jogo, tendo em mente que o elfo possui apenas 12 cubos vermelhos, 13 cubos verdes, e 14 cubos azuis.

Exemplo:
    Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
    Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
    Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
    Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
    Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green

Os jogos 1 e 2 seriam possiveis, porém o jogo 3 seriam impossivel já que o elfo possui apenas 12 cubos vermelhos e mostrou 20 cubos vermelhos ao mesmo tempo.

*/

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        File game_records = new File("input.txt");

        try {
            Scanner read_records = new Scanner(game_records);

            int total = 0;

            String game_id = "";

            while (read_records.hasNextLine()) {
                String line = read_records.nextLine();

                boolean isPossible = true;

                game_id = line.substring(0, line.indexOf(":"));

                System.out.print(game_id + " ");
                game_id = game_id.replaceAll("[^0-9]", "").trim();

                String new_line = line.substring(line.indexOf(":"), line.length());

                String[] values = new_line.replaceAll("[^0-9' ']", "").trim().replaceAll(" +", " ").split(" ");

                String[] names = new_line.replaceAll("[^a-zA-Z' ']", "").trim().replaceAll(" +", " ")
                        .split(" ");

                for (int j = 0; j < names.length; j++) {
                    String cube_color = names[j];
                    int cube_value = Integer.parseInt(values[j]);

                    switch (cube_color.toString()) {
                        case "blue":
                            if (cube_value > 14) {
                                isPossible = false;
                            }
                            break;
                        case "green":
                            if (cube_value > 13) {
                                isPossible = false;
                            }
                            break;
                        case "red":
                            if (cube_value > 12) {
                                isPossible = false;
                            }
                    }
                }

                if (isPossible)
                    total += Integer.parseInt(game_id);
                System.out.println("Jogo possivel: " + isPossible);
            }
            System.out.println("Soma dos ids de todos os jogos possiveis: " + total);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
