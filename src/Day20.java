import aocutil.AOCProblem;

import java.util.ArrayList;
import java.util.List;

public class Day20 implements AOCProblem {

    private final int target;

    public Day20(int s) {
        target = s;
    }

    private List<Integer> getPrimeFactors(int number, List<Integer> primes) {
        List<Integer> factors = new ArrayList<>();
        for (int prime : primes) {
            if (number % prime == 0) {
                factors.add(prime);
                number /= prime;
            }
        }
        return factors;
    }

    @Override
    public int solvePart1() {

        //only for housnumber >= 3
        List<Integer> primes = new ArrayList<>();
        primes.add(2);
        int houseNumber = 3;
        while (true) {
            List<Integer> factors = getPrimeFactors(houseNumber, primes);
            if (factors.isEmpty()) primes.add(houseNumber);
            int presents = 10 + houseNumber * 10;
            for (int factor : factors) {
                presents += factor * 10;
            }
            if (presents >= target) {
                return houseNumber;
            }
            houseNumber++;
        }
    }

    @Override
    public int solvePart2() {
        return 0;
    }
}
