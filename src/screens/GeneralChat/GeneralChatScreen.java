package src.screens.GeneralChat;

import src.components.Assets;
import src.components.GenericButton;
import src.components.UserConversationItem;
import src.infrastructure.client.ChatClient;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Insets;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class GeneralChatScreen {
    private JFrame frame;
    private JPanel leftPanel;
    private JTextArea inputField;
    private ChatClient client;
    private ScreenHandler handler;
    private HashMap<Integer, JScrollPane> membersPanel;

    // Selected chat, starts with -1 (no chat selected)
    int currentChannelId = -1;

    public GeneralChatScreen(ChatClient client) throws FontFormatException, IOException {
        this.client = client;
        this.handler = new ScreenHandler(this, client);

        this.membersPanel = new HashMap<Integer, JScrollPane>();

        int frameHeight = 608;
        int frameWidth = 853;

        Color commonBorderColor = new Color(195, 207, 217);

        this.frame = new JFrame();
        this.frame.setIconImage(new Assets().getAppIcon());

        this.leftPanel = new JPanel();
        this.leftPanel.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 2, commonBorderColor));
        this.leftPanel.setMaximumSize(new Dimension(250, frameHeight));
        this.leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        this.leftPanel.setBackground(Color.white);

        JScrollPane onlineUsersScrollPane = new JScrollPane(leftPanel);
        onlineUsersScrollPane.setPreferredSize(new Dimension(250, frameHeight));
        onlineUsersScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        onlineUsersScrollPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

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

        sendButton.addActionListener(this.handler.sendMessageOnClick());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.X_AXIS));
        inputPanel.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, commonBorderColor));

        inputPanel.setPreferredSize(new Dimension(frameHeight, 45));
        inputPanel.setBackground(Color.white);

        inputPanel.add(scrolInputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);

        this.frame.setLayout(new BorderLayout());
        this.frame.add(onlineUsersScrollPane, BorderLayout.WEST);
        this.frame.add(inputPanel, BorderLayout.SOUTH);

        this.frame.setSize(frameWidth, frameHeight);
        this.frame.setLocationRelativeTo(null);
        this.frame.setResizable(false);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.client.setEventActionOnNewMemberJoin(this.handler.newConnectedMember());
        this.client.setEventActionOnReceiveMessage(this.handler.receivedMessage());
        this.client.setEventActionOnJoiningServer(this.handler.joiningServer());
    }

    public void setCurrentChannelId(int channelId) {
        this.currentChannelId = channelId;
    }

    public int getCurrentChannelId() {
        return this.currentChannelId;
    }

    public void updateScreenTitle() {
        int connectedMembers = this.client.getServerMembers().size();
        this.frame.setTitle("Disgrace - " + this.client.getServerName() + " (" + connectedMembers + " membros)");
    }

    public JPanel getMessagesPanel(int channelId) {
        return (JPanel) this.membersPanel.get(channelId).getViewport().getView();
    }

    public JPanel getMessagesPanel() {
        return (JPanel) this.membersPanel.get(0).getViewport().getView();
    }

    public JTextArea getInputField() {
        return this.inputField;
    }

    public JPanel getConnectMemberPanel() {
        return this.leftPanel;
    }

    public UserConversationItem getUserConversationItem(int channelId) {
        Component[] components = this.leftPanel.getComponents();

        for (Component component : components) {
            if (component instanceof UserConversationItem) {
                UserConversationItem userConversationItem = (UserConversationItem) component;
                if (userConversationItem.getChannelId() == channelId) {
                    return userConversationItem;
                }
            }
        }

        return null;
    }

    public void show() {
        this.frame.setVisible(true);
    }

    public void setMainPanel(int memberChannelId) {
        UserConversationItem oldUserConversationItem = this.getUserConversationItem(this.currentChannelId);
        if (oldUserConversationItem != null) {
            oldUserConversationItem.setIsFocused(false);
        }

        this.setCurrentChannelId(memberChannelId);
        UserConversationItem currentUserConversationItem = this.getUserConversationItem(memberChannelId);
        currentUserConversationItem.setUnreadMessages(0);
        currentUserConversationItem.setIsFocused(true);

        Component component = this.frame.getComponent(0);
        JScrollPane memberPanel = this.membersPanel.get(memberChannelId);

        if (component instanceof JScrollPane) {
            this.frame.remove(0);
        }

        this.frame.add(memberPanel, 0);
        this.frame.revalidate();
        this.frame.repaint();
    }

    public void addMemberPanel(int channelId) {
        Color commonBorderColor = new Color(195, 207, 217);

        JPanel memberPanel = new JPanel();
        memberPanel.setLayout(new BoxLayout(memberPanel, BoxLayout.Y_AXIS));
        memberPanel.setBackground(Color.white);
        memberPanel.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, commonBorderColor));

        JScrollPane messagesScrollPane = new JScrollPane(memberPanel);
        messagesScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        messagesScrollPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        messagesScrollPane.setMaximumSize(new Dimension(250, 608));

        this.membersPanel.put(channelId, messagesScrollPane);
    }
}
