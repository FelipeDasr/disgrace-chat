package src.interfaces;

import java.io.IOException;

import org.json.JSONObject;

public interface ConnectedPoint<GenericPoint> {
    public void setEventHandler(InputEventHandler<GenericPoint> inputEventHandler);
    public void emitEvent(String event, JSONObject data) throws IOException;
    public void listenEventsInParallel();
    public void closeConnection() throws IOException;
}
