import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu{

    private JLabel mainLabel;
    public JButton newUserButton;
    public JButton existingUserButton;
    private JPanel mainPanel;


    /**
     * to show the main menu
     * with new and existing buttons
     * @param player
     */
    public MainMenu(Player player) {
        UserForm userForm = new UserForm(player);
        newUserButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */

            //to open the new user form
            @Override
            public void actionPerformed(ActionEvent e) {
                userForm.userLabel.setText("New User");
                openNextform(player);
            }
        });

        //to open the existing user form
        existingUserButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
              userForm.userLabel.setText("Existing user");
             openNextform1(player);
            }
        });
    }


    private void openNextform(Player player){
        SwingUtilities.getWindowAncestor(mainPanel).dispose();
        UserForm.main(player);
    }

    private void openNextform1(Player player){
        SwingUtilities.getWindowAncestor(mainPanel).dispose();
        ExistingUser.main(player);
    }

    public static void main(String[] args){
        String s = new String();
        mainform(new Player(s));
    }

    public static void mainform(Player player) {
        JFrame frame = new JFrame("MainMenu");
        frame.setContentPane(new MainMenu(player).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
