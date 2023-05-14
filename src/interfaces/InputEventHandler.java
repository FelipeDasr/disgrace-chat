package src.interfaces;

import java.io.IOException;

import org.json.JSONObject;

import src.infrastructure.server.ConnectedClient;

public interface InputEventHandler {
    void execute(ConnectedClient connectedClient, String event, JSONObject data) throws IOException;
}
