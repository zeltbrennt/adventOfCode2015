import aocutil.AOCProblem;
import aocutil.InputReader;

import java.util.HashMap;
import java.util.Map;

public class Day03 implements AOCProblem {

    public String data;

    public Day03(String puzzleFile) {
        this.data = InputReader.singleLine(puzzleFile);
    }

    @Override
    public int solvePart1() {
        Map<String, Integer> visitedHouses = new HashMap<>();
        int x = 0;
        int y = 0;
        String house = String.format("%d %d", x, y);
        visitedHouses.put(house, 0);
        for (char c : data.toCharArray()) {
            switch (c) {
                case '<' -> x--;
                case '>' -> x++;
                case '^' -> y++;
                case 'v' -> y--;
            }
            house = String.format("%d %d", x, y);
            visitedHouses.put(house, visitedHouses.getOrDefault(house, 0) + 1);
        }
        return visitedHouses.size();
    }

    @Override
    public int solvePart2() {
        Map<String, Integer> visitedHouses = new HashMap<>();
        int santa_x = 0;
        int santa_y = 0;
        int robo_x = 0;
        int robo_y = 0;
        String house = String.format("%d %d", santa_x, santa_y);
        visitedHouses.put(house, 0);
        char[] directions = data.toCharArray();
        for (int i = 0; i < directions.length; i++) {
            if (i % 2 == 0) {
                switch (directions[i]) {
                    case '<' -> santa_x--;
                    case '>' -> santa_x++;
                    case '^' -> santa_y++;
                    case 'v' -> santa_y--;
                }
                house = String.format("%d %d", santa_x, santa_y);
            } else {
                switch (directions[i]) {
                    case '<' -> robo_x--;
                    case '>' -> robo_x++;
                    case '^' -> robo_y++;
                    case 'v' -> robo_y--;
                }
                house = String.format("%d %d", robo_x, robo_y);
            }
            visitedHouses.put(house, visitedHouses.getOrDefault(house, 0) + 1);
        }
        return visitedHouses.size();
    }
}
