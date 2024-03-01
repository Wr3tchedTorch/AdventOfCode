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
