public class PipeSketch {
    private char[][] sketch;
    int[][] pipesDistanceToStartingPos;

    private boolean startedLooping = false;
    private boolean endedLooping = false;
    private String previusDirection = "";

    PipeSketch(String sketch) {
        this.sketch = Utils.get2DCharArrayFromText(sketch);
        pipesDistanceToStartingPos = new int[this.sketch.length][this.sketch[0].length];
    }

    private String[] getPipeDirections(char pipe) {
        String[] directions = {"all", "all"};
        switch (pipe) {
            case '|':
                directions[0] = "up";
                directions[1] = "down";
                break;
            case '-':
                directions[0] = "left";
                directions[1] = "right";
                break;
            case 'L':
                directions[0] = "up";
                directions[1] = "right";
                break;
            case 'J':
                directions[0] = "up";
                directions[1] = "left";
                break;
            case '7':
                directions[0] = "left";
                directions[1] = "down";
                break;
            case 'F':
                directions[0] = "right";
                directions[1] = "down";
                break;
            case '.':
                directions[0] = "ground";
                directions[1] = "ground";
                break;

        }
        return directions;
    }

    private int[] getStartingPos() {
        int[] pos = new int[2];
        for (int row = 0; row < sketch.length; row++) {
            for (int column = 0; column < sketch[0].length; column++) {
                if (sketch[row][column] == 'S') {
                    pos[0]=row;
                    pos[1]=column;
                    break;
                }
            }
        }
        return pos;
    }

    private boolean pipeHasPosition(char pipe, String pos) {
        return (getPipeDirections(pipe)[0].equals(pos) || getPipeDirections(pipe)[1].equals(pos) || getPipeDirections(pipe)[0].equals("all"));
    }

    private int[] getNextPos(int[] startingPos) {
        int[] newPos = new int[2];
        
        if (startedLooping && sketch[startingPos[0]][startingPos[1]] == 'S') {
            endedLooping = true;
            return newPos;
        }
        
        char leftPipe = sketch[startingPos[0]][startingPos[1]-1];
        char rightPipe = sketch[startingPos[0]][startingPos[1]+1];
        char upPipe = sketch[startingPos[0]-1][startingPos[1]];
        char downPipe = sketch[startingPos[0]+1][startingPos[1]];

        int yOffset = 0;
        int xOffset = 0;

        if (pipeHasPosition(leftPipe, "right") && !previusDirection.equals("left")) {
            xOffset = -1;
            previusDirection = "right";
        } 
        else if (pipeHasPosition(rightPipe, "left") && !previusDirection.equals("right")) {
            xOffset = 1;
            previusDirection = "left";
        }
        else if (pipeHasPosition(upPipe, "down") && !previusDirection.equals("up")) {
            yOffset = -1;
            previusDirection = "down";
        }
        else if (pipeHasPosition(downPipe, "up") && !previusDirection.equals("down")) {
            yOffset = 1;
            previusDirection = "up";
        }
        
        newPos[0] = startingPos[0]+yOffset;
        newPos[1] = startingPos[1]+xOffset;
        pipesDistanceToStartingPos[newPos[0]][newPos[1]] = pipesDistanceToStartingPos[startingPos[0]][startingPos[1]] + 1;
        
        return newPos;
    }

    public int getFarthestDistanceInLoop() {
        int[] newPos = getStartingPos();

        while (!endedLooping) {
            newPos = getNextPos(newPos);
            startedLooping = true;
        }

        System.out.println(newPos);
        return 0;
    }
}