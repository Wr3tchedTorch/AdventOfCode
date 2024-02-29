import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class App {
    String full_input = "";

    public static void main(String[] args) {
        try {
            Scanner read_file = new Scanner(new File("input.txt"));

            App app = new App();

            while (read_file.hasNextLine()) {
                String line = read_file.nextLine();
                app.full_input += line + "\n";
            }

            read_file.close();
            
            String[] seeds = app.get_map_numbers("seeds").split(" ");

            // Getting the maps
            String[] seed_to_soil = app.get_map_numbers("seed-to-soil map").split("\n");
            String[] soil_to_fertilizer = app.get_map_numbers("soil-to-fertilizer map").split("\n");
            String[] fertilizer_to_water = app.get_map_numbers("fertilizer-to-water map").split("\n");
            String[] water_to_light = app.get_map_numbers("water-to-light map").split("\n");
            String[] light_to_temperature = app.get_map_numbers("light-to-temperature map").split("\n");
            String[] temperature_to_humidity = app.get_map_numbers("temperature-to-humidity map").split("\n");
            String[] humidity_to_location = app.get_map_numbers("humidity-to-location map").split("\n");            

            long lowest_location = 2147483646;
            
            for (String seed : seeds) {

                long soil = app.convert_number(seed_to_soil, Long.parseLong(seed));
                long fertilizer = app.convert_number(soil_to_fertilizer, soil);
                long water = app.convert_number(fertilizer_to_water, fertilizer);
                long light = app.convert_number(water_to_light, water);
                long temperature = app.convert_number(light_to_temperature, light);
                long humidity = app.convert_number(temperature_to_humidity, temperature);
                long location = app.convert_number(humidity_to_location, humidity);

                if (lowest_location > location) { lowest_location = location; }
            }

            System.out.println("The lowest location to plant a seed is: " + lowest_location);            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public long convert_number(String[] convertion_map_array, long num_to_convert) {        
        long res = 0;

        for (String line : convertion_map_array) {
            long destination = Long.parseLong(line.split(" ")[0]);
            long source = Long.parseLong(line.split(" ")[1]);
            long range = Long.parseLong(line.split(" ")[2]);

            if ((source < num_to_convert && source + range > num_to_convert) || (source == num_to_convert)) {
                long diff = num_to_convert - source;
                res = diff + destination;
            }

        }

        if (res == 0) {
            res = num_to_convert;
        }

        return res;
    }

    public String get_map_numbers(String map) {
        long next_map = 0;
        switch (map) {
            case "seeds":
                next_map = full_input.indexOf("seed-to-soil map");
                break;
            case "seed-to-soil map":
                next_map = full_input.indexOf("soil-to-fertilizer map:");
                break;
            case "soil-to-fertilizer map":
                next_map = full_input.indexOf("fertilizer-to-water map:");
                break;
            case "fertilizer-to-water map":
                next_map = full_input.indexOf("water-to-light map:");
                break;
            case "water-to-light map":
                next_map = full_input.indexOf("light-to-temperature map:");
                break;
            case "light-to-temperature map":
                next_map = full_input.indexOf("temperature-to-humidity map:");
                break;
            case "temperature-to-humidity map":
                next_map = full_input.indexOf("humidity-to-location map:");
                break;
            case "humidity-to-location map":
                next_map = full_input.length()-1;
                break;
        }

        return full_input.substring(full_input.indexOf(map + ":"), (int) next_map).split(":")[1].trim();
    }

}