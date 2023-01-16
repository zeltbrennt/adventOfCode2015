import aocutil.AOCProblem;
import aocutil.InputReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Day18 implements AOCProblem {

    private final int[] init_pos;
    private final int side = 100;
    private final int n_squares = side * side;
    private final int max_iter = 100;


    public Day18(String s) {
        List<Integer> temp = new ArrayList<>();
        for (String line : InputReader.multipleLines(s)) {
            for (char c : line.toCharArray()) {
                if (c == '#') temp.add(1);
                else temp.add(0);
            }
        }
        init_pos = new int[temp.size()];
        for (int i = 0; i < temp.size(); i++) {
            init_pos[i] = temp.get(i);
        }
    }

    private int checkSquare(int[] state, int i) {
        int neighbors = 0;
        neighbors += (i / side != 0 && i % side != 0) ? state[i - 1 - side] : 0;
        neighbors += (i / side != 0) ? state[i - side] : 0;
        neighbors += (i / side != 0 && i % side != side - 1) ? state[i + 1 - side] : 0;
        neighbors += (i % side != 0) ? state[i - 1] : 0;
        neighbors += (i % side != side - 1) ? state[i + 1] : 0;
        neighbors += (i % side != 0 && i / side != side - 1) ? state[i - 1 + side] : 0;
        neighbors += (i / side != side - 1) ? state[i + side] : 0;
        neighbors += (i / side != side - 1 && i % side != side - 1) ? state[i + 1 + side] : 0;
        return neighbors;
    }

    public int[] exportForVis() {
        IntStream.range(0, init_pos.length).forEach(i -> init_pos[i] *= 10);
        return init_pos;
    }

    @Override
    public int solvePart1() {
        int[] state = Arrays.copyOfRange(init_pos, 0, init_pos.length);
        for (int iter = 0; iter < max_iter; iter++) {
            int[] next = new int[state.length];
            for (int i = 0; i < n_squares; i++) {
                int n = checkSquare(state, i);
                if (state[i] == 1 && (n == 2 || n == 3)) next[i] = 1;
                else if (state[i] == 0 && n == 3) next[i] = 1;
            }
            state = next;
        }
        return Arrays.stream(state).filter(s -> s == 1).toArray().length;
    }

    @Override
    public int solvePart2() {
        int[] state = Arrays.copyOfRange(init_pos, 0, init_pos.length);
        for (int iter = 0; iter < max_iter; iter++) {
            int[] next = new int[state.length];
            for (int i = 0; i < n_squares; i++) {
                int n = checkSquare(state, i);
                if (state[i] == 1 && (n == 2 || n == 3)) next[i] = 1;
                else if (state[i] == 0 && n == 3) next[i] = 1;
                else if (i == 0 || i == 99 || i == 100 * 99 || i == 100 * 100 - 1) next[i] = 1;
            }
            state = next;
        }
        return Arrays.stream(state).filter(s -> s == 1).toArray().length;
    }
}
