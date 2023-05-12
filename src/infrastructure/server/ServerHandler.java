package src.infrastructure.server;

import org.json.JSONObject;

import src.interfaces.InputEventHandler;

public class ServerHandler {
    private final ChatServer server;

    public ServerHandler(ChatServer server) {
        this.server = server;
    }

    public InputEventHandler handleEventOnReceive() {
        return new InputEventHandler() {
            @Override
            public void execute(String event, JSONObject data) {
                System.out.println("Event received: " + event + "\nBody: " + data.toString(4));
            }
        };
    }
}
