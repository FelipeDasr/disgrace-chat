package src.screens.GeneralChat;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import src.components.UserConversationItem;
import src.components.UserMessageItem;
import src.entities.Client;
import src.entities.ClientMessage;
import src.infrastructure.client.ChatClient;
import src.interfaces.MemberEventHandler;
import src.interfaces.MessageEventHandler;

public class ScreenHandler {
    private GeneralChatScreen frame;
    private ChatClient client;

    public ScreenHandler(GeneralChatScreen frame, ChatClient client) {
        this.frame = frame;
        this.client = client;
    }

    public MemberEventHandler joiningServer() {
        return new MemberEventHandler() {
            @Override
            public void execute(Client member) {
                try {
                    addMemberPanel(0);
                    frame.updateScreenTitle();

                    for (Client serverMember : client.getServerMembers()) {
                        addNewMember(serverMember);
                    }

                    addSpaceBetweenMessages(0);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }

    public MemberEventHandler newConnectedMember() {
        return new MemberEventHandler() {
            @Override
            public void execute(Client member) {
                try {
                    frame.updateScreenTitle();
                    addNewMember(member);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }

    public MessageEventHandler receivedMessage() {
        return new MessageEventHandler() {
            @Override
            public void execute(ClientMessage message) {
                try {
                    JPanel messagesPanel = frame.getMessagesPanel(message.getTargetChannelId());

                    if (!lastMessageIsFromAnotherUser(message.getTargetChannelId())) {
                        addSpaceBetweenMessages(message.getTargetChannelId());
                    }

                    messagesPanel.add(new UserMessageItem(message));

                    if (frame.currentChannelId == message.getTargetChannelId()) {
                        messagesPanel.revalidate();
                        messagesPanel.repaint();
                    } else {
                        UserConversationItem memberItem = frame.getUserConversationItem(message.getTargetChannelId());
                        memberItem.setUnreadMessages(memberItem.getUnreadMessages() + 1);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }

    private void addNewMember(Client member) throws FontFormatException, IOException {
        UserConversationItem memberItem = new UserConversationItem(member);
        memberItem.addActionListener(this.changeMainPanel());

        // Check if really is a new member
        // because the channel id 0 is used to send messages in the general chat
        if (member.getChannelId() != 0) {
            int generalChatChannelId = 0;
            ClientMessage clientMessage = new ClientMessage(member, generalChatChannelId, "Se conectou", new Date());

            this.addSpaceBetweenMessages(0);
            frame.getMessagesPanel().add(new UserMessageItem(clientMessage)).revalidate();
        }

        frame.getConnectMemberPanel().add(memberItem).revalidate();
        frame.getConnectMemberPanel().add(memberItem).repaint();
        frame.getConnectMemberPanel().add(Box.createVerticalStrut(10)).revalidate();

        addMemberPanel(member.getChannelId());
    }

    public ActionListener sendMessageOnClick() {
        return new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent event) {
                try {
                    if (frame.currentChannelId < 0) {
                        return;
                    }

                    String message = frame.getInputField().getText();
                    int targetChannelId = frame.getCurrentChannelId();

                    if (!message.isEmpty()) {
                        if (lastMessageIsFromAnotherUser(frame.getCurrentChannelId())) {
                            addSpaceBetweenMessages(frame.getCurrentChannelId());
                        }

                        ClientMessage clientMessage = new ClientMessage(client, targetChannelId, message, new Date());
                        frame.getMessagesPanel(targetChannelId).add(new UserMessageItem(clientMessage)).revalidate();
                        client.sendMessage(targetChannelId, message);
                        frame.getInputField().setText("");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }

    public MemberEventHandler clientLeft() {
        return new MemberEventHandler() {
            @Override
            public void execute(Client member) {
                try {
                    frame.updateScreenTitle();

                    String message = "Se desconectou";
                    int generalChatChannelId = 0;
                    ClientMessage clientMessage = new ClientMessage(member, generalChatChannelId, message, new Date());

                    removeMemberPanel(member.getChannelId());
                    addSpaceBetweenMessages(0);

                    if (frame.getCurrentChannelId() == member.getChannelId()) {
                        goToChatPanel(0);
                    }

                    frame.getMessagesPanel().add(new UserMessageItem(clientMessage)).revalidate();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }

    private void addSpaceBetweenMessages(int channelId) {
        frame.getMessagesPanel(channelId).add(Box.createVerticalStrut(10)).revalidate();
    }

    private boolean lastMessageIsFromAnotherUser(int channelId) {
        JPanel messagesPanel = frame.getMessagesPanel(channelId);
        int messagesCount = messagesPanel.getComponentCount();

        if (messagesCount == 0) {
            return false;
        }

        Component lastComponent = messagesPanel.getComponent(messagesCount - 1);

        if (lastComponent != null) {
            if (!(lastComponent instanceof UserMessageItem)) {
                return true;
            }

            int userChannelId1 = ((UserMessageItem) lastComponent).getUserMessage().getUser().getChannelId();
            if (userChannelId1 != client.getChannelId()) {
                return true;
            }
        }

        return false;
    }

    private ActionListener changeMainPanel() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserConversationItem userConversationItem = (UserConversationItem) e.getSource();
                int channelId = userConversationItem.getChannelId();

                goToChatPanel(channelId);
            }
        };
    }

    private void removeMemberPanel(int channelId) {
        // Remove member from connected members list (GUI)
        Component emptySpace = null;
        for (Component component : frame.getConnectMemberPanel().getComponents()) {
            if (component instanceof UserConversationItem) {
                UserConversationItem memberItem = (UserConversationItem) component;

                if (memberItem.getChannelId() == channelId) {
                    frame.getConnectMemberPanel().remove(emptySpace);
                    frame.getConnectMemberPanel().remove(memberItem);
                    frame.getConnectMemberPanel().revalidate();
                    frame.getConnectMemberPanel().repaint();
                    break;
                }
            } else {
                emptySpace = component;
            }
        }
    }

    public void goToChatPanel(int memberChannelId) {
        UserConversationItem oldUserConversationItem = frame.getUserConversationItem(frame.getCurrentChannelId());
        if (oldUserConversationItem != null) {
            oldUserConversationItem.setIsFocused(false);
        }

        frame.setCurrentChannelId(memberChannelId);
        UserConversationItem currentUserConversationItem = frame.getUserConversationItem(memberChannelId);
        currentUserConversationItem.setUnreadMessages(0);
        currentUserConversationItem.setIsFocused(true);

        Component component = this.frame.getFrame().getComponent(0);
        JScrollPane memberPanel = this.frame.getMembersPanelHashMap().get(memberChannelId);

        if (component instanceof JScrollPane) {
            this.frame.getFrame().remove(0);
        }

        this.frame.getFrame().add(memberPanel, 0);
        this.frame.getFrame().revalidate();
        this.frame.getFrame().repaint();
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

        this.frame.getMembersPanelHashMap().put(channelId, messagesScrollPane);
    }
}