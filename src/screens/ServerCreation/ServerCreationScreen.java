package src.screens.ServerCreation;

import java.awt.FontFormatException;
import java.io.IOException;

import src.screens.SecondStepGeneric.SecondStepGenericScreen;

public class ServerCreationScreen extends SecondStepGenericScreen {
    private ScreenHandler screenHandler;

    public ServerCreationScreen() throws FontFormatException, IOException {
        super("Criar servidor");
        this.screenHandler = new ScreenHandler(this);

        this.setFirstFormInputLabel("Nome:            ");
        this.setSecondFormInputLabel("Porta:              ");
        this.setButtonLabel("Criar servidor");

        this.getMainButton().addActionListener(this.screenHandler.confirmServerCreationOnClick());
    }
}
