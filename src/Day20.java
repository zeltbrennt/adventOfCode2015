import aocutil.AOCProblem;

import java.util.HashSet;
import java.util.Set;

public class Day20 implements AOCProblem {

    private final int target;

    public Day20(int s) {
        target = s / 10;
    }

    private Set<Integer> getPrimeFactors(int number) {
        Set<Integer> factors = new HashSet<>();
        while (number % 2 == 0) {
            factors.add(number);
            factors.add(2);
            number /= 2;
        }
        for (int i = 3; i <= Math.sqrt(number); i += 2) {
            while (number % i == 0) {
                factors.add(i);
                number /= i;
            }
        }
        if (number > 2) factors.add(number);
        return factors;
    }

    @Override
    public int solvePart1() {

        //1701888 too high
        int houseNumber = 1 << 16;
        while (true) {
            Set<Integer> factors = getPrimeFactors(houseNumber);
            factors.add(houseNumber);
            int presents = 1;
            for (int factor : factors) presents += factor;
            if (presents >= target) return houseNumber;
            houseNumber++;
        }
    }

    @Override
    public int solvePart2() {
        return 0;
    }
}
