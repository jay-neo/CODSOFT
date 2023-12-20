import java.util.Random;
import java.util.Scanner;
import java.util.InputMismatchException;

public class NumberGame {
  public static void main(String args[]) {
    Scanner scanner = new Scanner(System.in);
    Random random = new Random();

    System.out.println("\n\n        ###############################################");
    System.out.println("        ##   Welcome to the Number Guessing Game!!   ##");
    System.out.println("        ###############################################");

    int score = 0;
    boolean playAgain = true;

    while (playAgain) {
      int targetNumber = random.nextInt(100) + 1;
      int attempts = 0;
      int maxAttempts = 5;

      System.out.println("\n\n\n\nI have selected a number between 1 and 100. Can you guess it within " + maxAttempts + " attempts?");
      while (attempts < maxAttempts) {
        attempts++;
        System.out.print("Attempt " + attempts + ": Enter your guess: ");
        if(!scanner.hasNextInt()) { 
          System.out.println("\n\n       Invalid input. Please enter an integer!!\n\n");
          System.out.println("\n\n        Game over! Your score: " + score + "\n\n");
          scanner.close();
          return;
        }
        int userGuess = scanner.nextInt();
        scanner.nextLine();
        if (userGuess == targetNumber) {
          System.out.println("\n     ##################################################################" + "#".repeat(String.valueOf(targetNumber).length()));
          System.out.println("     ##    Congratulations! You guessed the number " + targetNumber + " in " + attempts + " attempts.   ##");
          System.out.println("     ##################################################################" + "#".repeat(String.valueOf(targetNumber).length()) + "\n");
          score++;
          break;
        } else if (userGuess < targetNumber) {
          System.out.println("Too low! Try again.");
        } else {
          System.out.println("Too high! Try again.");
        }
      }

      if (attempts >= maxAttempts) {
        System.out.println("\n        Sorry, you've run out of attempts. The correct number was " + targetNumber + ".\n");
      }

      System.out.print("Do you want to play again? (yes/no): ");
      String playAgainInput = scanner.nextLine();
      playAgain = playAgainInput.equalsIgnoreCase("yes");

    }

    System.out.println("\n\n         Game over! Your score: " + score + "\n\n");
    scanner.close();
  }
}
