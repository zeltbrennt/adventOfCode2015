import aocutil.AOCProblem;
import aocutil.InputReader;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class Day17 implements AOCProblem {

    private Deque<Container> containers = new ArrayDeque<>();
    public Day17(String s) {
        List<String> values = InputReader.multipleLines(s);
        for (int i = 0; i < values.size(); i++) {
            containers.push(new Container(i, Integer.parseInt(values.get(i))));
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

    private record Container(int id, int capacity) {}
}
