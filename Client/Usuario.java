package Client;

import src.components.Assets;
import src.components.GenericButton;

import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridLayout;
import java.awt.event.*;
import java.io.IOException;

public class Usuario extends JFrame implements ItemListener {
    private final JFrame frame;
    private final JPanel mainPanel, panelDropdown, secondaryPanel;
    private final Color whiteColor = new Color(255, 255, 255);
    private final JComboBox dropdown;
    private final ImageIcon icon;
    private final Font mainFont;
    private final GenericButton enterButton;

    public Usuario() throws FontFormatException, IOException {
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
        //panelDropdown.setAlignmentY(JPanel.CENTER_ALIGNMENT);
        panelDropdown.setMaximumSize(new Dimension(30, 30));

        // secondary panel
        this.secondaryPanel = new JPanel();
        this.secondaryPanel.setLayout((new BoxLayout(secondaryPanel, BoxLayout.Y_AXIS)));
        secondaryPanel.setBackground(Color.white);

        String options[] = {"Avatar 01", "Avatar 02", "Avatar 03",
           "Avatar 04", "Avatar 05", "Avatar 06", "Avatar 07"};

        // create checkbox
        dropdown = new JComboBox(options);
        dropdown.setMaximumSize(new Dimension(130, 40));
        dropdown.setSize(100,30);
        dropdown.setFont(this.mainFont.deriveFont(Font.BOLD, 18));

        JLabel userAvatar = new JLabel();
        userAvatar.setIcon(this.getAvatarImage(0));

        dropdown.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e) {
                if (e.getSource() == dropdown) {
                    int avatarId = 0;
                    for(int i=0;i < 7;i++){
                        if(e.getItem() == options[i]){
                            avatarId = i;
                        }
                    }
                    userAvatar.setIcon(getAvatarImage(avatarId));
                    
                }
            }
        });

        dropdown.setAlignmentX(JComboBox.BOTTOM_ALIGNMENT);

        // Adiciona o JTextField e formata seu tamanho
		JTextField textNameUser = new JTextField();
		textNameUser.setMaximumSize(new Dimension(250, 35));
        textNameUser.setMinimumSize(new Dimension(250, 35));

        textNameUser.setAlignmentY(JTextField.CENTER_ALIGNMENT);
        
        // text username 
		JLabel textNameUsername = new JLabel("Nome de usuario:                             ");
		textNameUsername.setFont(this.mainFont.deriveFont(Font.BOLD, 18));
		textNameUsername.setAlignmentX(JTextField.CENTER_ALIGNMENT);

        // Button
        enterButton = new GenericButton("Salvar");
        enterButton.setAlignmentX(JTextField.CENTER_ALIGNMENT);
        

        this.mainPanel.add(panelDropdown);

        panelDropdown.add(Box.createHorizontalStrut(210));
        panelDropdown.add(userAvatar);
        panelDropdown.add(Box.createHorizontalStrut(60));
        panelDropdown.add(dropdown);
        
        secondaryPanel.add(Box.createVerticalStrut(69));
        secondaryPanel.add(textNameUsername);  
        //Panel.add(Box.createVerticalStrut(2));
        secondaryPanel.add(textNameUser);   
        secondaryPanel.add(Box.createVerticalStrut(60));
        secondaryPanel.add(enterButton);

        mainPanel.add(secondaryPanel);

        // Add o mainPanel the screen
        frame.add(mainPanel);
    
        //config the window 
		frame.setSize(743, 558);
        
        //frame.show();
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
    }

    private ImageIcon getAvatarImage(int avatarId) {
		return new ImageIcon(
            new Assets().getAvatarImage(avatarId).getImage().getScaledInstance(
                150, 150,  java.awt.Image.SCALE_SMOOTH
            )
        );
    }
    
    public void itemStateChanged(ItemEvent e) {
        throw new UnsupportedOperationException("Unimplemented method 'itemStateChanged'");
    }

    public static void main(String[] args) throws FontFormatException, IOException{
        new Usuario();
    }

}

    

