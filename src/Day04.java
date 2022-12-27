import aocutil.AOCProblem;
import aocutil.InputReader;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Day04 implements AOCProblem {

    public String puzzle;

    public Day04(String puzzleInput) {
        this.puzzle = InputReader.singleLine(puzzleInput);
    }

    @Override
    public int solvePart1() {
        return genericSolve(1);
    }

    @Override
    public int solvePart2() {
        return genericSolve(2);
    }

    private int genericSolve(int part) {
        int upper = part == 1 ? 16 : 1;
        int answer = 0;
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        while (true) {
            byte[] messageBytes = (puzzle + answer).getBytes(StandardCharsets.UTF_8);
            byte[] checksum = md.digest(messageBytes);
            if (checksum[0] == 0 && checksum[1] == 0 && checksum[2] < upper && checksum[2] >= 0) {
                break;
            }
            answer++;
        }
        return answer;
    }
}
