import aocutil.AOCProblem;
import aocutil.InputReader;

import java.util.ArrayList;
import java.util.List;

public class Day18 implements AOCProblem {

    private final int[] start;

    public Day18(String s) {
        List<Integer> status = new ArrayList<>();
        for (String line : InputReader.multipleLines(s)) {
            for (char c : line.toCharArray()) {
                if (c == '#') status.add(10);
                else status.add(0);
            }
        }
        start = new int[status.size()];
        for (int i = 0; i < status.size(); i++) {
            start[i] = status.get(i);
        }
    }

    public int[] exportPart1() {
        return start;
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
