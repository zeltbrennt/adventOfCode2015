import aocutil.AOCProblem;
import aocutil.InputReader;

import java.util.List;

public class Day24 implements AOCProblem {
    private final List<String> packages;

    public Day24(String s) {
        this.packages = InputReader.multipleLines(s);
    }

    @Override
    public int solvePart1() {
        int[] r = new int[]{1, 2, 3, 4, 5, 7, 8, 9, 10, 11};
        for (int i = 1; i < r.length; i++) {
            for (int j = 1; j < 1 << r.length; j++) {
                if (Integer.bitCount(j) == i) {
                    String bits = String.format("%" + r.length + "s", Integer.toBinaryString(j)).replace(' ', '0');
                    System.out.println(bits);
                    int targetWeight = 0;
                    System.out.print("[ ");
                    for (int k = 0; k < bits.length(); k++) {
                        if (bits.charAt(k) == '1') {
                            System.out.printf("%02d ", r[k]);
                            targetWeight += r[k];
                        }
                    }
                    System.out.println("] = " + targetWeight);
                }
            }
        }
        return 0;
    }

    @Override
    public int solvePart2() {
        return 0;
    }
}
