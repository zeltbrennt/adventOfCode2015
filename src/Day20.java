import aocutil.AOCProblem;

import java.util.HashSet;
import java.util.Set;

public class Day20 implements AOCProblem {

    private final int target;

    public Day20(int s) {
        target = s;
    }

    private Set<Integer> getFactors(int number, int limit) {
        Set<Integer> factors = new HashSet<>();
        for (int i = 1; i <= Math.sqrt(number); i++) {
            int remainder = number % i;
            int divisor = number / i;
            if (remainder == 0) {
                if (divisor <= limit) factors.add(i);
                if (i <= limit) factors.add(divisor);
            }
        }
        return factors;
    }


    @Override
    public int solvePart1() {
        int houseNumber = 1 << 18;
        while (true) {
            Set<Integer> factors = getFactors(houseNumber, Integer.MAX_VALUE);
            int presents = factors.stream().mapToInt(factor -> factor).map(factor -> 10 * factor).sum();
            if (presents >= target) return houseNumber;
            houseNumber++;
        }
    }

    @Override
    public int solvePart2() {
        int houseNumber = 1 << 18;
        while (true) {
            Set<Integer> factors = getFactors(houseNumber, 50);
            int presents = factors.stream().mapToInt(factor -> factor).map(factor -> 11 * factor).sum();
            if (presents >= target) return houseNumber;
            houseNumber++;
        }
    }
}
