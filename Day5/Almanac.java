public class Almanac {
    private String completeText = "";

    Almanac(String filePath) {
        completeText = Utils.getFileText(filePath);
    }

    private String getMapNumbers(String map) {        
        long nextMap = 0;
        switch (map) {
            case "seeds":
                nextMap = completeText.indexOf("seed-to-soil map");
                break;
            case "seed-to-soil map":
                nextMap = completeText.indexOf("soil-to-fertilizer map:");
                break;
            case "soil-to-fertilizer map":
                nextMap = completeText.indexOf("fertilizer-to-water map:");
                break;
            case "fertilizer-to-water map":
                nextMap = completeText.indexOf("water-to-light map:");
                break;
            case "water-to-light map":
                nextMap = completeText.indexOf("light-to-temperature map:");
                break;
            case "light-to-temperature map":
                nextMap = completeText.indexOf("temperature-to-humidity map:");
                break;
            case "temperature-to-humidity map":
                nextMap = completeText.indexOf("humidity-to-location map:");
                break;
            case "humidity-to-location map":
                nextMap = completeText.length();
                break;
        }
        String mapNumbers = completeText.substring(completeText.indexOf(map + ":"), (int) nextMap).split(":")[1].trim();
        return mapNumbers;
    }

    private long convertNumber(String[] convertionMap, long numConvertingFrom) {        
        long convertedNum = 0;
        for (String line : convertionMap) {
            long destination = Long.parseLong(line.split(" ")[0]);
            long source = Long.parseLong(line.split(" ")[1]);
            long range = Long.parseLong(line.split(" ")[2]);

            if ((source < numConvertingFrom && source + range > numConvertingFrom) || (source == numConvertingFrom)) {
                long diff = numConvertingFrom - source;
                convertedNum = diff + destination;
            }
        }
        if (convertedNum == 0) {
            convertedNum = numConvertingFrom;
        }
        return convertedNum;
    }
    
    public long getLowestLocationNum() {
        String[] seeds = getMapNumbers("seeds").split(" ");

        String[] seed_to_soil = getMapNumbers("seed-to-soil map").split("\n");
        String[] soil_to_fertilizer = getMapNumbers("soil-to-fertilizer map").split("\n");
        String[] fertilizer_to_water = getMapNumbers("fertilizer-to-water map").split("\n");
        String[] water_to_light = getMapNumbers("water-to-light map").split("\n");
        String[] light_to_temperature = getMapNumbers("light-to-temperature map").split("\n");
        String[] temperature_to_humidity = getMapNumbers("temperature-to-humidity map").split("\n");
        String[] humidity_to_location = getMapNumbers("humidity-to-location map").split("\n");

        long lowestLocation = 2147483646;

        for (String seed : seeds) {
            long soil = convertNumber(seed_to_soil, Long.parseLong(seed));
            long fertilizer = convertNumber(soil_to_fertilizer, soil);
            long water = convertNumber(fertilizer_to_water, fertilizer);
            long light = convertNumber(water_to_light, water);
            long temperature = convertNumber(light_to_temperature, light);
            long humidity = convertNumber(temperature_to_humidity, temperature);
            long location = convertNumber(humidity_to_location, humidity);

            if (lowestLocation > location) {
                lowestLocation = location;
            }
        }
        
        return lowestLocation;
    }
}
