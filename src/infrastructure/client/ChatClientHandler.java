package src.infrastructure.client;

import java.io.IOException;

import org.json.JSONObject;

import src.interfaces.InputEventHandler;
import src.interfaces.PointHandler;

public class ChatClientHandler implements PointHandler<ConnectedServer>{

    public InputEventHandler<ConnectedServer> handleEventOnReceive() {
        return new InputEventHandler<ConnectedServer>() {
            @Override
            public void execute(ConnectedServer connectedClient, String event, JSONObject data) throws IOException {
                switch (event) {
                    case "joined":
                        // System.out.println(data.toString(4));
                        break;
                }
            }
        };
    }
}
