package src.screens.UserCreation;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ScreenHandler {
    private final UserCreationScreen frame;

    public ScreenHandler(UserCreationScreen frame){
        this.frame = frame;
    }

    public ItemListener selectAvatarOnClick(){
        return new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getSource() == frame.getDropdown()) {
                    int avatarId = frame.getDropdown().getSelectedIndex();
                     frame.getUserAvatarLabel().setIcon(frame.getAvatarImage(avatarId));
                }
            }
        };
    }
            }
        };
    }
}
