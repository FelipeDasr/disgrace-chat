package src.screens.UserCreation;

import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;

import org.json.JSONObject;

import src.infrastructure.client.ChatClient;
import src.screens.GeneralChat.GeneralChatScreen;

public class ScreenHandler {
    private final UserCreationScreen frame;

    public ScreenHandler(UserCreationScreen frame) {
        this.frame = frame;
    }

    public ItemListener selectAvatarOnClick() {
        return new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getSource() == frame.getDropdown()) {
                    int avatarId = frame.getDropdown().getSelectedIndex();
                    frame.getUserAvatarLabel().setIcon(frame.getAvatarImage(avatarId));
                }
            }
        };
    }

    public ActionListener confirmUserCreationOnClick() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    int avatarId = frame.getDropdown().getSelectedIndex();
                    String username = frame.getUsernameField().getText();

                    if (username.length() == 0 || username.length() <= 3) {
                        return;
                    }

                    ChatClient chatClient = frame.getChatClient();
                    chatClient.setAvatarId(avatarId);
                    chatClient.setName(username);

                    JSONObject userData = new JSONObject();
                    userData.put("name", username);
                    userData.put("avatarId", avatarId);

                    chatClient.getConnectedPoint().emitEvent("join", userData);

                    frame.hide();
                    GeneralChatScreen generalChatScreen = new GeneralChatScreen(chatClient);
                    generalChatScreen.show();
                } catch (IOException | FontFormatException e) {
                    e.printStackTrace();
                }
            }
        };
    }
}
