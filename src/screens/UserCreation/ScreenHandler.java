package src.screens.UserCreation;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ScreenHandler {
    private final UserCreationScreen frame;

    public ScreenHandler(UserCreationScreen frame){
        this.frame = frame;
    }

    public ItemListener selectAvatarOnClick(){
        String options[] = {"Avatar 01", "Avatar 02", "Avatar 03",
        "Avatar 04", "Avatar 05", "Avatar 06", "Avatar 07"};

        return new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getSource() == frame.getDropdown()) {
                    int avatarId = 0;
                    for(int i=0;i < 7;i++){
                        if(e.getItem() == options[i]){
                            avatarId = i;
                        }
                    }
                    frame.getUserAvatarLabel().setIcon(frame.getAvatarImage(avatarId));
                }
            }
        };
    }
}
