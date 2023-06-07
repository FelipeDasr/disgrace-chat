package src.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class ErrorMessage extends JLabel {
    public ErrorMessage() throws FontFormatException, IOException {
        super("");
        this.setFont(new Assets().getMainFont().deriveFont(Font.BOLD, 16));
        this.setAlignmentX(JTextField.CENTER_ALIGNMENT);
        this.setForeground(Color.RED);
    }
}
