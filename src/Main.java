import frame.GameFrame;
import panel.MainMenuPanel;
import utils.SoundLoader;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SoundLoader.playBackgroundMusic();
            GameFrame gameFrame = new GameFrame(new MainMenuPanel());
            gameFrame.setVisible(true);
        });
    }
}