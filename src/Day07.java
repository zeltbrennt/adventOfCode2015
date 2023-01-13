import aocutil.AOCProblem;
import aocutil.InputReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day07 implements AOCProblem {

    private final Map<String, String[]> graph;
    private int solution;

    public Day07(String puzzleFile) {
        List<String> puzzle = InputReader.multipleLines(puzzleFile);
        graph = new HashMap<>();
        puzzle.stream().map(line -> line.split(" -> ")).forEach(arr -> graph.put(arr[1], arr[0].split("\\s")));
    }

    private int getValue(String node, Map<String, Integer> memo) {
        if (memo.containsKey(node)) return memo.get(node);
        if (!graph.containsKey(node)) return Integer.parseInt(node);
        String[] description = graph.get(node);
        int value;
        if (description.length == 1) value = getValue(description[0], memo);
        else if (description.length == 2) value = ~getValue(description[1], memo) & 0xffff;
        else {
            int left = getValue(description[0], memo);
            int right = getValue(description[2], memo);
            value = switch (description[1]) {
                case "AND" -> left & right;
                case "OR" -> left | right;
                case "XOR" -> left ^ right;
                case "RSHIFT" -> left >> right;
                case "LSHIFT" -> left << right;
                default -> 0;
            };
        }
        memo.put(node, value);
        return value;
    }

    @Override
    public int solvePart1() {
        Map<String, Integer> memo = new HashMap<>();
        /* for (String k : graph.keySet()) System.out.printf("%s: %d\n", k, getValue(k, memo));*/
        solution = getValue("a", memo);
        return solution;
    }

    @Override
    public int solvePart2() {
        graph.put("b", new String[]{String.valueOf(solution)});
        return solvePart1();
    }
}