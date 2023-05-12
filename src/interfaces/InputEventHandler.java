package src.interfaces;

import org.json.JSONObject;

public interface InputEventHandler {
    void execute(String event, JSONObject data);
}
