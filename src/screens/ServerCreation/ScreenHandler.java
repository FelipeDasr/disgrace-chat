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
                String serverName = frame.getFirstFormInputTextField().getText().trim();

                if (serverName.length() < 5) {
                    frame.setErrorMessage("O nome do servidor não pode ter menos de 5 caracteres");
                    return;
                }

                String serverPortString = frame.getSecondFormInputTextField().getText().trim();

                if (serverPortString.length() < 4) {
                    frame.setErrorMessage("A porta do servidor não pode ter menos de 4 algarismos");
                    return;
                }

                try {
                    ChatServer server = new ChatServer(serverName, Integer.parseInt(serverPortString));
                    server.bind();
                    server.listenConnectionsInParallel();
                } catch (IOException e) {
                    System.out.println("Error while creating server");
                }
            }
        };
    };
}
