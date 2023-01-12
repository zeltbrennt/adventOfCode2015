import aocutil.AOCProblem;
import aocutil.InputReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day09 implements AOCProblem {

    private final Map<String, List<Tuple<String, Integer>>> map;

    public Day09(String input) {
        map = new HashMap<>();
        List<String[]> puzzle = InputReader.multipleLines(input).stream().map(x -> x.split("\\sto\\s|\\s=\\s")).toList();
        for (String[] strings : puzzle) {
            int dist = Integer.parseInt(strings[2]);
            if (map.containsKey(strings[0])) {
                map.get(strings[0]).add(new Tuple<>(strings[1], dist));
            } else {
                List<Tuple<String, Integer>> temp = new ArrayList<>();
                temp.add(new Tuple<>(strings[1], dist));
                map.put(strings[0], temp);
            }
            if (map.containsKey(strings[1])) {
                map.get(strings[1]).add(new Tuple<>(strings[0], dist));
            } else {
                List<Tuple<String, Integer>> temp = new ArrayList<>();
                temp.add(new Tuple<>(strings[0], dist));
                map.put(strings[1], temp);
            }
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
}

class Tuple<X, Y> {
    public final X x;
    public final Y y;
    public Tuple(X x, Y y) {
        this.x = x;
        this.y = y;
    }
}