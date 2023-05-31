package src.screens.ServerLogs;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Dimension;
import java.awt.FontFormatException;
import java.awt.Color;

import java.io.IOException;
import java.util.Date;

import src.components.Assets;
import src.components.ServerLogsHeader;
import src.components.UserMessageItem;
import src.entities.Client;
import src.entities.ClientMessage;
import src.infrastructure.server.ChatServer;

public class ServerLogsScreen {
    private ChatServer server;
    private JFrame frame;
    private Client serverClient;
    private JPanel rightPanel;
    private JPanel leftPanel;
    private ScreenHandler handler;

    public ServerLogsScreen(ChatServer server) throws FontFormatException, IOException {
        this.server = server;
        this.handler = new ScreenHandler(this);

        int frameHeight = 608;
        int frameWidth = 853;

        this.serverClient = new Client(server.getName(), 0, 0);

        this.frame = new JFrame("Disgrace - " + this.server.getName());
		this.frame.setLayout(new BoxLayout(this.frame.getContentPane(), BoxLayout.X_AXIS));
		frame.setIconImage(new Assets().getAppIcon());

        Color commonBorderColor = new Color(195, 207, 217);

        this.leftPanel = new JPanel();
        leftPanel.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 2, commonBorderColor));
        leftPanel.setMaximumSize(new Dimension(250, frameHeight));
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBackground(Color.white);

        ServerLogsHeader leftPanelHeader = new ServerLogsHeader(this.server.getName(), this.server.getConnectedClients().size());
        leftPanel.add(leftPanelHeader);

        this.rightPanel = new JPanel();
        rightPanel.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, commonBorderColor));
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setMaximumSize(new Dimension(frameWidth - 250, frameHeight));
        rightPanel.setBackground(Color.white);
        
        JScrollPane rightScrollPanel = new JScrollPane(rightPanel);
        rightScrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        rightScrollPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        rightScrollPanel.setMaximumSize(new Dimension(frameWidth - 250, frameHeight));

        JScrollPane leftScrollPanel = new JScrollPane(leftPanel);
        leftScrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        leftScrollPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        leftScrollPanel.setMaximumSize(new Dimension(250, frameHeight));

        frame.add(leftScrollPanel);
        frame.add(rightScrollPanel);

		this.frame.setSize(frameWidth, frameHeight);
		this.frame.setLocationRelativeTo(null);
		this.frame.setResizable(false);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.server.setEventActionOnMemberJoin(this.handler.joinedMember());

        this.showInitialLogs();
    }

    private void showInitialLogs() throws FontFormatException, IOException {
        ClientMessage messageLog1 = new ClientMessage(this.serverClient, "Servidor iniciado com sucesso!", new Date());
        ClientMessage messageLog2 = new ClientMessage(this.serverClient, "Aguardando conexões", new Date());
        ClientMessage messageLog3 = new ClientMessage(this.serverClient, "Rodando no endereço: " + this.server.getHostAddress(), new Date());
        ClientMessage messageLog4 = new ClientMessage(this.serverClient, "Rodando na porta: " + this.server.getPort(), new Date());

        this.rightPanel.add(new UserMessageItem(messageLog1));
        this.rightPanel.add(new UserMessageItem(messageLog2));
        this.rightPanel.add(Box.createVerticalStrut(15));
        this.rightPanel.add(new UserMessageItem(messageLog3));
        this.rightPanel.add(new UserMessageItem(messageLog4));
        this.rightPanel.add(Box.createVerticalStrut(15));
    }

    public JPanel getLogsPanel() {
        return this.rightPanel;
    }

    public JPanel getMembersPanel() {
        return this.leftPanel;
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
