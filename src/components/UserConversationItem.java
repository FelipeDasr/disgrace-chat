package src.components;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.io.IOException;

class UserConversationItem extends JPanel {
    public UserConversationItem(String userName) throws FontFormatException, IOException {
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setMaximumSize(new Dimension(200, 65));

        Image userAvatarImage = new Assets().getAppIcon();
		ImageIcon resizedUserAvatar = new ImageIcon(userAvatarImage.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH));
		
		JLabel userAvatarLabel = new JLabel();
        userAvatarLabel.setAlignmentX(JLabel.LEFT);
		userAvatarLabel.setIcon(resizedUserAvatar);

        Font mainFont = new Assets().getMainFont().deriveFont(Font.BOLD, 17);

        JLabel userNameLabel = new JLabel(userName);
        userNameLabel.setFont(mainFont);

        this.add(userAvatarLabel);
        this.add(Box.createHorizontalStrut(7));
        this.add(userNameLabel);
    }
}
