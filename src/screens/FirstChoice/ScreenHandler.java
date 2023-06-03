package src.screens.FirstChoice;

import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import src.screens.JoinServer.JoinServerScreen;
import src.screens.SecondStepGeneric.SecondStepGenericScreen;
import src.screens.ServerCreation.ServerCreationScreen;

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
					frame.hide();
					SecondStepGenericScreen createServerScreen = new ServerCreationScreen();
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
					frame.hide();
					JoinServerScreen createServerScreen = new JoinServerScreen();
					createServerScreen.show();
				} catch (FontFormatException | IOException e) {
					System.out.println(e.getMessage());
				}
			}
		};
	}
}
