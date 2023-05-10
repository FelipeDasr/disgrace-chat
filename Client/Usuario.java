package Client;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Usuario extends JFrame implements ItemListener {
    private final JFrame frame;
    private final JPanel Panel;
    private final Color whiteColor = new Color(255, 255, 255);
    private final JComboBox dropdown;
    private final ImageIcon icon;
    private final JLabel textSelect;
    //private JButton mainButton;

    public Usuario(){
        this.frame = new JFrame("DisGrace");
		this.Panel = new JPanel();
		this.Panel.setBackground(this.whiteColor);

        this.icon = new ImageIcon("src/assets/images/DisgraceIcon1.png");

        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
		this.Panel.setLayout(new BoxLayout(this.Panel, BoxLayout.Y_AXIS));
		this.Panel.setMaximumSize(new Dimension(743, 558));
		frame.setIconImage(this.icon.getImage());


        String options[] = {"Avatar 01", "Avatar 02", "Avatar 03",
           "Avatar 04", "Avatar 05", "Avatar 06", "Avatar 07", "Avatar 08" };

        // create checkbox
        dropdown = new JComboBox(options);

        this.textSelect = new JLabel("Selecione o seu avatar");
        

        dropdown.setMaximumSize(new Dimension(100, 27));
        dropdown.setSize(100,30);

        dropdown.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e) {
                if (e.getSource() == dropdown) {
                    textSelect.setText(dropdown.getSelectedItem() + " Selecionado");
                }
            }
        });

        // set color of text
        textSelect.setForeground(Color.red);

        Panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        Panel.add(dropdown);
        Panel.add(textSelect);
        // Add o panel the screen
        frame.add(Panel);

        //config the window 
		frame.setSize(743, 558);
        
        //frame.show();
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

    }
    

    
    public void itemStateChanged(ItemEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'itemStateChanged'");
    }

    // main class
    public static void main(String[] args){
        new Usuario();
    }

}

    

