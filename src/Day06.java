import aocutil.AOCProblem;
import aocutil.InputReader;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day06 implements AOCProblem {
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 1000;

    private final boolean[][] lights = new boolean[WIDTH][HEIGHT];
    private final String[] instructions;
    private final List<String> puzzle;
    private final int[][] coords;

    public Day06(String inputFile) {
        this.puzzle = InputReader.multipleLines(inputFile);
        this.instructions = new String[puzzle.size()];
        this.coords = new int[puzzle.size()][4];
        parseInput();
    }

    private void parseInput() {
        Pattern coordPattern = Pattern.compile("\\d+");
        Pattern turnOn = Pattern.compile("turn on");
        Pattern toggle = Pattern.compile("toggle");
        Matcher coordMatcher;
        Matcher switchMatcher;
        Matcher toggleMatcher;
        int[] coords = new int[4];
        for (int j = 0; j < puzzle.size(); j++) {
            String line = puzzle.get(j);
            coordMatcher = coordPattern.matcher(line);
            switchMatcher = turnOn.matcher(line);
            toggleMatcher = toggle.matcher(line);
            if (switchMatcher.find()) instructions[j] = "on";
            else if (toggleMatcher.find()) instructions[j] = "toggle";
            else instructions[j] = "off";
            int i = 0;
            while (coordMatcher.find()) {
                coords[i] = Integer.parseInt(coordMatcher.group());
                i++;
            }
            this.coords[j] = coords.clone();
        }
    }
    
    @Override
    public int solvePart1() {
        for (int i = 0; i < instructions.length; i++) {
            System.out.println(puzzle.get(i));
            for (int x = coords[i][0]; x <= coords[i][2]; x++) {
                for (int y = coords[i][1]; y <= coords[i][3]; y++) {
                    switch (instructions[i]) {
                        case "on" -> lights[x][y] = true;
                        case "off" -> lights[x][y] = false;
                        case "toggle" -> lights[x][y] = !lights[x][y];
                    }
                }
            }
        }
        int sum = 0;
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                sum += lights[x][y] ? 1 : 0;
            }

        }
        return sum;
    }

    @Override
    public int solvePart2() {
        return 0;
    }
}
