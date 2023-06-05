package src.screens.GeneralChat;

import java.awt.FontFormatException;
import java.io.IOException;
import java.util.Date;

import javax.swing.Box;

import src.components.UserConversationItem;
import src.components.UserMessageItem;
import src.entities.Client;
import src.entities.ClientMessage;
import src.infrastructure.client.ChatClient;
import src.interfaces.MemberEventHandler;

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

    private void addNewMember(Client member) throws FontFormatException, IOException {
        UserConversationItem memberItem = new UserConversationItem(member);

        // Check if really is a new member
        // because the channel id 0 is used to send messages in the general chat
        if (client.getChannelId() != 0) {
            ClientMessage clientMessage = new ClientMessage(member, "Se conectou", new Date());
            screen.getMessagesPanel().add(new UserMessageItem(clientMessage)).revalidate();
        }

        screen.getConnectMemberPanel().add(Box.createVerticalStrut(10)).revalidate();
        screen.getConnectMemberPanel().add(memberItem).revalidate();
    }
}