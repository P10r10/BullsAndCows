package bullscows;

import java.util.Scanner;

public class BullsAndCows {
    private final Scanner scanner;

    public BullsAndCows(Scanner scanner) {
        this.scanner = scanner;
        start();
    }

    private void start() {
        int turn = 1, length = 0;
        System.out.println("Input the length of the secret code:");
        try {
            length = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("error");
            System.exit(1);
        }
        if (length > 36) {
            System.out.println("Error: can't generate a secret number with a length over 36");
            System.exit(1);
        }
        if (length <= 0) {
            System.out.println("Error: lenght can't be 0 or less");
            System.exit(1);
        }
        System.out.println("Input the number of possible symbols in the code:");
        int possible = Integer.parseInt(scanner.nextLine());
        if (possible > 36) {
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
            System.exit(1);
        }
        SecretCode secretCode = new SecretCode(SecretCode.generate(length, possible));
        System.out.println("The secret is prepared: ");
        for (int i = 0; i < length; i++) {
            System.out.print("*");
        }
        System.out.print(" (");
        if (possible <= 10) {
            System.out.printf("0-%c).\n", SecretCode.SYMBOLS.charAt(possible - 1));
        } else {
            System.out.printf("0-9, a-%c).\n", SecretCode.SYMBOLS.charAt(possible - 1));
        }
        System.out.println("Okay, let's start a game!:");
        while (true) {
            System.out.printf("Turn %d:\n", turn++);
            String guess = scanner.nextLine();
            if (secretCode.countBulls(guess) == length) {
                secretCode.grade(guess);
                System.out.println("Congratulations! You guessed the secret code.");
                break;
            }
            secretCode.grade(guess);
        }
    }
}
