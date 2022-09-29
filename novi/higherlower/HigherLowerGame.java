package novi.higherlower;

import novi.Playable;

import java.util.Random;
import java.util.Scanner;

public class HigherLowerGame implements Playable {

    private Scanner scanner;
    private boolean gameIsRunning = false;


    public HigherLowerGame(Scanner scanner) {
        this.scanner = scanner;
    }

    public void playGame() {
        gameIsRunning = true;

        // Here you should generate the number to guess
        Random random = new Random();
        //    Het spel kiest een willekeurig nummer tussen 0 en 100 dat geraden moet worden.
        int randomNumber = random.nextInt(1,101);
        //        Het spel zet het aantal gespeelde beurten op 0
        int numTurns = 0;
        int guess;

        while (gameIsRunning) {
            numTurns++;
            System.out.println("Guss a number between 1 and 100: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Dat is geen geldig cijfer!");
                scanner.next(); // belangrijk!!
            }
            guess = scanner.nextInt();

            System.out.printf("You guessed: %s \n", guess);
            if (guess > randomNumber) {
                System.out.println("That number is too high!");
            } else if (guess < randomNumber) {
                System.out.println("That number is too low!");
            } else {
                System.out.printf("Correct! You guessed the number in %s turns.", numTurns);
                gameIsRunning = false;
            }
        }
    }
}
//
//    De speler start het spel (je start de applicatie door het starten van de ‘main’ methode)
//    Het spel kiest een willekeurig nummer tussen 0 en 100 dat geraden moet worden.
//        Het spel zet het aantal gespeelde beurten op 0
//        Het spel print de regel ‘Make a guess’
//        De speler raadt een nummer
//        Het spel hoogt het aantal gespeelde beurten op met 1
//        Was het nummer te hoog? Het spel print de regel `That number is too high!` (terug naar stap 4)
//        Was het nummer te laag? Het spel print de regel `That number is too low!` (terug naar stap 4)
//        Komt het nummer overeen? Het spel print de regel `Correct! You guessed the number in x turns` waarbij x het aantal gespeelde beurten is. (het spel wordt gestopt)