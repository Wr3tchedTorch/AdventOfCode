import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MapNetwork {
    private ArrayList<String> map = new ArrayList<>();
    ArrayList<String> mapWithoutInstructions = new ArrayList<>();
    
    MapNetwork(ArrayList<String> map) {
        this.map = map;

        for (String line : map) {
            mapWithoutInstructions.add(line);
        }

        mapWithoutInstructions.remove(0);
    }
    
    private char[] getStepsInstructions() {
        String stepsInstructions = map.get(0);
        
        return stepsInstructions.toCharArray();
    }
    
    private String[] getPaths() {
        String[] paths = new String[mapWithoutInstructions.size()];
        
        int i = 0;

        for (String line : mapWithoutInstructions) {
            paths[i] = line.substring(0, line.indexOf("=")).trim();
            i++;
        }

        return paths;
    }

    private Map<String, String>[] getavailableChoiches() {
        Map<String, String>[] availableChoichesList = new Map[mapWithoutInstructions.size()];
        
        int i = 0;
        for (String line : mapWithoutInstructions) {
            line = line.substring(line.indexOf("("), line.length());
            line = line.replaceAll("\\(", "").replaceAll("\\)", "");
            String leftOption = line.split(",")[0].trim();
            String rightOption = line.split(",")[1].trim();
            
            Map<String, String> saveValues = new HashMap<String, String>();
            saveValues.put("left", leftOption);
            saveValues.put("right", rightOption);

            availableChoichesList[i] = saveValues;
            i++;
        }

        return availableChoichesList;
    }

    public int getStepsNumber() {
        char[] instructions = getStepsInstructions();
        String[] paths = getPaths();
        String currentPath = paths[Arrays.asList(paths).indexOf("AAA")];
        Map<String, String>[] availableChoiches = getavailableChoiches();
        int stepsCount = 0;

        int i = 0;
        String direction = "";

        boolean reachedGoal = false;

        do {
            char instruction = instructions[i];
            
            direction = instruction == 'L' ? "left" : "right";
            currentPath = (String)availableChoiches[Arrays.asList(paths).indexOf(currentPath)].get(direction);
            currentPath = currentPath.trim();
            
            i++;

            if (i == instructions.length) {
                i = 0;
            }

            stepsCount++;
            reachedGoal = currentPath.equals("ZZZ");
        } while (!reachedGoal);


        return stepsCount;
    }
}
