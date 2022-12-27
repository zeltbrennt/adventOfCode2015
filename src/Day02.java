import aocutil.AOCProblem;
import aocutil.InputReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day02 implements AOCProblem {

    public List<int[]> data;

    public Day02(String puzzleInput) {
        this.data = parsePuzzleInput(puzzleInput);
    }

    public List<int[]> parsePuzzleInput(String puzzleFile) {
        List<int[]> input = new ArrayList<>();
        List<String> puzzle = InputReader.multipleLines(puzzleFile);
        puzzle.forEach(present -> input.add(Arrays.stream(present.split("x")).mapToInt(Integer::parseInt).toArray()));
        return input;
    }

    @Override
    public int solvePart1() {
        int[] side = new int[3];
        int result = 0;
        for (int[] dim : data) {
            side[0] = 2 * dim[0] * dim[1];
            side[1] = 2 * dim[1] * dim[2];
            side[2] = 2 * dim[0] * dim[2];
            result += Arrays.stream(side).sum() + Arrays.stream(side).min().orElse(0) / 2;
        }
        return result;
    }

    @Override
    public int solvePart2() {
        int result = 0;
        int[] ribbon = new int[3];
        for (int[] dim : data) {
            ribbon[0] = 2 * dim[0];
            ribbon[1] = 2 * dim[1];
            ribbon[2] = 2 * dim[2];
            result += (dim[0] * dim[1] * dim[2]) + Arrays.stream(ribbon).sum() - Arrays.stream(ribbon).max().orElse(0);
        }
        return result;
    }
}
