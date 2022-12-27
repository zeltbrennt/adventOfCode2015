import aocutil.AOCProblem;

public class Main {
    public static void main(String[] args) {
        System.out.println("\nDay 01:");
        AOCProblem day01 = new Day01("input/01.txt");
        System.out.printf("Part 1: %d\n", day01.solvePart1());
        System.out.printf("Part 2: %d\n", day01.solvePart2());
        System.out.println("\nDay 02:");
        AOCProblem day02 = new Day02("input/02.txt");
        System.out.printf("Part 1: %d\n",  day02.solvePart1());
        System.out.printf("Part 2: %d\n", day02.solvePart2());
    }
}