import aocutil.AOCProblem;
import aocutil.InputReader;

public class Day17 implements AOCProblem {

    private static final int CAPACITY = 150;
    private final int[] containers;
    private int nCombo = 0;

    public Day17(String s) {
        containers = InputReader.multipleLines(s).stream().mapToInt(Integer::parseInt).toArray();
    }

    private void testCombinations() {
        int set = 1 << containers.length;
        for (int i = 0; i < set; i++) {
            int volume = 0, pos = i;
            for (int container : containers) {
                volume += container * (1 & pos);
                pos >>= 1;
            }
            if (volume == CAPACITY) nCombo++;
        }
    }

    @Override
    public int solvePart1() {
        testCombinations();
        return nCombo;
    }

    @Override
    public int solvePart2() {
        return 0;
    }

    private record Container(int id, int capacity) {
    }
}
