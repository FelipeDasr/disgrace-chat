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

                if (serverName.length() < 5 || serverName.length() > 12) {
                    frame.setErrorMessage("O nome do servidor deve ter de 5 a 12 caracteres");
                    return;
                }

                String serverPortString = frame.getSecondFormInputTextField().getText().trim();

                if (serverPortString.length() < 4 || serverPortString.length() > 5) {
                    frame.setErrorMessage("A porta do servidor deve ter de 4 a 5 algarismos");
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
