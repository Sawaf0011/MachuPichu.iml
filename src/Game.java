import java.util.*;

public class Game {
    private final int MAX_TURN = 5;
    private static final int BASE = 6;
    private static final int MIDDLE = 3;
    private static final int TOP = 1;


    /**
     * Complete the method that calculates the accurate score for each
     * set of 5 dice
     * Ensure you follow the rules specified in the brief
     * the process of how I calculated and checked for the special characters
     * is explained briefly in the method itself
     * with gray comments
     * but in conclusion it finds a special character
     * change the mpStatus and change the special character to 0
     * until it reaches the TOP then it adds all the elements of the array
     * and continue adding after it
     *
     * @param currentRoll
     * @param player
     * @return
     */

    public static String checkMachuPichu(Integer[] currentRoll, Player player) {
//Used for clarity in code and to avoid hardcoded ints
//If the code is sorted the calculation is significantly easier
        Arrays.sort(currentRoll, Collections.reverseOrder());
//We only need to do this check if the player doesn't have all the elements
        if (player.getMpStatus() != Player.HAS_TOP) {
//Check all the dice
            for (int i = 0; i < currentRoll.length; i++) {
                int dice = currentRoll[i];
                switch (dice) {
                    case BASE -> {
                        if (player.getMpStatus() == Player.NO_ELEMENTS) {
                            player.setMpStatus(Player.HAS_BASE);
//If this dice counts towards the status set it to 0 in the array
                            currentRoll[i] = 0;
                        }
                    }
                    case MIDDLE -> {
                        if (player.getMpStatus() == Player.HAS_BASE) {
                            player.setMpStatus(Player.HAS_MIDDLE);
                            currentRoll[i] = 0;
                        }
                    }
                    case TOP -> {
                        if (player.getMpStatus() == Player.HAS_MIDDLE) {
                            player.setMpStatus(Player.HAS_TOP);
                            currentRoll[i] = 0;
                        }
                    }
                }
            }
        }
//Calculates the correct score
        if (player.getMpStatus() == Player.HAS_TOP) {
            for (int n : currentRoll) {
                player.incScore(n);
            }
        }
        return Arrays.toString(currentRoll);
    }



    /**
     * Complete the method to play a single turn of the game
     * Print out the current player details
     * Pause for a click at each turn
     * @param player
     * @return
     */
    private void playTurn(Player player) {
        //The counter is set here to increase by 1 every time the loop runs once
        int counter = 0;
        //This loop is create to generate five random numbers five times
        //MAX_TURN is use as a limit for how many times the loop should run
        while (counter < MAX_TURN) {
            //The dice is recalled here to use the methods in that class
            Dice dice = new Dice();
            //The array is set up to generate five random numbers using
            //getDiceList method from the Dice class
            Integer[] arrayDice = dice.getDiceList();
            //To print out the roll
            System.out.print(player.getName() + " rolled " );
            //Uses checkMachuPichu method to check up and compile with the rules of the games
            checkMachuPichu(arrayDice, player);
            //To print out the score and the players info and status
            System.out.println(player);
            System.out.println("Score is " + player.getScore());
            System.out.println("Click to continue");
            //The scanner is set up to pause for click at each turn
            Scanner scanner = new Scanner(System.in);
            scanner.nextLine();
            //counter increments by 1 until it is 5 to make the loop stop looping
            counter++;
        }
    }


    /**
     * Complete the method to read the Player file
     * Set up the player using the getPlayer method
     * Set up a computer player
     * Use an appropriate loop to loop until they no loner want to play the game
     * play an entire game between a human player and a computer player
     * Write the players data and the player data to the data file
     */
    public void playGame() {
        //Read the player file and save it in the Players class (using the method in the players class)
        //Use getPlayer method on players to get the PLayer data
        //The arrayList here is created to read the players from the file
        ArrayList<Player> playerData = Players.readPlayerDataFromFile();
        //This player object is created for the user to play with it
        Player player = Players.getPlayer(playerData);
        //This player object is for the computer
        Player player1 = new Player("Computer");
        //The boolean is set up to make a loop run until the player does not want to play against the computer anymore
        boolean status = true;
            //The while is chosen here to make the loop run under certain condition
            // (if boolean status is set to true)
        while(status){
            //These two code is to recall the playTurn method
            //to make the use play against a computer
            playTurn(player);
            playTurn(player1);
            //This condition is made if the user wins against the computer
            if(player.getScore() > player1.getScore()){
                System.out.println(player.getName() + " is the winner");

            }//This if the computer wins against the user
             else if (player1.getScore() > player.getScore()) {
                System.out.println("Computer is the winner ");
            }
            //This if the player and the computer had the same score
            //which means it is a draw
            else if (player1.getScore() == player.getScore()){
                System.out.println(" It's a draw ");
            }
            //This is to ask the user if they want to play again
            System.out.println("Do you want to play again");
            //The scanner is created to let the user to input there answer
            Scanner scanner = new Scanner(System.in);
            String answer = scanner.nextLine();
            //If the user wants to play again the scores of both the user and the player
            //is set to 0 to restart the game
            //and also their mpStatus is set to NO_ELEMENTS
            //since they began the game from the beginning and still did not
            // any of the special characters
            if (answer.equalsIgnoreCase("yes")) {
                System.out.println("Do you want to play with same player ?");
                Scanner scanner1 = new Scanner(System.in);
                String option = scanner1.nextLine();
                if(option.equalsIgnoreCase("yes")){
                    Players.writePlayerDataToFile(player, playerData);
                }
                if (option.equalsIgnoreCase("no")) {
                    player.setHighScore();
                    Players.writePlayerDataToFile(player, playerData);
                    player = Players.getPlayer(playerData);
                }
                player.setScore(0);
                player.getScore();
                player1.setScore(0);
                player1.getScore();
                player.setMpStatus(Player.NO_ELEMENTS);
                player1.setMpStatus(Player.NO_ELEMENTS);
                status = true;
            }
            //If the user does not want to play again
            //the boolean status is set to false
            //for stopping the while loop from running
            if (answer.equalsIgnoreCase("no")) {
                status = false;
            }
            //This condition is set to let the user if they pressed enter by mistake
            // at the end instead of answering with yes or no
            //the loop stops running
            else if (answer.equalsIgnoreCase("")){
                break;
            }
        }
        //This is when the use finishes playing the game
        //if the user achieved a new highscore it is going to be updated
        player.setHighScore();
        //This is to write nto the file the player that is being played with
        Players.writePlayerDataToFile(player, playerData);
    }

}
