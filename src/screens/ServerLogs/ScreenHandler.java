package src.screens.ServerLogs;

import java.awt.FontFormatException;
import java.io.IOException;
import java.util.Date;

import src.components.UserConversationItem;
import src.components.UserMessageItem;
import src.entities.Client;
import src.entities.ClientMessage;
import src.interfaces.MemberEventHandler;

public class ScreenHandler {
    private ServerLogsScreen screen;

    public ScreenHandler(ServerLogsScreen screen) {
        this.screen = screen;
    }

    public MemberEventHandler joinedMember() {
        return new MemberEventHandler() {
            @Override
            public void execute(Client member) {
                try {
                    System.out.println("MEMBER JOINED " + member.getName());
                    UserConversationItem memberItem = new UserConversationItem(member);
                    ClientMessage clientMessage = new ClientMessage(member, "Se conectou", new Date());
                    
                    screen.getLogsPanel().add(new UserMessageItem(clientMessage));
                    screen.getMembersPanel().add(memberItem);
                }
                catch (Exception e) {
                    System.out.println(e);
                }
            }
        };
    }
}
