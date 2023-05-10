package src.components;

import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.JLabel;

import java.awt.RenderingHints;
import java.io.IOException;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;

public class UnreadMessagesNotification extends JLabel {

    public UnreadMessagesNotification(int unreadMessages) throws FontFormatException, IOException {
        Border emptyBorder = BorderFactory.createEmptyBorder(3, 7, 4, 8);

        this.setOpaque(false);
        this.setVisible(false);
        this.setBorder(emptyBorder);

        this.setBackground(new Color(242, 63, 66));
        this.setText(Integer.toString(unreadMessages));
        this.setForeground(Color.WHITE);

        this.setFont(new Assets().getMainFont().deriveFont(Font.BOLD, 11));
    }

    // Paint the round background and label.
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 27, 27);

        super.paintComponent(g);
        g2.dispose();
    }
}