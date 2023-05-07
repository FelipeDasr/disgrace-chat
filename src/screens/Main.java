package src.screens;

import java.awt.*;
import java.io.IOException;

import javax.swing.*;

import src.components.Assets;
import src.components.GenericButton;

public class Main {	
    private final Assets appAssets = new Assets();
	
	public Main() throws FontFormatException, IOException {
		JFrame frame = new JFrame("DisGrace");
		JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 255, 255));

		frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setMaximumSize(new Dimension(743, 558));
		frame.setIconImage(this.appAssets.getAppIcon());
		
		JButton createServerButton = new GenericButton("Criar servidor");
		createServerButton.setAlignmentX(JButton.CENTER_ALIGNMENT);

		JButton joinServerButton = new GenericButton("Entrar em um servidor");
		joinServerButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
		
		JLabel logoPresentation = this.getAppLogo();

		panel.add(Box.createVerticalStrut(25));
		panel.add(logoPresentation);

		panel.add(Box.createVerticalStrut(45));
		panel.add(createServerButton);

		panel.add(Box.createVerticalStrut(15));
		panel.add(joinServerButton);

		frame.add(panel);
		frame.setSize(743, 558);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	private JLabel getAppLogo() {
        Image appLogo = this.appAssets.getAppLogo();
		ImageIcon resizedLogo = new ImageIcon(appLogo.getScaledInstance(200, 250,  java.awt.Image.SCALE_SMOOTH));
		JLabel logo = new JLabel();
		logo.setAlignmentX(JButton.CENTER_ALIGNMENT);
		logo.setIcon(resizedLogo);
		
		return logo;
	}
	
	public static void main(String[] args) throws FontFormatException, IOException {
		new Main();
	}
}