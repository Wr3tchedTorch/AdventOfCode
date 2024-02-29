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
        File gameRecordsFile = new File("input.txt");

        try {
            Scanner readingGameRecords = new Scanner(gameRecordsFile);

            int sumOfPossibleGamesId = 0;

            String gameId = "";

            while (readingGameRecords.hasNextLine()) {
                String gameRecord = readingGameRecords.nextLine();

                boolean isTheGamePossible = true;

                gameId = gameRecord.substring(0, gameRecord.indexOf(":"));
                gameId = gameId.replaceAll("[^0-9]", "").trim();

                String gameInfo = gameRecord.substring(gameRecord.indexOf(":"), gameRecord.length());

                String[] cubeValues = gameInfo.replaceAll("[^0-9' ']", "").trim().replaceAll(" +", " ").split(" ");

                String[] cubeNames = gameInfo.replaceAll("[^a-zA-Z' ']", "").trim().replaceAll(" +", " ")
                        .split(" ");

                for (int j = 0; j < cubeNames.length; j++) {
                    String cubeColor = cubeNames[j];
                    int cubeValue = Integer.parseInt(cubeValues[j]);

                    switch (cubeColor.toString()) {
                        case "blue":
                            if (cubeValue > 14) {
                                isTheGamePossible = false;
                            }
                            break;
                        case "green":
                            if (cubeValue > 13) {
                                isTheGamePossible = false;
                            }
                            break;
                        case "red":
                            if (cubeValue > 12) {
                                isTheGamePossible = false;
                            }
                    }
                }

                if (isTheGamePossible)
                    sumOfPossibleGamesId += Integer.parseInt(gameId);      
            }

            readingGameRecords.close();
            
            System.out.println("Soma dos ids de todos os jogos possiveis: " + sumOfPossibleGamesId);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
