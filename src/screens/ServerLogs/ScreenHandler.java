package src.screens.ServerLogs;

import java.util.Date;

import javax.swing.Box;

import src.components.UserConversationItem;
import src.components.UserMessageItem;
import src.entities.Client;
import src.entities.ClientMessage;
import src.interfaces.MemberEventHandler;

public class ScreenHandler {
    private ServerLogsScreen frame;

    public ScreenHandler(ServerLogsScreen frame) {
        this.frame = frame;
    }

    public MemberEventHandler joinedMember() {
        return new MemberEventHandler() {
            @Override
            public void execute(Client member) {
                try {
                    UserConversationItem memberItem = new UserConversationItem(member);
                    ClientMessage clientMessage = new ClientMessage(member, 0, "Se conectou", new Date());

                    frame.getLogsPanel().add(new UserMessageItem(clientMessage)).revalidate();
                    frame.getMembersPanel().add(Box.createVerticalStrut(10)).revalidate();
                    frame.getMembersPanel().add(memberItem).revalidate();

                    frame.updateScreenTitle();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }
}
