import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ExistingUser {

    private final static String DATA_FILE = "data/passwordata.csv";
    private JPanel mainPanel;
    public JTextField userTextField;
    private JPasswordField passwordtextField;

    private static int echoChar;
    private JCheckBox showPasswordCheckBox;
    private JButton enterButton;
    private JButton clearButton;

    public ExistingUser(Player player) {
        //to check create echoChar for the password to display it in dots
        echoChar = passwordtextField.getEchoChar();
        enterButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                //creating a new arrayList
                ArrayList<Player> players = Players.readPlayerDataFromFile();
                String user = userTextField.getText();
                //String to store in the value of the password
                String pass = String.valueOf(passwordtextField.getPassword());
                //creating an arrayList to read from the file
                ArrayList<String> passwordList = new ArrayList<>();
                //creating a new instance of Player to store in the value of the checkName in it
                Player playerInfo = null;
                playerInfo = Players.checkNameExists(players, user);
                //the following if conditions are to check for the password if they met
                //the criteria or not
                //this to check if the player exists
                if (playerInfo == null) {
                    JOptionPane.showMessageDialog(mainPanel,
                            "User does not exists please enter existing User");
                } if(userTextField.getText().isEmpty()){
                    JOptionPane.showMessageDialog(mainPanel,
                            "Please enter a username");
                }
                //if the password is empty then it will return an error message
                if(pass.isEmpty()){
                    JOptionPane.showMessageDialog(mainPanel,
                            "Please enter a password");
                }
                //if the password is less than 8 characters
                if (pass.length() < 8) {
                    JOptionPane.showMessageDialog(mainPanel,
                            " Your password must be 8 characters long");
                }
                //to check if the password contains capital letters
                String upperCaseChars = "(.*[A-Z].*)";
                if (!pass.matches(upperCaseChars)) {
                    JOptionPane.showMessageDialog(mainPanel,
                            "Your password must contain at least one Upper Case letter ");

                }String lowerCaseChars = "(.*[a-z].*)";
                if(!pass.matches(lowerCaseChars)){
                    JOptionPane.showMessageDialog(mainPanel,
                            "Your password must contain at least one lower Case letter ");
                }
                //to check if the passwords comatins a number
                String numbers = "(.*[0-9].*)";
                if (!pass.matches(numbers)) {
                    JOptionPane.showMessageDialog(mainPanel,
                            "Your password must contain at least one number");
                }
                // to check if the password comtains a special character
                String specialChars = "(.*[@,#,$,%].*$)";
                if (!pass.matches(specialChars)) {
                    JOptionPane.showMessageDialog(mainPanel,
                            "Your password must contain at least one special character");
                }
                //to open the next form only if all conditions above are met
                else if (playerInfo != null && pass.matches(lowerCaseChars)&&pass.matches(specialChars)&&pass.matches(numbers)&&pass.matches(numbers)&& pass.length() > 8) {
                    openNext(player);
                }
                writePasswordDataFile(pass, passwordList);
            }
        });
        clearButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                //to adjust the usettextfield back to null
                userTextField.setText(null);
                //to adjust the passwordfield back to null
                passwordtextField.setText(null);
            }
        });

        showPasswordCheckBox.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                //is the password is checked then it shows the password
                if(showPasswordCheckBox.isSelected()){
                    passwordtextField.setEchoChar((char) 0);
                }
                //if not then it will show the password in dots
                else{
                    passwordtextField.setEchoChar((char) echoChar);
                }
            }
        });
    }

    public static void main(Player player) {
        JFrame frame = new JFrame("UserForm");
        frame.setContentPane(new ExistingUser(player).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    public static void writePasswordDataFile(String pass, ArrayList<String> passList){
        ArrayList<String> dataList = new ArrayList<>();
        for(String player: passList){
            if(!pass.equals(player)){
                dataList.add(pass);
            }
            FileIO.writeDataToFile(DATA_FILE, passList);
        }
    }

    private void openNext(Player player){
        SwingUtilities.getWindowAncestor(mainPanel).dispose();
        GameForm.main(player);
    }
}
