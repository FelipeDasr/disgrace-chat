package src.screens.JoinServer;

import java.awt.FontFormatException;
import java.io.IOException;

import src.screens.SecondStepGeneric.SecondStepGenericScreen;

public class JoinServerScreen extends SecondStepGenericScreen {
    public JoinServerScreen() throws FontFormatException, IOException {
        super("Entrar em um servidor");

        this.setFirstFormInputLabel("Host:               ");
        this.setSecondFormInputLabel("Porta:              ");
        this.setButtonLabel("Entrar");
    }
}
