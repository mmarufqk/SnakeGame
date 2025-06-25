package utils;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects; 

public class ImageLoader {

    public static BufferedImage loadImage(String path, JPanel panel) {
        try {
            return ImageIO.read(Objects.requireNonNull(ImageLoader.class.getResource(path)));
        } catch (IOException | NullPointerException e) {
            System.err.println("Gagal memuat gambar dari: " + path + " - " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(panel, "Gagal memuat gambar: " + path + ". Game mungkin tidak tampil sempurna.", "Error Gambar", JOptionPane.ERROR_MESSAGE);
            return null; 
        }
    }

    
    public static BufferedImage[] loadFoodImages(String[] foodNames, JPanel panel) {
        BufferedImage[] images = new BufferedImage[foodNames.length];
        String baseDir = "/assets/images/"; 
        for (int i = 0; i < foodNames.length; i++) {
            String path = baseDir + foodNames[i] + ".png";
            try {
                images[i] = ImageIO.read(Objects.requireNonNull(ImageLoader.class.getResource(path)));
            } catch (IOException | NullPointerException e) {
                System.err.println("Gagal memuat gambar makanan: " + path + " - " + e.getMessage());
                e.printStackTrace();
                images[i] = null; 
            }
        }
        return images;
    }

    public static BufferedImage loadBorderImage(JPanel panel) {
    String path = "/assets/images/Border.png";
    System.out.println("Mencoba memuat gambar border dari: " + path); 
    try {
        return ImageIO.read(Objects.requireNonNull(ImageLoader.class.getResource(path)));
    } catch (IOException | NullPointerException e) {
        System.err.println("Gagal memuat gambar border dari: " + path + " - " + e.getMessage());
        e.printStackTrace();
        JOptionPane.showMessageDialog(panel, "Gagal memuat gambar border. Border akan digambar dengan warna solid.", "Error Gambar Border", JOptionPane.WARNING_MESSAGE);
        return null;
    }
    }
    
    public static BufferedImage loadBackgroundImage(JPanel panel) {
        
        String path = "/assets/images/BackgroundMainMenu.png";
        
        System.out.println("Mencoba memuat gambar background dari: " + path);
        try {
            return ImageIO.read(Objects.requireNonNull(ImageLoader.class.getResource(path)));
        } catch (IOException | NullPointerException e) {
            System.err.println("Gagal memuat gambar bg dari: " + path + " - " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(panel, "Gagal memuat gambar background. Latar belakang akan berwarna solid.", "Error Gambar Background", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    public static BufferedImage loadGameBackgroundImage(JPanel panel) {
        String path = "/assets/images/GameBackground.png";
        System.out.println("Mencoba memuat gambar background dari: " + path);
        try {
            return ImageIO.read(Objects.requireNonNull(ImageLoader.class.getResource(path)));
        } catch (IOException | NullPointerException e) {
            
            System.err.println("Gagal memuat gambar background dari: " + path + " - " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(panel, "Gagal memuat gambar background. Game mungkin tampil tanpa latar belakang.", "Error Gambar Background", JOptionPane.WARNING_MESSAGE);
            return null;
        }
    }
    
    public static BufferedImage loadSinglePlayerBackgroundImage(JPanel panel) {
        String path = "/assets/images/SinglePlayerBackground.png";
        System.out.println("Mencoba memuat gambar background dari: " + path);
        try {
            return ImageIO.read(Objects.requireNonNull(ImageLoader.class.getResource(path)));
        } catch (IOException | NullPointerException e) {
            
            System.err.println("Gagal memuat gambar background dari: " + path + " - " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(panel, "Gagal memuat gambar background. Game mungkin tampil tanpa latar belakang.", "Error Gambar Background", JOptionPane.WARNING_MESSAGE);
            return null;
        }
    }
    
    public static BufferedImage loadMultiPlayerBackgroundImage(JPanel panel) {
        String path = "/assets/images/MultiPlayerBackground.png";
        System.out.println("Mencoba memuat gambar background dari: " + path);
        try {
            return ImageIO.read(Objects.requireNonNull(ImageLoader.class.getResource(path)));
        } catch (IOException | NullPointerException e) {
            
            System.err.println("Gagal memuat gambar background dari: " + path + " - " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(panel, "Gagal memuat gambar background. Game mungkin tampil tanpa latar belakang.", "Error Gambar Background", JOptionPane.WARNING_MESSAGE);
            return null;
        }
    }

        public static BufferedImage loadPreviewImage(String snakeColor, String borderOption, JPanel panel) {
        String borderSuffix = borderOption.equals("With Border") ? "Border" : "NoBorder";
        
        String path = "/assets/images/Preview" + snakeColor + borderSuffix + ".png";
        
        System.out.println("Mencoba memuat gambar preview dari: " + path);
        try {
            return ImageIO.read(Objects.requireNonNull(ImageLoader.class.getResource(path)));
        } catch (IOException | NullPointerException e) {
            System.err.println("Gagal memuat gambar preview dari: " + path + " - " + e.getMessage());
            e.printStackTrace();
            return null; 
        }
    }
        
        
        public static BufferedImage loadYappingImage(JPanel panel) {
        String path = "/assets/images/Yapping.png";
        System.out.println("Mencoba memuat gambar background dari: " + path);
        try {
            return ImageIO.read(Objects.requireNonNull(ImageLoader.class.getResource(path)));
        } catch (IOException | NullPointerException e) {
            
            System.err.println("Gagal memuat gambar background dari: " + path + " - " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(panel, "Gagal memuat gambar background. Game mungkin tampil tanpa latar belakang.", "Error Gambar Background", JOptionPane.WARNING_MESSAGE);
            return null;
        }
    }
        
        public static BufferedImage loadDeveloperPhotoImage(JPanel panel) {
        String path = "/assets/images/DeveloperPhoto.png";
        System.out.println("Mencoba memuat gambar background dari: " + path);
        try {
            return ImageIO.read(Objects.requireNonNull(ImageLoader.class.getResource(path)));
        } catch (IOException | NullPointerException e) {
            
            System.err.println("Gagal memuat gambar background dari: " + path + " - " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(panel, "Gagal memuat gambar background. Game mungkin tampil tanpa latar belakang.", "Error Gambar Background", JOptionPane.WARNING_MESSAGE);
            return null;
        }
    }
}