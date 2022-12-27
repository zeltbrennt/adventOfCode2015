import aocutil.AOCProblem;
import aocutil.InputReader;

public class Day01 implements AOCProblem {

    public String data;

    public Day01(String puzzleInput) {
        this.data = InputReader.singleLine(puzzleInput);
    }

    @Override
    public int solvePart1() {
        return data.chars().map(ch -> ch == '(' ? 1 : -1).sum();
    }

    @Override
    public int solvePart2() {
        int temp = 0;
        char[] chars = data.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            temp += chars[i] == '(' ? 1 : -1;
            if (temp < 0) return i;
        }
        return -1;
    }
}
