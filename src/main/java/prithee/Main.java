package prithee;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Sonnet sonnet = Sonnet.createDefault();
        PritheeGame game = new PritheeGame(sonnet, new Random());
        Scanner scanner = new Scanner(System.in);

        System.out.println("Prithee");
        System.out.println("The sonnet stops at a missing word. Type the missing word.");
        System.out.println("Three correct answers wins. Three wrong answers ends the show.");
        System.out.println();

        while (!game.isOver()) {
            game.nextPrompt();
            System.out.println(game.getPromptText());
            System.out.println();
            System.out.print("Prithee, what is the next word? ");

            if (!scanner.hasNextLine()) {
                System.out.println();
                System.out.println("No input found. Exiting.");
                scanner.close();
                return;
            }

            String answer = scanner.nextLine();
            String expected = game.getCurrentWord();
            boolean correct = game.submitAnswer(answer);

            if (correct) {
                System.out.println("Correct.");
            } else {
                System.out.println("Error. The word was: " + expected);
            }

            System.out.println("Score: " + game.getCorrectCount() + " correct, "
                    + game.getIncorrectCount() + " incorrect.");
            System.out.println();
        }

        if (game.isWin()) {
            System.out.println("Three correct. The play goes on.");
        } else {
            System.out.println("Three errors. The prompter takes over.");
        }

        scanner.close();
    }
}
