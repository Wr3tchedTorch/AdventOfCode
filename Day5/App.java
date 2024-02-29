import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class App {
    String fullText = "";

    public static void main(String[] args) {
        try {
            Scanner readTextFile = new Scanner(new File("input.txt"));

            App app = new App();

            while (readTextFile.hasNextLine()) {
                String currentLine = readTextFile.nextLine();
                app.fullText += currentLine + "\n";
            }

            readTextFile.close();
            
            String[] seedsNumbers = app.get_map_numbers("seeds").split(" ");

            // Getting the maps
            String[] seed_to_soil = app.get_map_numbers("seed-to-soil map").split("\n");
            String[] soil_to_fertilizer = app.get_map_numbers("soil-to-fertilizer map").split("\n");
            String[] fertilizer_to_water = app.get_map_numbers("fertilizer-to-water map").split("\n");
            String[] water_to_light = app.get_map_numbers("water-to-light map").split("\n");
            String[] light_to_temperature = app.get_map_numbers("light-to-temperature map").split("\n");
            String[] temperature_to_humidity = app.get_map_numbers("temperature-to-humidity map").split("\n");
            String[] humidity_to_location = app.get_map_numbers("humidity-to-location map").split("\n");            

            long lowestLocation = 2147483646;
            
            for (String seed : seedsNumbers) {
                
                long soilValue = app.convert_number(seed_to_soil, Long.parseLong(seed));
                long fertilizerValue = app.convert_number(soil_to_fertilizer, soilValue);
                long waterValue = app.convert_number(fertilizer_to_water, fertilizerValue);
                long lightValue = app.convert_number(water_to_light, waterValue);
                long temperatureValue = app.convert_number(light_to_temperature, lightValue);
                long humidityValue = app.convert_number(temperature_to_humidity, temperatureValue);
                long locationValue = app.convert_number(humidity_to_location, humidityValue);

                if (lowestLocation > locationValue) { lowestLocation = locationValue; }
            }

            System.out.println("The lowest location to plant a seed is: " + lowestLocation);            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public long convert_number(String[] convertion_map_array, long num_to_convert) {        
        long convertedNumber = 0;

        for (String line : convertion_map_array) {
            long destination = Long.parseLong(line.split(" ")[0]);
            long source = Long.parseLong(line.split(" ")[1]);
            long range = Long.parseLong(line.split(" ")[2]);

            if ((source < num_to_convert && source + range > num_to_convert) || (source == num_to_convert)) {
                long diff = num_to_convert - source;
                convertedNumber = diff + destination;
            }

        }

        if (convertedNumber == 0) {
            convertedNumber = num_to_convert;
        }

        return convertedNumber;
    }

    public String get_map_numbers(String map) {
        long next_map = 0;
        switch (map) {
            case "seeds":
                next_map = fullText.indexOf("seed-to-soil map");
                break;
            case "seed-to-soil map":
                next_map = fullText.indexOf("soil-to-fertilizer map:");
                break;
            case "soil-to-fertilizer map":
                next_map = fullText.indexOf("fertilizer-to-water map:");
                break;
            case "fertilizer-to-water map":
                next_map = fullText.indexOf("water-to-light map:");
                break;
            case "water-to-light map":
                next_map = fullText.indexOf("light-to-temperature map:");
                break;
            case "light-to-temperature map":
                next_map = fullText.indexOf("temperature-to-humidity map:");
                break;
            case "temperature-to-humidity map":
                next_map = fullText.indexOf("humidity-to-location map:");
                break;
            case "humidity-to-location map":
                next_map = fullText.length()-1;
                break;
        }

        return fullText.substring(fullText.indexOf(map + ":"), (int) next_map).split(":")[1].trim();
    }

}