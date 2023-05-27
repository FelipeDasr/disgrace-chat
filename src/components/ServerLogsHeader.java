package src.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class ServerLogsHeader extends JPanel {
    JLabel connectedMembersSizeLabel;

    public ServerLogsHeader(String serverName, int connectedMembersSize) throws FontFormatException, IOException {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        Color commonBorderColor = new Color(195, 207, 217);
        Border commonLabelBorder = BorderFactory.createEmptyBorder(0, 40, 0, 0);

        JLabel serverNameLabel = new JLabel(serverName);
        serverNameLabel.setFont(new Assets().getMainFont().deriveFont(Font.PLAIN, 22));
        serverNameLabel.setBorder(commonLabelBorder);

        this.connectedMembersSizeLabel = new JLabel();
        this.connectedMembersSizeLabel.setFont(new Assets().getMainFont().deriveFont(Font.BOLD, 15));
        this.connectedMembersSizeLabel.setBorder(commonLabelBorder);
        this.updateConnectedMembersSize(connectedMembersSize);

        this.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, commonBorderColor));
        this.setMaximumSize(new Dimension(250, 100));
        this.setBackground(Color.white);

        this.add(Box.createVerticalStrut(18));
        this.add(serverNameLabel);
        this.add(this.connectedMembersSizeLabel);
    }

    public void updateConnectedMembersSize(int connectedMembersSize) {
        this.connectedMembersSizeLabel.setText(connectedMembersSize + "/100 Usuários online");
    }
}
