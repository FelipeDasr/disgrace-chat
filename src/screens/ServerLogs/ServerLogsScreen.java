package src.screens.ServerLogs;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.io.IOException;
import java.awt.Color;

import src.components.Assets;
import src.infrastructure.server.ChatServer;

public class ServerLogsScreen {
    private ChatServer server;
    private JFrame frame;

    public ServerLogsScreen(ChatServer server) {
        this.server = server;

        this.frame = new JFrame("Disgrace - " + this.server.getName());
		this.frame.setLayout(new BoxLayout(this.frame.getContentPane(), BoxLayout.X_AXIS));
		frame.setIconImage(new Assets().getAppIcon());

		JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 255, 255));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setMaximumSize(new Dimension(743, 558));

        this.frame.add(panel);
		this.frame.setSize(743, 558);
		this.frame.setLocationRelativeTo(null);
		this.frame.setResizable(false);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void show() {
        this.frame.setVisible(true);
    }

    public static void main(String[] args) throws IOException {
        ChatServer server = new ChatServer("S.I da depressão", 7777);
        ServerLogsScreen serverLogsScreen = new ServerLogsScreen(server);
        serverLogsScreen.show();
    }
}
