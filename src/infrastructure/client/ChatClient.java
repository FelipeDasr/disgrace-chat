package src.infrastructure.client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

import src.entities.User;

public class ChatClient extends User {
    private InetAddress serverAddress;
    private Socket socket;
    
    public ChatClient(String name, int avatarId) {
        super(name, avatarId);
        this.socket = new Socket();
    }

    public void connectToServer(String serverHost, int serverPort) throws IOException {
        this.serverAddress = InetAddress.getByName(serverHost);
        InetSocketAddress addressToConnect = new InetSocketAddress(this.serverAddress, serverPort);
        this.socket.connect(addressToConnect);
    }

    public void disconnectFromServer() throws IOException {
        this.socket.close();
    }
}
