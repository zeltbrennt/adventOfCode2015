import aocutil.AOCProblem;

public class Day25 implements AOCProblem {
    private final int y;
    private final int x;

    private long val = 20151125;
    private final int mod = 33554393;
    private final int mult = 252533;
    public Day25(int row, int col) {
        this.x = col - 1;
        this.y = row - 1;
    }

    @Override
    public int solvePart1() {
        int rowIdx = x + y;
        int seqIdx = rowIdx * (rowIdx + 1) / 2 + x;
        for (int i = 0; i < seqIdx; i++) val = (val * mult) % mod;
        return (int) val;
    }

    @Override
    public int solvePart2() {
        return 0;
    }
}
