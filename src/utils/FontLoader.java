package utils;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.JComponent; 


public class FontLoader {

    private static Font customFont = null;
    private static Font getCustomFont() {
        if (customFont == null) {
            try {
                
                InputStream is = FontLoader.class.getResourceAsStream("/assets/fonts/EASyText.ttf");
                if (is != null) {
                    customFont = Font.createFont(Font.TRUETYPE_FONT, is);
                    is.close(); 
                } else {
                    System.err.println("Font file not found: /assets/fonts/EASyText.ttf");
                }
            } catch (IOException | FontFormatException e) {
                e.printStackTrace();
                System.err.println("Error loading custom font: " + e.getMessage());
            }
        }
        return customFont;
    }
    
    public static void applyFont(JComponent component, float size) {
        Font fontToApply = getCustomFont();
        if (fontToApply != null) {
            component.setFont(fontToApply.deriveFont(size));
        } else {
            component.setFont(new Font("Times New Roman", Font.PLAIN, (int) size));
            System.err.println("Applying default Serif font to " + component.getClass().getSimpleName());
        }
    }
    
    public static void applyFont(JComponent component, int style, float size) {
        Font fontToApply = getCustomFont();
        if (fontToApply != null) {
            component.setFont(fontToApply.deriveFont(style, size));
        } else {
            component.setFont(new Font("Times New Roman", style, (int) size));
            System.err.println("Applying default Serif font to " + component.getClass().getSimpleName());
        }
    }
}