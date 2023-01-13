import aocutil.AOCProblem;

public class Day11 implements AOCProblem {

    private final char[] password;

    public Day11(String s) {
        this.password = s.toCharArray();
    }

    private void incrementPassword(int idx) {
        if (password[idx] + 1 > 'z') {
            password[idx] = 'a';
            incrementPassword(idx - 1);
        } else password[idx] = (char) (password[idx] + 1);
    }

    private boolean complyRuleOne() {
        for (int i = 0; i <= password.length - 3; i++) {
            if (password[i + 1] - password[i] == 1 && password[i + 2] - password[i + 1] == 1) return true;
        }
        return false;
    }

    private boolean complyRuleTwo() {
        int found = 0;
        for (int i = 0; i <= password.length - 2; i++) {
            if (password[i] == password[i + 1]) {
                found++;
                i++;
            }
        }
        return found >= 2;
    }

    private boolean complyRuleThree() {
        for (int i = 0; i < password.length; i++) {
            char c = password[i];
            if (c == 'o' || c == 'i' || c == 'l') {
                skip(i);
                return false;
            }
        }
        return true;
    }

    @Override
    public int solvePart1() {
        while (!(complyRuleOne() && complyRuleTwo() && complyRuleThree())) {
            incrementPassword(password.length - 1);
        }
        System.out.println(password);
        return password.length;
    }

    private void skip(int i) {
        for (int j = i + 1; j < password.length; j++) {
            password[j] = 'z';
        }
    }

    @Override
    public int solvePart2() {
        incrementPassword(password.length - 1);
        solvePart1();
        return 0;
    }
}
