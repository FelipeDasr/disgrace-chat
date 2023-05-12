package src.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JTextArea;

import src.entities.ClientMessage;

class UserMessageItem extends JTextArea {
    private final ClientMessage userMessage;

    public UserMessageItem(ClientMessage userMessage) throws FontFormatException, IOException {
        this.userMessage = userMessage;

        this.setFont(new Assets().getMainFont().deriveFont(Font.BOLD, 19));
        this.setText(this.getFormatedContent());
        this.setForeground(this.getColor());

        this.setBorder(BorderFactory.createEmptyBorder(0, 10, 	0, 10));

        this.setLineWrap(true);
        this.setWrapStyleWord(true);
        this.setEditable(false);
    }

    private String getFormatedContent() {
        return "[" + this.userMessage.getFormatedHourString() + "h - " 
                + this.userMessage.getUser().getName() + "]: " 
                    + this.userMessage.getContent();
    }

    private Color getColor() {
        switch(this.userMessage.getUser().getAvatarId()) {
            case 0:
                return new Color(236, 38, 143);

            case 1:
                return new Color(0, 152, 218);	

            case 2:
                return new Color(203, 115, 113);

            case 3:
                return new Color(161, 123, 182);

            case 5:
                return new Color(0, 168, 89);

            case 6:
                return new Color(96, 95, 84);

            case 7:
                return new Color(237, 47, 89);

            default:
                return new Color(32, 30, 30);
        }
    }
}
