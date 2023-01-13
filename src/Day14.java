import aocutil.AOCProblem;
import aocutil.InputReader;

import java.util.*;

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
        for (Raindeer rd : racers) {
            int flyIntervals = RACE_DURATION / (rd.duration + rd.rest);
            int distance = flyIntervals * rd.speed * rd.duration;
            distance += Math.min(RACE_DURATION % (rd.duration + rd.rest), rd.duration) * rd.speed;
            maxDistance = Math.max(distance, maxDistance);
        }
        return maxDistance;
    }

    @Override
    public int solvePart2() {
        maxDistance = 0;
        Map<Raindeer, Integer> status = new HashMap<>();
        Map<Raindeer, Integer> points = new HashMap<>();
        racers.forEach(rd -> {
            status.put(rd, 0);
            points.put(rd, 0);
        });
        for (int i = 0; i < RACE_DURATION; i++) {
            for (Raindeer rd : racers) {
                int distance = status.get(rd);
                distance += i % (rd.duration + rd.rest) < rd.duration ? rd.speed : 0;
                maxDistance = Math.max(distance, maxDistance);
                status.put(rd, distance);
            }
            racers.stream().filter(rd -> status.get(rd) == maxDistance).forEach(rd -> points.put(rd, points.get(rd) + 1));
        }
        return points.values().stream().max(Integer::compareTo).get();
    }

    private record Raindeer(int speed, int duration, int rest) {
    }

}
