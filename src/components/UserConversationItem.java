package src.components;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import src.entities.Client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.io.IOException;

public class UserConversationItem extends JButton {
    private UnreadMessagesNotification unreadMessagesNotification;
    private int unreadMessages;
    private Client user;

    public UserConversationItem(Client user) throws FontFormatException, IOException {
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setMaximumSize(new Dimension(250, 55));
        this.setBorder(BorderFactory.createEmptyBorder());
        this.setIsFocused(false);

        this.user = user;
        unreadMessages = 0;

        Image userAvatarImage = new Assets().getAvatarImage(user.getAvatarId()).getImage().getScaledInstance(50, 50,
                java.awt.Image.SCALE_SMOOTH);

        ImageIcon userAvatarIcon = new ImageIcon(userAvatarImage);

        JLabel userAvatarLabel = new JLabel();
        userAvatarLabel.setAlignmentX(JLabel.LEFT);
        userAvatarLabel.setIcon(userAvatarIcon);

        Font mainFont = new Assets().getMainFont().deriveFont(Font.BOLD, 17);

        JLabel userNameLabel = new JLabel(user.getName());
        userNameLabel.setFont(mainFont);

        this.unreadMessagesNotification = new UnreadMessagesNotification(unreadMessages);
        this.unreadMessagesNotification.setAlignmentX(Box.RIGHT_ALIGNMENT);

        this.add(Box.createHorizontalStrut(7));
        this.add(userAvatarLabel);
        this.add(Box.createHorizontalStrut(7));
        this.add(userNameLabel);
        this.add(Box.createHorizontalStrut(22 + (12 - user.getName().length()) * 8));
        this.add(this.unreadMessagesNotification);
    }

    public void setUnreadMessages(int unreadMessages) {
        this.unreadMessages = unreadMessages;

        // If there are no unread messages, hide the notification
        if (unreadMessages <= 0) {
            this.unreadMessagesNotification.setVisible(false);
            this.unreadMessages = 0;
            return;
        }

        // If there are unread messages, check if the notification is visible
        if (!this.unreadMessagesNotification.isVisible()) {
            this.unreadMessagesNotification.setVisible(true);
        }

        String unreadMessagesString = Integer.toString(unreadMessages);
        this.unreadMessagesNotification.setText(unreadMessagesString);
    }

    public int getUnreadMessages() {
        return this.unreadMessages;
    }

    public void setIsFocused(boolean isFocused) {
        if (isFocused) {
            this.setBackground(new Color(215, 217, 220));
        } else {
            this.setBackground(Color.white);
        }
    }

    public int getChannelId() {
        return this.user.getChannelId();
    }
}
