package src.interfaces;

import java.io.IOException;

import org.json.JSONObject;

public interface InputEventListener {
    public void setEventHandler(InputEventHandler inputEventHandler);
    public void emitEvent(String event, JSONObject data) throws IOException;
    public void listenEventsInParallel();
}
