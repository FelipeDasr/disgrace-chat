package src.screens.ServerLogs;

import java.util.Date;

import javax.swing.Box;

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
                    UserConversationItem memberItem = new UserConversationItem(member);
                    ClientMessage clientMessage = new ClientMessage(member, "Se conectou", new Date());

                    screen.getLogsPanel().add(new UserMessageItem(clientMessage)).revalidate();
                    screen.getMembersPanel().add(Box.createVerticalStrut(10)).revalidate();
                    screen.getMembersPanel().add(memberItem).revalidate();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        };
    }
}
