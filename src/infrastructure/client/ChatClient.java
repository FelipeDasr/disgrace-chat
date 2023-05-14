package src.infrastructure.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

import src.entities.Client;

public class ChatClient extends Client {
    private InetAddress serverAddress;
    private ObjectInputStream inputChannel;
    private ObjectOutputStream outputChannel;
    private Socket socket;
    
    public ChatClient(String name, int avatarId, int channelId) {
        super(name, avatarId, channelId);
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
