import aocutil.AOCProblem;
import aocutil.InputReader;

import java.util.List;

public class Day08 implements AOCProblem {

    private final List<String> puzzle;

    public Day08(String input) {
        puzzle = InputReader.multipleLines(input);
    }

    @Override
    public int solvePart1() {
        int str = puzzle.stream().mapToInt(String::length).sum();
        int code = puzzle.stream()
                .map(x -> x.replace("\\\\", "S"))
                .map(x -> x.replace("\\\"", "Q"))
                .map(x -> x.replaceAll("\\\\x[a-f0-9]{2}", "X"))
                .mapToInt(x -> x.length() - 2)
                .sum();
        return str - code;
    }

    @Override
    public int solvePart2() {
        int str = puzzle.stream().mapToInt(String::length).sum();
        int code = puzzle.stream()
                .map(x -> x.replace("\"", "QQ"))
                .map(x -> x.replace("\\", "SS"))
                .mapToInt(x -> x.length() + 2)
                .sum();
        return code - str;
    }
}
