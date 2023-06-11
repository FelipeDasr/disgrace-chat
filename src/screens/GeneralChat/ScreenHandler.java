package src.screens.GeneralChat;

import java.awt.Component;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Date;

import javax.swing.Box;
import javax.swing.JPanel;

import src.components.UserConversationItem;
import src.components.UserMessageItem;
import src.entities.Client;
import src.entities.ClientMessage;
import src.infrastructure.client.ChatClient;
import src.interfaces.MemberEventHandler;
import src.interfaces.MessageEventHandler;

public class ScreenHandler {
    private GeneralChatScreen screen;
    private ChatClient client;

    public ScreenHandler(GeneralChatScreen screen, ChatClient client) {
        this.screen = screen;
        this.client = client;
    }

    public MemberEventHandler joiningServer() {
        return new MemberEventHandler() {
            @Override
            public void execute(Client member) {
                try {
                    screen.updateScreenTitle();
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
                    screen.updateScreenTitle();
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
                    JPanel messagesPanel = screen.getMessagesPanel(message.getTargetChannelId());

                    if (!lastMessageIsFromAnotherUser(message.getTargetChannelId())) {
                        addSpaceBetweenMessages(message.getTargetChannelId());
                    }

                    messagesPanel.add(new UserMessageItem(message)).revalidate();
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
            screen.getMessagesPanel().add(new UserMessageItem(clientMessage)).revalidate();
        }

        screen.getConnectMemberPanel().add(memberItem).revalidate();
        screen.getConnectMemberPanel().add(memberItem).repaint();
        screen.getConnectMemberPanel().add(Box.createVerticalStrut(10)).revalidate();

        screen.addMemberPanel(member.getChannelId());
    }

    public ActionListener sendMessageOnClick() {
        return new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent event) {
                try {
                    String message = screen.getInputField().getText();
                    int targetChannelId = screen.getCurrentChannelId();

                    if (!message.isEmpty()) {
                        if (lastMessageIsFromAnotherUser(screen.getCurrentChannelId())) {
                            addSpaceBetweenMessages(screen.getCurrentChannelId());
                        }

                        ClientMessage clientMessage = new ClientMessage(client, targetChannelId, message, new Date());
                        screen.getMessagesPanel(targetChannelId).add(new UserMessageItem(clientMessage)).revalidate();
                        client.sendMessage(targetChannelId, message);
                        screen.getInputField().setText("");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }

    private void addSpaceBetweenMessages(int channelId) {
        screen.getMessagesPanel(channelId).add(Box.createVerticalStrut(10)).revalidate();
    }

    private boolean lastMessageIsFromAnotherUser(int channelId) {
        JPanel messagesPanel = screen.getMessagesPanel(channelId);
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
                screen.setMainPanel(channelId);
                screen.setMainPanel(channelId);

                screen.setMainPanel(channelId);
            }
        };
    }
}