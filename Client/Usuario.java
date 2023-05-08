package Client;

import java.awt.*;
import javax.swing.*;
 
public class Usuario{
    private final JFrame frame;
    private final JPanel panel;
    //private final JComboBox dropdown;
    private final JLabel selectAvatar, l1;
    private final ImageIcon icon = new ImageIcon("src/assets/images/DisgraceIcon1.png");

    
    public Usuario(){
        frame = new JFrame("DisGrace");
		panel = new JPanel();

        // create a object
        //Usuario s = new Usuario();

        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setMaximumSize(new Dimension(743, 558));
		frame.setIconImage(this.icon.getImage());

        // array of string containing "images"
        String s1[] = { "Avatar 01", "Avatar 02", "Avatar 03", "Avatar 04", "Avatar 05" };

        // create checkbox
        JComboBox dropdown = new JComboBox<>(s1);
        dropdown.setAlignmentX(JComboBox.CENTER_ALIGNMENT);
        dropdown.setMaximumSize(new Dimension(97, 40));


        // add ItemListener
        //dropdown.addItemListener(s);

        // Create labels
        selectAvatar = new JLabel("Selecione o seu avatar:");
        l1 = new JLabel("AVATAR SELECIONADO!");

        // set color of text
        selectAvatar.setForeground(Color.white);
        l1.setForeground(Color.white);

        panel.add(Box.createVerticalStrut(45));
        panel.add(selectAvatar);

        panel.add(Box.createVerticalStrut(45));
        panel.add(dropdown);
        
        panel.add(Box.createVerticalStrut(45));
        panel.add(l1);




        
        frame.add(panel);
		frame.setSize(743, 558);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

    }
    public static void main(String[] args){
        new Usuario();

    }
}
