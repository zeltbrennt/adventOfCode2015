import aocutil.AOCProblem;
import aocutil.InputReader;

import java.util.Arrays;
import java.util.Iterator;


public class Day12 implements AOCProblem {

    private String json;

    public Day12(String s) {
        json = InputReader.singleLine(s);
    }

    @Override
    public int solvePart1() {
        return Arrays.stream(json.replaceAll("[^0-9-]", " ").trim().split(" +")).mapToInt(Integer::parseInt).sum();
    }

    @Override
    public int solvePart2() {
        return 0;
    }
}
