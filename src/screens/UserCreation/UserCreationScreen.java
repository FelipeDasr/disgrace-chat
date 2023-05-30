package src.screens.UserCreation;

import src.components.Assets;
import src.components.GenericButton;
import src.infrastructure.client.ChatClient;

import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridLayout;
import java.awt.event.*;
import java.io.IOException;

public class UserCreationScreen extends JFrame implements ItemListener {
    private final JFrame frame;
    private final JPanel mainPanel, panelDropdown, secondaryPanel;
    private final Color whiteColor = new Color(255, 255, 255);
    private final JComboBox<String> dropdown;
    private final ImageIcon icon;
    private final Font mainFont;
    private final GenericButton enterButton;
    private final ScreenHandler screenHandler;
    private final JTextField usernameTextInput;
    private final JLabel userAvatar;
    private final ChatClient chatClient;

    public UserCreationScreen() throws FontFormatException, IOException {
        this.screenHandler = new ScreenHandler(this);

        this.mainFont = new Assets().getMainFont();
        
        this.frame = new JFrame("DisGrace");
		this.mainPanel = new JPanel(new GridLayout(2,1));
		this.mainPanel.setBackground(this.whiteColor);
        
        this.icon = new ImageIcon("src/assets/images/DisgraceIcon1.png");

        // Panel Principal
        this.mainPanel.setMaximumSize(new Dimension(743, 558));
		frame.setIconImage(this.icon.getImage());

        // Panel Dropdown
        this.panelDropdown = new JPanel();
        this.panelDropdown.setBackground(this.whiteColor);
        this.panelDropdown.setLayout((new BoxLayout(panelDropdown, BoxLayout.X_AXIS)));
        this.panelDropdown.setMaximumSize(new Dimension(30, 30));

        // secondary panel
        this.secondaryPanel = new JPanel();
        this.secondaryPanel.setLayout((new BoxLayout(secondaryPanel, BoxLayout.Y_AXIS)));
        secondaryPanel.setBackground(Color.white);

        String options[] = {"Avatar 01", "Avatar 02", "Avatar 03",
           "Avatar 04", "Avatar 05", "Avatar 06", "Avatar 07"};

        this.dropdown = new JComboBox<String>(options);
        this.dropdown.setMaximumSize(new Dimension(130, 40));
        this.dropdown.setSize(100,30);
        this.dropdown.setFont(this.mainFont.deriveFont(Font.BOLD, 18));

        this.userAvatar = new JLabel();
        this.userAvatar.setIcon(this.getAvatarImage(0));

        this.dropdown.addItemListener(this.screenHandler.selectAvatarOnClick());

        this.dropdown.setAlignmentX(JComboBox.BOTTOM_ALIGNMENT);

        // Adiciona o JTextField e formata seu tamanho e alinhamento
		this.usernameTextInput = new JTextField();
		this.usernameTextInput.setMaximumSize(new Dimension(250, 35));
        this.usernameTextInput.setMinimumSize(new Dimension(250, 35));
        this.usernameTextInput.setAlignmentY(JTextField.CENTER_ALIGNMENT);
        
        // text username 
		JLabel textInputLabel = new JLabel("Nome de usuario:                             ");
		textInputLabel.setFont(this.mainFont.deriveFont(Font.BOLD, 18));
		textInputLabel.setAlignmentX(JTextField.CENTER_ALIGNMENT);

        // Button
        enterButton = new GenericButton("Salvar");
        enterButton.setAlignmentX(JTextField.CENTER_ALIGNMENT);
        enterButton.addActionListener(this.screenHandler.confirmUserCreationOnClick());
        
        // add
        this.mainPanel.add(panelDropdown);

        panelDropdown.add(Box.createHorizontalStrut(210));
        panelDropdown.add(userAvatar);

        panelDropdown.add(Box.createHorizontalStrut(60));
        panelDropdown.add(dropdown);
        
        secondaryPanel.add(Box.createVerticalStrut(69));
        secondaryPanel.add(textInputLabel);  
        secondaryPanel.add(this.usernameTextInput);   
        secondaryPanel.add(Box.createVerticalStrut(60));
        secondaryPanel.add(enterButton);

        mainPanel.add(secondaryPanel);
        frame.add(mainPanel);

        //config the window 
		frame.setSize(743, 558);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

        this.chatClient = new ChatClient();
        this.chatClient.connectToServer("localhost", 7777);
    }

    public ImageIcon getAvatarImage(int avatarId) {
		return new ImageIcon(
            new Assets().getAvatarImage(avatarId).getImage().getScaledInstance(
                150, 150,  java.awt.Image.SCALE_SMOOTH
            )
        );
    }
    
    public void itemStateChanged(ItemEvent e) {
        throw new UnsupportedOperationException("Unimplemented method 'itemStateChanged'");
    }
    
    public JComboBox<String> getDropdown(){
        return this.dropdown;
    }

    public JTextField getUsernameField() {
        return this.usernameTextInput;
    }

    public ChatClient getChatClient() {
        return this.chatClient;
    }

    public JLabel getUserAvatarLabel(){
        return this.userAvatar;
    }

    public static void main(String[] args) throws FontFormatException, IOException {
        new UserCreationScreen();
    }
}
    

