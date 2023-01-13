import aocutil.AOCProblem;
import aocutil.InputReader;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day14 implements AOCProblem {

    private final Set<Raindeer> racers = new HashSet<>();
    public Day14(String s) {
        List<String> puzzle = InputReader.multipleLines(s);
        for (String line : puzzle) {
            int[] values = Arrays.stream(line.replaceAll("\\D", " ").trim().split(" +")).mapToInt(Integer::parseInt).toArray();
            racers.add(new Raindeer(values[0], values[1], values[2]));
        }
    }

    @Override
    public int solvePart1() {
        return 0;
    }

    @Override
    public int solvePart2() {
        return 0;
    }

    private record Raindeer(int speed, int duration, int rest) {
    }

}
