package src.infrastructure.server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;

import java.net.Socket;

import org.json.JSONObject;

import src.entities.Client;
import src.interfaces.ConnectedPoint;
import src.interfaces.InputEventHandler;
import src.interfaces.MemberEventHandler;

public class ConnectedClient extends Client implements ConnectedPoint<ConnectedClient> {
    private final ObjectOutputStream outputChannel;
    private final ObjectInputStream inputChannel;
    private final Socket socket;
    private boolean isIdentified;

    private InputEventHandler<ConnectedClient> inputEventHandler;
    private MemberEventHandler onDisconnectEventHandler;

    public ConnectedClient(int channelId, Socket socket) throws IOException {
        super(channelId);
        this.socket = socket;
        this.outputChannel = new ObjectOutputStream(this.socket.getOutputStream());
        this.inputChannel = new ObjectInputStream(this.socket.getInputStream());
        this.inputEventHandler = null;
        this.isIdentified = false;
    }

    public void setEventHandler(InputEventHandler<ConnectedClient> inputEventHandler) {
        this.inputEventHandler = inputEventHandler;
    }

    public void setDisconnectedClientEventHandler(MemberEventHandler onDisconnectEventHandler) {
        this.onDisconnectEventHandler = onDisconnectEventHandler;
    }

    public void emitEvent(String event, JSONObject data) throws IOException {
        JSONObject eventObject = new JSONObject();
        eventObject.put("event", event);
        eventObject.put("data", data);

        this.outputChannel.writeObject(eventObject.toString());
    }

    private void listenEvents() throws IOException, ClassNotFoundException {
        while (this.socket.isConnected()) {
            String dataString = (String) this.inputChannel.readObject();
            JSONObject eventObject = new JSONObject(dataString);
            String event = eventObject.getString("event");
            JSONObject data = eventObject.getJSONObject("data");

            this.inputEventHandler.execute(this, event, data);
        }
    }

    public void listenEventsInParallel() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    listenEvents();
                } catch (IOException | ClassNotFoundException error) {
                    closeConnection();
                }
            }
        };

        Thread parallelThread = new Thread(runnable);
        parallelThread.start();
    }

    public void closeConnection() {
        try {
            this.socket.close();
            this.inputChannel.close();
            this.outputChannel.close();

            this.onDisconnectEventHandler.execute(this);
        } catch (Exception error) {
            System.out.println("Erro ao desconectar cliente");
        }
    }

    public boolean isIdentified() {
        return this.isIdentified;
    }

    public void setAsIdentified() {
        this.isIdentified = true;
    }
}
