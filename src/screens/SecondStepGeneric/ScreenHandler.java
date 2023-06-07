package src.screens.SecondStepGeneric;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ScreenHandler {
    public KeyListener checkKeyPressed() {
        return new KeyListener() {

            @Override
            public void keyTyped(KeyEvent event) {
                boolean keyIsValid = isAValidKeyForPort(event.getKeyChar());

                if (!keyIsValid) {
                    event.setKeyChar('\u0000');
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        };

    }

    private boolean isAValidKeyForPort(char key) {
        int asciiKeyCode = (int) key;
        int asciiBackspaceCode = 8;

        if ((asciiKeyCode >= 48 && asciiKeyCode <= 57) || asciiKeyCode == asciiBackspaceCode)
            return true;
        return false;
    }
}
