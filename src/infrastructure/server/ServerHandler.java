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
                switch (event) {
                    case "join":
                        joinEvent(data);
                        break;

                    default:
                        break;
                }
            }
        };
    }

    private void emitEventToClient(int channelId, JSONObject eventObject) {

    }

    private void joinEvent(JSONObject data) {

    }
}
