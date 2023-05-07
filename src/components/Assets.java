package src.components;

import javax.swing.ImageIcon;

import java.awt.Font;
import java.awt.Image;
import java.awt.FontFormatException;

import java.io.IOException;
import java.io.InputStream;

public class Assets {
    public Font getMainFont() throws FontFormatException, IOException {
        String fontPath = "src/assets/fonts/MainFont.ttf";
        InputStream fontFile = getClass().getClassLoader().getResourceAsStream(fontPath);

        return Font.createFont(Font.TRUETYPE_FONT, fontFile);
    }

    public Image getAppIcon() {
        return this.getImage("src/assets/images/DisgraceIcon1.png");
    }

    public Image getAppLogo() {
        return getImage("src/assets/images/DisgraceLogo.png");
    }

    private Image getImage(String path) {
        return new ImageIcon(path).getImage();
    }
}
