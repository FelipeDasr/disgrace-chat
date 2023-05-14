package src.interfaces;

import org.json.JSONObject;

import src.infrastructure.server.ConnectedClient;

public interface InputEventHandler {
    void execute(ConnectedClient connectedClient, String event, JSONObject data);
}
