package src.infrastructure.client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

import src.entities.Client;

public class ChatClient extends Client {
    private InetAddress serverAddress;
    private Socket socket;
    private ConnectedServer connectedPoint;

    private final ChatClientHandler handler;
    
    public ChatClient() {
        this.socket = new Socket();
        this.handler = new ChatClientHandler();
    }

    public void connectToServer(String serverHost, int serverPort) throws IOException {
        this.serverAddress = InetAddress.getByName(serverHost);
        InetSocketAddress addressToConnect = new InetSocketAddress(this.serverAddress, serverPort);
        this.socket.connect(addressToConnect);

        this.createConnectedPoint();
    }

    public void disconnectFromServer() throws IOException {
        this.socket.close();
    }

    public ConnectedServer getConnectedPoint() {
        return this.connectedPoint;
    }

    private void createConnectedPoint() throws IOException {
        this.connectedPoint = new ConnectedServer(this.socket);
        this.connectedPoint.setEventHandler(this.handler.handleEventOnReceive());
        this.connectedPoint.listenEventsInParallel();
    }
}
