package src.screens.ServerLogs;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Dimension;
import java.awt.FontFormatException;
import java.awt.Color;

import java.io.IOException;

import src.components.Assets;
import src.infrastructure.server.ChatServer;

public class ServerLogsScreen {
    private ChatServer server;
    private JFrame frame;

    public ServerLogsScreen(ChatServer server) throws FontFormatException, IOException {
        this.server = server;
        int frameHeight = 608;
        int frameWidth = 853;

        this.frame = new JFrame("Disgrace - " + this.server.getName());
		this.frame.setLayout(new BoxLayout(this.frame.getContentPane(), BoxLayout.X_AXIS));
		frame.setIconImage(new Assets().getAppIcon());

        Color commonBorderColor = new Color(195, 207, 217);

        JPanel leftPanel = new JPanel();
        leftPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 2, commonBorderColor));
        leftPanel.setMaximumSize(new Dimension(250, frameHeight));
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setMaximumSize(new Dimension(frameWidth - 250, frameHeight));
        rightPanel.setBackground(Color.white);
        
        JScrollPane rightScrollPanel = new JScrollPane(rightPanel);
        rightScrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        rightScrollPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        rightScrollPanel.setMaximumSize(new Dimension(frameWidth - 250, frameHeight));

        frame.add(leftPanel);
        frame.add(rightScrollPanel);

		this.frame.setSize(frameWidth, frameHeight);
		this.frame.setLocationRelativeTo(null);
		this.frame.setResizable(false);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void show() {
        this.frame.setVisible(true);
    }

    public static void main(String[] args) throws IOException, FontFormatException {
        ChatServer server = new ChatServer("S.I da depressão", 7777);
        ServerLogsScreen serverLogsScreen = new ServerLogsScreen(server);
        serverLogsScreen.show();
    }
}
