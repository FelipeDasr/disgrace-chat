package src.infrastructure.client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Vector;

import src.entities.Client;

public class ChatClient extends Client {
    private InetAddress serverAddress;
    private Socket socket;
    private ConnectedServer connectedPoint;
    private String serverName;

    private Vector<Client> serverMembers;

    private final ChatClientHandler handler;

    public ChatClient() {
        this.socket = new Socket();
        this.handler = new ChatClientHandler(this);
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

    public String getServerName() {
        return this.serverName;
    }

    public void setServerName(String name) {
        this.serverName = name;
    }

    public void setServerMembers(Vector<Client> serverMembers) {
        this.serverMembers = serverMembers;
    }

    public Vector<Client> getServerMembers() {
        return this.serverMembers;
    }

    private void createConnectedPoint() throws IOException {
        this.connectedPoint = new ConnectedServer(this.socket);
        this.connectedPoint.setEventHandler(this.handler.handleEventOnReceive());
        this.connectedPoint.listenEventsInParallel();
    }
}
