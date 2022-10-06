package novi.slotmachines;

import novi.Playable;
import java.util.Scanner;

 public class SlotMachineGame implements Playable {

     protected int coins = 100;
     private  int maxCoins = coins;
     private Scanner inputScanner;
     private SlotMachine slotMachine;

     public SlotMachineGame(Scanner inputScanner) {
         this.inputScanner = inputScanner;
     }

     public void playGame() {
         //refresh the scanner because otherwise it will (somehow) not detect the first input.
         inputScanner = new Scanner(System.in);

         boolean inputInvalid = true;
         while (inputInvalid) {
             System.out.println("What machine do you want to play?");
             System.out.println("press 1 for \"Penny Machine\"");
             System.out.println("press 2 for \"High Roller Machine\"");
             System.out.println("press 3 for \"Five Reeler Machine\"");

             int machineSelection;
             machineSelection = inputScanner.nextInt();
             if (machineSelection > 0 && machineSelection <= 3) {
                 inputInvalid = false;
                 if (setupGame(machineSelection)) {
                     gameLoop();
                 }
             } else {
                 System.out.println("Dat is geen geldige selectie!");
             }
             System.out.println();
         }
     }

     private boolean setupGame(int machineSelection) {
         switch (machineSelection) {
             case 1 -> {
                 slotMachine = new Penny();
             }
             case 2 -> {
                 slotMachine = new HighRoller();
             }
             case 3 -> {
                 slotMachine = new FiveReeler();
             }
             default -> {
                 System.out.println("Invalid input");
                 return false;
             }
         }

         // check if player has enough coins
         if (coins >= slotMachine.getRollPrice()) {
             slotMachine.renderIntro();
             return true;

         } else {
             System.out.println("Sorry, you do not have enough coins to play the " + slotMachine.name);
             return false;
         }
     }

     private void gameLoop() {
         boolean automatic = false;
         System.out.println("Play automatic? (y/n)");
         inputScanner = new Scanner(System.in);
         char yn = inputScanner.nextLine().charAt(0);
         if (yn == 'y') {
             automatic = true;
         }

         boolean gameIsRunning = true;
         while (gameIsRunning) {
             System.out.println("You now have a total of " + coins + " coins.");

             if (!automatic) {
                 char choice;
                 System.out.println("Press \"r\" to roll, or \"q\" to quit.");
                 inputScanner = new Scanner(System.in);
                 String input = inputScanner.nextLine();
                 choice = (input == "") ? 'r' : input.charAt(0);
                 if (choice == 'r') {
                     // OK, Lets Roll!!!
                     gameIsRunning = roll();
                 } else if (choice == 'q') {
                     gameIsRunning = false;
                 } else {
                     System.out.println("Invalid input. Terminating program...");
                     gameIsRunning = false;
                 }
             }
             gameIsRunning = roll();
         }
         System.out.println("Bye bye!");
     }

     private boolean roll () {
         boolean gameIsRunning = true;
         if (coins >= slotMachine.getRollPrice()) {
             coins -= slotMachine.getRollPrice();
             int payout = slotMachine.roll();
             coins += payout;
             if (coins > maxCoins) {
                 maxCoins = coins;
             }
         } else {
             System.out.println("Sorry, you do not have enough coins to play the " + slotMachine.name);
             System.out.println("At one point , you had " + maxCoins + " coins.");
             System.out.println("Go home!!!");
             gameIsRunning = false;
         }
         return gameIsRunning;
     }
 }