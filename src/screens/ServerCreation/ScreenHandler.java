package src.screens.ServerCreation;

import java.awt.event.ActionListener;
import java.io.IOException;

import src.infrastructure.server.ChatServer;

// private class (only to be used by ServerCreationScreen.java)
class ScreenHandler {
    private final ServerCreationScreen frame;

    public ScreenHandler(ServerCreationScreen frame) {
        this.frame = frame;
    }

    public ActionListener confirmServerCreationOnClick() {
        return new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent event) {
                String serverName = frame.getFirstFormInputTextField().getText();
                int serverPort = Integer.parseInt(frame.getSecondFormInputTextField().getText());

                try {
                    ChatServer server = new ChatServer(serverName, serverPort);
                    server.bind();
                    server.listenConnectionsInParallel();
                } catch (IOException e) {
                    System.out.println("Error while creating server");
                }
            }
        };
    };
}
