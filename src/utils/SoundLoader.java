package utils;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.net.URL; 

public class SoundLoader {

    private static Clip buttonEnterSoundClip;
    private static Clip backButtonSoundClip;
    private static Clip comboBoxSoundClip;
    private static Clip eatingSoundClip; 
    private static Clip gameOverSoundClip; 
    private static Clip backgroundMusicClip; 
    private static Clip gameMenuMusicClip; 
    private static boolean isBackgroundMusicPlaying = false;
    private static boolean isgameMenuMusicPlaying = false;

    static {
        
        loadSound("/assets/sounds/ButtonEnter.wav", clip -> buttonEnterSoundClip = clip);
        loadSound("/assets/sounds/ButtonBack.wav", clip -> backButtonSoundClip = clip);
        loadSound("/assets/sounds/ComboBox.wav", clip -> comboBoxSoundClip = clip);
        loadSound("/assets/sounds/SnakeEating.wav", clip -> eatingSoundClip = clip); 
        loadSound("/assets/sounds/GameOverSinglePlayer.wav", clip -> gameOverSoundClip = clip);
        loadSound("/assets/sounds/Backsound.wav", clip -> backgroundMusicClip = clip); 
        loadSound("/assets/sounds/GameMenu.wav", clip -> gameMenuMusicClip = clip); 
    }

    private static void loadSound(String path, java.util.function.Consumer<Clip> setter) {
        try {
            URL soundUrl = SoundLoader.class.getResource(path);
            if (soundUrl == null) {
                System.err.println("Sound file not found: " + path);
                return;
            }
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundUrl);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            setter.accept(clip);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.err.println("Failed to load sound " + path + ": " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void playClip(Clip clip) {
        if (clip != null) {
            if (clip.isRunning()) {
                clip.stop();
            }
            clip.setFramePosition(0);
            clip.start();
        }
    }
    
    
    public static void playButtonEnterSound() {
        playClip(buttonEnterSoundClip);
    }
    
    public static void playBackButtonSound() {
        playClip(backButtonSoundClip);
    }
    
    public static void playComboBoxSound() {
        playClip(comboBoxSoundClip);
    }

    
    public static void playEatingSound() {
        playClip(eatingSoundClip);
    }

    
    public static void playGameOverSound() {
        playClip(gameOverSoundClip);
    }

   
    
    public static void closeAllSounds() {
        if (buttonEnterSoundClip != null) buttonEnterSoundClip.close();
        if (backButtonSoundClip != null) backButtonSoundClip.close();
        if (comboBoxSoundClip != null) comboBoxSoundClip.close();
        if (eatingSoundClip != null) eatingSoundClip.close();
        if (gameOverSoundClip != null) gameOverSoundClip.close();
    }

    
    public static void playBackgroundMusic() {
        if (backgroundMusicClip != null) {
            if (backgroundMusicClip.isRunning()) {
                isBackgroundMusicPlaying = true; 
                return;
            }
            backgroundMusicClip.setFramePosition(0);
            backgroundMusicClip.loop(Clip.LOOP_CONTINUOUSLY); 
            isBackgroundMusicPlaying = true;
        } else {
            System.err.println("Background music clip is null. It might not have loaded correctly.");
        }
    }

    public static void playGameMenuSound() {
        if (gameMenuMusicClip != null) {
            if (gameMenuMusicClip.isRunning()) {
                isgameMenuMusicPlaying = true; 
                return;
            }
            gameMenuMusicClip.setFramePosition(0);
            gameMenuMusicClip.loop(Clip.LOOP_CONTINUOUSLY);
            isgameMenuMusicPlaying = true;
        } else {
            System.err.println("Background music clip is null. It might not have loaded correctly.");
        }
    }
    
    public static void stopBackgroundMusic() {
        if (backgroundMusicClip != null && backgroundMusicClip.isRunning()) {
            backgroundMusicClip.stop();
            backgroundMusicClip.setFramePosition(0); 
            isBackgroundMusicPlaying = false;
        }
    }
    
    public static void stopGameMenuSound() {
        if (gameMenuMusicClip != null && gameMenuMusicClip.isRunning()) {
            gameMenuMusicClip.stop();
            gameMenuMusicClip.setFramePosition(0); 
            isgameMenuMusicPlaying = false;
        }
    }
    
    public static boolean isBackgroundMusicPlaying() {
        return isBackgroundMusicPlaying;
    }
    
     public static boolean isGameMenuPlaying() {
        return isgameMenuMusicPlaying;
    }
}