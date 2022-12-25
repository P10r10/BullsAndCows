package bullscows;

import java.util.Random;

public class SecretCode {
    private final String code;
    public static final String SYMBOLS = "0123456789abcdefghijklmnopqrstuvwxyz";

    public SecretCode(String code) {
        this.code = code;
    }

    private boolean isBull(int index, char c) {
        return code.charAt(index) == c;
    }

    public int countBulls(String guess) {
        int count = 0;
        for (int i = 0; i < guess.length(); i++) {
            if (isBull(i, guess.charAt(i))) {
                count++;
            }
        }
        return count;
    }

    private boolean isCow(int index, char c) {
        if (isBull(index, c)) {
            return false;
        }
        for (int i = 0; i < code.length(); i++) {
            if (code.charAt(i) == c) {
                return true;
            }
        }
        return false;
    }
    public int countCows(String guess) {
        int count = 0;
        for (int i = 0; i < guess.length(); i++) {
            if (isCow(i, guess.charAt(i))) {
                count++;
            }
        }
        return count;
    }

    public void grade(String guess) {
        int bulls = countBulls(guess);
        int cows = countCows(guess);
        System.out.print("Grade: ");
        if (bulls > 0) {
            System.out.printf("%d bull", bulls);
            System.out.print(bulls > 1 ? "s" : "");
        }
        if (bulls > 0 && cows > 0) {
            System.out.print(" and ");
        }
        if (cows > 0) {
            System.out.printf("%d cow", cows);
            System.out.print(cows > 1 ? "s" : "");
        }
        if (bulls == 0 && cows == 0) {
            System.out.print("None. ");
        }
        System.out.println();
    }

    public static String generate(int length, int possible){
        if (length > possible) {
           // throw new IllegalArgumentException("length > possible");
            System.out.printf("Error: it's not possible to generate a code with a length" +
                    " of %d with %d unique symbols.", length, possible);
            System.exit(1);
        }
        StringBuilder res = new StringBuilder();
        StringBuilder s = new StringBuilder(SYMBOLS);
        s.delete(possible, s.length());
        while (res.length() < length) {
            int idx = new Random().nextInt(s.length());
            res.append(s.charAt(idx));
            s.deleteCharAt(idx);
        }
        return res.toString();
    }
}
