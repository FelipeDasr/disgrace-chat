package src.interfaces;

import src.entities.Client;

public interface MemberEventHandler {
    public void execute(Client event);
}
