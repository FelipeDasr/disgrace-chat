package src.components;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import src.entities.Client;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.io.IOException;

public class UserConversationItem extends JPanel {
    private UnreadMessagesNotification unreadMessagesNotification;
    private int unreadMessages;

    public UserConversationItem(Client user) throws FontFormatException, IOException {
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setMaximumSize(new Dimension(225, 65));
        unreadMessages = 0;

        Image userAvatarImage = new Assets().getAvatarImage(user.getAvatarId()).getImage().getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);;
		ImageIcon userAvatarIcon = new ImageIcon(userAvatarImage);
		
		JLabel userAvatarLabel = new JLabel();
        userAvatarLabel.setAlignmentX(JLabel.LEFT);
		userAvatarLabel.setIcon(userAvatarIcon);

        Font mainFont = new Assets().getMainFont().deriveFont(Font.BOLD, 17);

        JLabel userNameLabel = new JLabel(user.getName());
        userNameLabel.setFont(mainFont);

        this.unreadMessagesNotification = new UnreadMessagesNotification(unreadMessages);

        this.add(Box.createHorizontalStrut(7));
        this.add(userAvatarLabel);
        this.add(Box.createHorizontalStrut(7));
        this.add(userNameLabel);
        this.add(Box.createHorizontalStrut(40));
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
}
