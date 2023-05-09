package src;

import java.awt.FontFormatException;
import java.io.IOException;

import src.screens.FirstChoice.FirstChoiceScreen;

public class Main {
	public static void main(String args[]) throws FontFormatException, IOException {
		FirstChoiceScreen mainScreen = new FirstChoiceScreen();
		mainScreen.show();
	}
}