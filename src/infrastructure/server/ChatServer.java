package src.infrastructure.server;

public class ChatServer {
    private ChatServer socket;
    private String name;
    private int port;

    public void ServerSocker(String name, int port) {
        this.socket = new ChatServer();
        this.name = name;
        this.port = port;
    }

    public void start() {

    }

    public void close() {

    }

    public void listen() {
        
    }
}
