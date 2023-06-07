package src.screens.GeneralChat;

import java.awt.FontFormatException;
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

                    addSpaceBetweenMessages();
                } catch (Exception e) {
                    System.out.println(e);
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
                    System.out.println(e);
                }
            }
        };
    }

    public MessageEventHandler receivedMessage() {
        return new MessageEventHandler() {
            @Override
            public void execute(ClientMessage message) {
                try {
                    JPanel messagesPanel = screen.getMessagesPanel();
                    int indexOfLastMessage = messagesPanel.getComponentCount() - 1;

                    UserMessageItem lastMessage = (UserMessageItem) messagesPanel.getComponent(indexOfLastMessage);

                    if (lastMessage != null) {
                        int userChannelId1 = lastMessage.getUserMessage().getUser().getChannelId();
                        int userChannelId2 = message.getUser().getChannelId();

                        if (userChannelId1 != userChannelId2) {
                            addSpaceBetweenMessages();
                        }
                    }

                    messagesPanel.add(new UserMessageItem(message)).revalidate();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        };
    }

    private void addNewMember(Client member) throws FontFormatException, IOException {
        UserConversationItem memberItem = new UserConversationItem(member);

        // Check if really is a new member
        // because the channel id 0 is used to send messages in the general chat
        if (member.getChannelId() != 0) {
            int generalChatChannelId = 0;
            ClientMessage clientMessage = new ClientMessage(member, generalChatChannelId, "Se conectou", new Date());
            screen.getMessagesPanel().add(new UserMessageItem(clientMessage)).revalidate();
        }

        screen.getConnectMemberPanel().add(memberItem).revalidate();
    }

    public ActionListener sendMessageOnClick() {
        return new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent event) {
                try {
                    String message = screen.getInputField().getText();
                    int targetChannelId = screen.getCurrentChannelId();

                    if (!message.isEmpty()) {
                        ClientMessage clientMessage = new ClientMessage(client, targetChannelId, message, new Date());
                        screen.getMessagesPanel().add(new UserMessageItem(clientMessage)).revalidate();
                        client.sendMessage(targetChannelId, message);
                        screen.getInputField().setText("");
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        };
    }

    private void addSpaceBetweenMessages() {
        screen.getMessagesPanel().add(Box.createVerticalStrut(10)).revalidate();
    }
}