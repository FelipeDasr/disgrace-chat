package src.infrastructure.server;

import java.io.IOException;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Vector;

import src.interfaces.MemberEventHandler;

public class ChatServer {
    private ServerSocket socket;
    private InetAddress serverAddress;
    private ServerHandler handler;
    private String name;
    private int port;
    private int nextChannelId = 1;

    private final Vector<ConnectedClient> connectedClients;

    public ChatServer(String name, int port) throws IOException {
        this.serverAddress = InetAddress.getByName("localhost");
        this.socket = new ServerSocket();
        this.name = name;
        this.port = port;

        this.connectedClients = new Vector<ConnectedClient>();
        this.handler = new ServerHandler(this);
    }

    public String getName() {
        return this.name;
    }

    public ConnectedClient getClientByChannelId(int channelId) {
        for (ConnectedClient client : this.connectedClients) {
            if (client.getChannelId() == channelId) {
                return client;
            }
        }

        return null;
    }

    public Vector<ConnectedClient> getConnectedClients() {
        return this.connectedClients;
    }

    public void removeClient(ConnectedClient client) {
        this.connectedClients.remove(client);
    }

    public void bind() throws IOException {
        InetSocketAddress addressToBind = new InetSocketAddress(this.serverAddress, this.port);
        this.socket.bind(addressToBind);
    }

    public void close() throws IOException {
        this.socket.close();
    }

    private void listenConnections() throws IOException {
        while (true) {
            Socket clientSocket = this.socket.accept();
            int newClientChannelId = nextChannelId++;

            ConnectedClient connectedClient = new ConnectedClient(newClientChannelId, clientSocket);
            this.connectedClients.add(connectedClient);

            connectedClient.setEventHandler(this.handler.handleEventOnReceive());
            connectedClient.setDisconnectedClientEventHandler(this.handler.handleDisconnectedClientEvent());
            connectedClient.listenEventsInParallel();
        }
    }

    public void listenConnectionsInParallel() {
        Runnable runnableFunction = new Runnable() {
            @Override
            public void run() {
                try {
                    listenConnections();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread thread = new Thread(runnableFunction);
        thread.start();
    }

    public void setEventActionOnMemberJoin(MemberEventHandler eventHandler) {
        this.handler.setEventActionOnMemberJoin(eventHandler);
    }

    public void setEventActionOnMemberLeave(MemberEventHandler eventHandler) {
        this.handler.setEventActionOnMemberLeave(eventHandler);
    }

    public int getPort() {
        return this.port;
    }

    public String getHostAddress() throws UnknownHostException {
        return InetAddress.getLocalHost().getHostAddress();
    }

    public static void main(String[] args) throws IOException {
        ChatServer server = new ChatServer("Chat Server", 8080);
        server.bind();
        server.listenConnections();
    }
}
