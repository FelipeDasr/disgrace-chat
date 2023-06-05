package src.screens.GeneralChat;

import src.components.Assets;
import src.components.GenericButton;
import src.infrastructure.client.ChatClient;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Insets;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class GeneralChatScreen {
    private JFrame frame;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JPanel messagesPanel;
    private JTextArea inputField;
    private ChatClient client;
    private ScreenHandler handler;

    public GeneralChatScreen(ChatClient client) throws FontFormatException, IOException {
        this.client = client;
        this.handler = new ScreenHandler(this, client);

        int frameHeight = 608;
        int frameWidth = 853;

        Color commonBorderColor = new Color(195, 207, 217);

        this.messagesPanel = new JPanel();
        this.messagesPanel.setLayout(new BoxLayout(messagesPanel, BoxLayout.Y_AXIS));
        this.messagesPanel.setBackground(Color.white);
        this.messagesPanel.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, commonBorderColor));

        this.frame = new JFrame("Disgrace");
        this.frame.setIconImage(new Assets().getAppIcon());

        this.leftPanel = new JPanel();
        this.leftPanel.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 2, commonBorderColor));
        this.leftPanel.setMaximumSize(new Dimension(250, frameHeight));
        this.leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        this.leftPanel.setBackground(Color.white);

        this.rightPanel = new JPanel();
        this.rightPanel.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, commonBorderColor));
        this.rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        this.rightPanel.setMaximumSize(new Dimension(frameWidth, frameHeight));
        this.rightPanel.setBackground(Color.white);

        JScrollPane onlineUsersScrollPane = new JScrollPane(leftPanel);
        onlineUsersScrollPane.setPreferredSize(new Dimension(250, frameHeight));
        onlineUsersScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        onlineUsersScrollPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        JScrollPane messagesScrollPane = new JScrollPane(messagesPanel);
        messagesScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        messagesScrollPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        messagesScrollPane.setMaximumSize(new Dimension(250, frameHeight));

        this.inputField = new JTextArea();
        this.inputField.setMaximumSize(new Dimension(frameHeight, 45));
        this.inputField.setAutoscrolls(true);
        this.inputField.setLineWrap(true);
        this.inputField.setFont(new Assets().getMainFont().deriveFont(Font.BOLD, 19));

        JScrollPane scrolInputField = new JScrollPane(inputField);
        scrolInputField.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrolInputField.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        scrolInputField.setMaximumSize(new Dimension(frameWidth, 45));

        GenericButton sendButton = new GenericButton("Enviar");
        sendButton.setMargin(new Insets(2, 6, 2, 6));
        sendButton.setMaximumSize(new Dimension(70, 45));

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.X_AXIS));
        inputPanel.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, commonBorderColor));

        inputPanel.setPreferredSize(new Dimension(frameHeight, 45));
        inputPanel.setBackground(Color.white);

        inputPanel.add(scrolInputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);

        this.frame.setLayout(new BorderLayout());
        this.frame.add(onlineUsersScrollPane, BorderLayout.WEST);
        this.frame.add(messagesScrollPane);
        this.frame.add(inputPanel, BorderLayout.SOUTH);

        this.frame.setSize(frameWidth, frameHeight);
        this.frame.setLocationRelativeTo(null);
        this.frame.setResizable(false);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.client.setEventActionOnNewMemberJoin(this.handler.newConnectedMember());
        this.client.setEventActionOnJoiningServer(this.handler.joiningServer());
    }

    public JPanel getMessagesPanel() {
        return this.messagesPanel;
    }

    public JTextArea getInputField() {
        return this.inputField;
    }

    public JPanel getConnectMemberPanel() {
        return this.leftPanel;
    }

    public void show() {
        this.frame.setVisible(true);
    }
}
