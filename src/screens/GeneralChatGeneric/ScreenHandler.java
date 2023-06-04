package src.screens.GeneralChatGeneric;

import java.util.Date;
import src.components.UserConversationItem;
import src.components.UserMessageItem;
import src.entities.Client;
import src.entities.ClientMessage;
import src.interfaces.MemberEventHandler;

public class ScreenHandler {
    private GeneralChatScreen screen;

    public ScreenHandler(GeneralChatScreen screen) {
        this.screen = screen;
    }

    public MemberEventHandler joinedMember() {
        return new MemberEventHandler() {
            @Override
            public void execute(Client member) {
                try {
                    UserConversationItem memberItem = new UserConversationItem(member);
                    ClientMessage clientMessage = new ClientMessage(member, "Se conectou", new Date());

                    screen.getMessagesPanel().add(new UserMessageItem(clientMessage)).revalidate();
                    screen.getConnectMemberPanel().add(memberItem).revalidate();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        };
    }
}