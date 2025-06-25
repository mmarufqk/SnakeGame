
import ui.MainMenu;
import utils.SoundLoader;

import javax.swing.SwingUtilities;


public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                SoundLoader.playBackgroundMusic(); 
                MainMenu mainMenu = new MainMenu();
                mainMenu.setVisible(true);
            }
        });
    }
}