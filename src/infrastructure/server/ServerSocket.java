package src.infrastructure.server;

public class ServerSocket {
    private ServerSocket socket;
    private String name;
    private int port;

    public void ServerSocker(String name, int port) {
        this.socket = new ServerSocket();
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
