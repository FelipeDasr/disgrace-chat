package src.interfaces;

import src.entities.ClientMessage;

public interface MessageEventHandler {
    public void execute(ClientMessage message);
}
