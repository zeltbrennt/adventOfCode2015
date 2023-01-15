import aocutil.AOCProblem;
import aocutil.InputReader;

public class Day17 implements AOCProblem {

    private static final int CAPACITY = 150;
    private final int[] containers;
    private int nCombo = 0;
    private int nMinCombo = 0;
    private int minContainer = Integer.MAX_VALUE;

    public Day17(String s) {
        containers = InputReader.multipleLines(s).stream().mapToInt(Integer::parseInt).toArray();
    }

    private void testCombinations() {
        int set = 1 << containers.length;
        for (int i = 0; i < set; i++) {
            int volume = 0, pos = i, used = 0;
            for (int container : containers) {
                used += (1 & pos);
                volume += container * (1 & pos);
                pos >>= 1;
            }
            if (volume == CAPACITY) {
                if (used < minContainer) {
                    nMinCombo = 1;
                    minContainer = used;
                } else if (used == minContainer) {
                    nMinCombo++;
                }
                nCombo++;
            }
        }
    }

    @Override
    public int solvePart1() {
        testCombinations();
        return nCombo;
    }

    @Override
    public int solvePart2() {
        if (nCombo == 0) solvePart1();
        return nMinCombo;
    }
}
