package src.infrastructure.server;

import java.io.IOException;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.util.ArrayList;

public class ChatServer {
    private ServerSocket socket;
    private InetAddress serverAddress; 
    private String name;
    private int port;

    private final ArrayList<ConnectedClient> connectedClients;

    public ChatServer(String name, int port) throws IOException {
        this.serverAddress = InetAddress.getByName("localhost");
        this.socket = new ServerSocket();
        this.name = name;
        this.port = port;

        this.connectedClients = new ArrayList<ConnectedClient>();
    }

    public String getName() {
        return this.name;
    }

    public void bind() throws IOException {
        InetSocketAddress addressToBind = new InetSocketAddress(this.serverAddress, this.port);
        this.socket.bind(addressToBind);
    }

    public void close() throws IOException {
        this.socket.close();
    }
}
