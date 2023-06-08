package src.screens.JoinServer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import src.infrastructure.client.ChatClient;
import src.screens.UserCreation.UserCreationScreen;

// private class (only to be used by JoinServerScreen.java)
class ScreenHandler {
    private final JoinServerScreen frame;

    public ScreenHandler(JoinServerScreen frame) {
        this.frame = frame;
    }

    public ActionListener joinServerOnClick() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.hideErrorMessage();

                String serverHost = frame.getFirstFormInputTextField().getText();
                String serverPort = frame.getSecondFormInputTextField().getText();

                if (serverHost.length() == 0 || serverPort.length() == 0) {
                    frame.setErrorMessage("Preencha todos os campos!");
                    return;
                }

                try {
                    ChatClient chatClient = new ChatClient();
                    chatClient.connectToServer(serverHost, Integer.parseInt(serverPort));
                    frame.hide();

                    UserCreationScreen userCreationScreen = new UserCreationScreen(chatClient);
                    userCreationScreen.show();
                } catch (Exception exception) {
                    frame.setErrorMessage("Erro ao conectar ao servidor!");
                }
            }
        };
    }
}
