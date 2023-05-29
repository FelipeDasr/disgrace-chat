package src.screens.ServerCreation;

import java.awt.FontFormatException;
import java.awt.event.ActionListener;
import java.io.IOException;

import src.infrastructure.server.ChatServer;
import src.screens.ServerLogs.ServerLogsScreen;

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
                    frame.hideErrorMessage();
                    
                    ChatServer server = new ChatServer(serverName, Integer.parseInt(serverPortString));
                    server.bind();
                    server.listenConnectionsInParallel();

                    frame.hide();
                    ServerLogsScreen serverLogsScreen = new ServerLogsScreen(server);
                    serverLogsScreen.show();

                } catch (IOException | FontFormatException e) {
                    frame.setErrorMessage("Não foi possível criar o servidor, verifique se a porta já está em uso.");
                    return;
                }
            }
        };
    };
}
