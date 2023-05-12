import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GameForm {
    private JPanel mainPanel;
    private JLabel gameFomLabel;
    private JTextField diceField;
    private JButton rollDIceButton;
    private JButton newGameButton;
    private JButton saveGameButton;
    private JTextPane diceRollTextPane;
    public JLabel userNameText;
    private JLabel highscoreLabel;
    private JTextField statusTextField;
    private JTextField usernameTextField;
    private JTextField scoreTextField;
    private int counter=0;
    private int maximum=5;

    /**
     * This form is to show the dice roll with the game status
     * with the NEW GAME button and SAVE GAME BUTTON
     * @param player
     */
    public GameForm(Player player){
        //This button is to roll the dice only five times
        rollDIceButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                //creating a new instance for the dice class
                Dice dice = new Dice();
                //creating an array to recall the method from the dice list to roll five random numbers
                Integer[] roll = dice.getDiceList();
                //creating a new string
                String string = new String();
                //if coindition to control how many times the dice is going to roll
                if (counter < maximum) {
                    //to recall the method of checkMachuPichu to calculate teh score correcrlty
                    string = Game.checkMachuPichu(roll, player);
                    String finalString = string;
                    //to set show the roll of the dice
                    diceField.setText(finalString);
                    //to set the text field to the mpStatus of the player
                    statusTextField.setText(player.getStatusStr());
                    //to increase the counter each time it loops
                    counter++;
                    //to show the score of the player
                    scoreTextField.setText("Score: " + player.getScore());
                }
            }
        });

        /**
         * This button is to set everything back to a new status as if they started a new game
         */
        newGameButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                //to set the counter to 0 to roll the dice from the beginning
                counter = 0;
                //to set the mpStatus to NO_ELEMENTS to begin counting
                //the status from the beginning
                player.setMpStatus(Player.NO_ELEMENTS);
                //to set the dicefield text to null
                 diceField.setText(null);
                 //to set the text field of the status to null
                 statusTextField.setText(null);
                 //to set the score back to 0
                player.setScore(0);
                //to set the score field to only score
                 scoreTextField.setText("Score ");
            }
        });

        /**
         * to save the data of the user
         */
        saveGameButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                //creating an arraylist to read players from the file
                ArrayList<Player> playerArrayList = Players.readPlayerDataFromFile();
                //to set the highscore of the user
                player.setHighScore();
                //to set back to the arraylist
                Players.writePlayerDataToFile(player, playerArrayList);
            }
        });
    }

    /**
     * the main of the class
     * @param player
     */
    public static void main(Player player) {
        JFrame frame = new JFrame("GameForm");
        frame.setContentPane(new GameForm(player).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

}
