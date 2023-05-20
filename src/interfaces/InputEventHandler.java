package src.interfaces;

import java.io.IOException;

import org.json.JSONObject;

public interface InputEventHandler<GenerictType> {
    void execute(GenerictType connectedPoint, String event, JSONObject data) throws IOException;
}
