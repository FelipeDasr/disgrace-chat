package src.screens.FirstChoice;

import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import src.components.SecondStepGenericScreen;

class ScreenHandler {
    private final FirstChoiceScreen frame;

    public ScreenHandler(FirstChoiceScreen frame) {
        this.frame = frame;
    }

    public ActionListener createServerOnClick() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				try {
					SecondStepGenericScreen createServerScreen = new SecondStepGenericScreen("Criar servidor");
					
					createServerScreen.setFirstFormInputLabel("Nome:               ");
					createServerScreen.setSecondFormInputLabel("Porta:              ");
					createServerScreen.setButtonLabel("Criar servidor");

					frame.hide();
					createServerScreen.show();
				} catch (FontFormatException | IOException error) {
					System.out.println(error.getMessage());
				}
			}
		};
	}

	public ActionListener joinServerOnClick() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				try {
					SecondStepGenericScreen createServerScreen = new SecondStepGenericScreen("Entrar em um servidor");
					
					createServerScreen.setFirstFormInputLabel("Host:               ");
					createServerScreen.setSecondFormInputLabel("Porta:              ");
					createServerScreen.setButtonLabel("Entrar");

					frame.hide();
					createServerScreen.show();
				} catch (FontFormatException | IOException e) {
					System.out.println(e.getMessage());
				}
			}
		};
	}
}
