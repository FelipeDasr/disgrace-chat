package src.infrastructure.client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Vector;

import src.entities.Client;
import src.interfaces.MemberEventHandler;
import src.interfaces.MessageEventHandler;

public class ChatClient extends Client {
    private InetAddress serverAddress;
    private Socket socket;
    private ConnectedServer connectedPoint;
    private String serverName;

    private Vector<Client> serverMembers;

    private final ClientHandler handler;

    public ChatClient() {
        this.socket = new Socket();
        this.handler = new ClientHandler(this);
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

    public Client getClientByChannelId(int channelId) {
        for (Client client : this.serverMembers) {
            if (client.getChannelId() == channelId) {
                return client;
            }
        }

        return null;
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

    public void sendMessage(int targetChannelId, String message) throws IOException {
        this.connectedPoint.sendMessageToServer(targetChannelId, message);
    }

    private void createConnectedPoint() throws IOException {
        this.connectedPoint = new ConnectedServer(this.socket);
        this.connectedPoint.setEventHandler(this.handler.handleEventOnReceive());
        this.connectedPoint.listenEventsInParallel();
    }

    public void setEventActionOnNewMemberJoin(MemberEventHandler eventHandler) {
        this.handler.setEventActionOnNewMemberJoin(eventHandler);
    }

    public void setEventActionOnJoiningServer(MemberEventHandler eventHandler) {
        this.handler.setEventActionOnJoiningServer(eventHandler);
    }

    public void setEventActionOnReceiveMessage(MessageEventHandler eventHandler) {
        this.handler.setEventActionOnReceiveMessage(eventHandler);
    }

    public void setEventActionOnMemberLeft(MemberEventHandler eventHandler) {
        this.handler.setEventActionOnMemberseLeft(eventHandler);
    }
}
