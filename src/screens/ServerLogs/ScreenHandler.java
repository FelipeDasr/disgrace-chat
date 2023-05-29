package src.screens.ServerLogs;

import java.awt.FontFormatException;
import java.io.IOException;
import java.util.Date;

import src.components.UserConversationItem;
import src.components.UserMessageItem;
import src.entities.Client;
import src.entities.ClientMessage;

public class ScreenHandler {
    private ServerLogsScreen screen;

    public ScreenHandler(ServerLogsScreen screen) {
        this.screen = screen;
    }

    public void joinedMember(Client member) throws FontFormatException, IOException {
        UserConversationItem memberItem = new UserConversationItem(member);
        ClientMessage clientMessage = new ClientMessage(member, "Se conectou", new Date());

        this.screen.getLogsPanel().add(new UserMessageItem(clientMessage), 0);
        this.screen.getMembersPanel().add(memberItem);
    }
}
