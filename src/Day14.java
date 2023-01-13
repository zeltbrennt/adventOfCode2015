import aocutil.AOCProblem;
import aocutil.InputReader;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day14 implements AOCProblem {

    private static final int RACE_DURATION = 2503;
    private final Set<Raindeer> racers = new HashSet<>();
    private int maxDistance = 0;

    public Day14(String s) {
        List<String> puzzle = InputReader.multipleLines(s);
        for (String line : puzzle) {
            int[] values = Arrays.stream(line.replaceAll("\\D", " ").trim().split(" +")).mapToInt(Integer::parseInt).toArray();
            racers.add(new Raindeer(values[0], values[1], values[2]));
        }
    }

    @Override
    public int solvePart1() {
        for (Raindeer raindeer : racers) {
            int flyIntervals = RACE_DURATION / (raindeer.duration + raindeer.rest);
            int distance = flyIntervals * raindeer.speed * raindeer.duration;
            distance += Math.min(RACE_DURATION % (raindeer.duration + raindeer.rest), raindeer.duration) * raindeer.speed;
            maxDistance = Math.max(distance, maxDistance);
        }
        return maxDistance;
    }

    @Override
    public int solvePart2() {
        return 0;
    }

    private record Raindeer(int speed, int duration, int rest) {
    }

}
