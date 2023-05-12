
import javax.management.StringValueExp;
import java.util.ArrayList;
import java.util.Scanner;

public class Players {
    private final static String DATA_FILE = "data/data.csv";

    /**
     * Complete this method
     * to read player's form the data file
     * @return
     */
    public static ArrayList<Player> readPlayerDataFromFile() {
        //New arrayList being set up
        //using Player as its type
        ArrayList<Player> players = new ArrayList<>();
        //Add the read file code here
        //another arrayList being generated to read players from the file
        ArrayList<String> playerData = FileIO.readDataFromFile(Players.DATA_FILE);
        //For loop to iterate through the arrayList that reads from the file
        //and adds the elements from it to the new arrayList
        //that stores a list of Player's object
        for (String s: playerData) {
            players.add(new Player(s));
        }
        return players;
    }


    /**
     * Complete this method to update the specific player data in the list if it already exists or add the player to
     * the list if it doesn't already exist
     * Write the whole list back to the file
     * @param
     * @param players
     */

    public static void writePlayerDataToFile(Player newPlayer, ArrayList<Player> players) {
        //Add write file code here
        //ArrayList is created
        ArrayList<String> animalData = new ArrayList<>();
        //boolean is created
        boolean added = false;
        String pass = new String();
        //for each loop created to loop through the arraylist that stores
        //the player.s info
        //if the player is already there it will update it
        //if not it will write it to the file
        for(Player player : players){
            if(player.getName().equals(newPlayer.getName())){
                player.setHighScore();
            }
            else {
                animalData.add(player.toCSVString());
            }
        }
        if (!added){
            animalData.add(newPlayer.toCSVString());
        }
        FileIO.writeDataToFile(DATA_FILE, animalData);
    }





    /**
     * Complete the method to check if the player is currently in the list
     * @param players
     * @param name
     * @return Return the player object if the player exists in the ArrayList or null if not found
     */
    public static Player checkNameExists(ArrayList<Player> players, String name) {
        //for loop is created to go through the players that are stored in the file
        //if the player exists it returns
        for(Player player : players){
            if(player.getName().equals(name)){
                return player;
            }
        }
        return null;
    }

    /**
     * Complete the method that uses checkNameExists above
     * If the name is found ask the user if they want to load the existing data
     * If they do want to load the data return the relevant player object
     * Otherwise return a new Player object.
     * @param players
     * @return
     */
    public static Player getPlayer(ArrayList<Player> players) {
        Player player = null;
        //Do the scanner stuff to get the player name
        //use checkNameExists to see if that name was in the file
        //If the name already exists ask them if they want to load data
        System.out.println("Enter name : ");
        Scanner scanner = new Scanner(System.in);
        String name;
        name = scanner.nextLine();
        //This uses the checkName method to see if the player
        //that is being netered by the user is already in the file or not
        player = checkNameExists(players, name);
        //This condition if the player is in the file
        //it asks the user if they want to load player's data
        //if yes it will print out the player
        //if no it will cotinue plaing
        if (player != null) {
            System.out.print(player.getName() + " already exists. Load data? (Y/N): ");
            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("Y")) {
                System.out.println(player);
            }
            if (choice.equalsIgnoreCase("N")) {
                System.out.println("Name exists continue playing");
            }
            //If the player does not exist in the file
            //it will return a new player and store it in the file
        }else if(player == null) {
            player = new Player(name);
        }
        return player;
    }
}



