package src.screens.FirstChoice;

import java.awt.*;
import java.io.IOException;

import javax.swing.*;

import src.components.Assets;
import src.components.GenericButton;

public class FirstChoiceScreen {
	private final Assets appAssets = new Assets();
	private final ScreenHandler screenHandler;
	private final JFrame frame;

	public FirstChoiceScreen() throws FontFormatException, IOException {
		this.frame = new JFrame("DisGrace");
		this.frame.setLayout(new BoxLayout(this.frame.getContentPane(), BoxLayout.X_AXIS));
		frame.setIconImage(this.appAssets.getAppIcon());

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setMaximumSize(new Dimension(743, 558));

		this.screenHandler = new ScreenHandler(this);

		JLabel logoPresentation = this.getAppLogo();

		GenericButton createServerButton = new GenericButton("Criar servidor");
		createServerButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
		createServerButton.addActionListener(this.screenHandler.createServerOnClick());

		GenericButton joinServerButton = new GenericButton("Entrar em um servidor");
		joinServerButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
		joinServerButton.addActionListener(this.screenHandler.joinServerOnClick());

		panel.add(Box.createVerticalStrut(25));
		panel.add(logoPresentation);

		panel.add(Box.createVerticalStrut(45));
		panel.add(createServerButton);

		panel.add(Box.createVerticalStrut(15));
		panel.add(joinServerButton);

		this.frame.add(panel);
		this.frame.setSize(743, 558);
		this.frame.setLocationRelativeTo(null);
		this.frame.setResizable(false);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private JLabel getAppLogo() {
		Image appLogo = this.appAssets.getAppLogo();
		ImageIcon resizedLogo = new ImageIcon(appLogo.getScaledInstance(200, 250, java.awt.Image.SCALE_SMOOTH));
		JLabel logo = new JLabel();
		logo.setAlignmentX(JButton.CENTER_ALIGNMENT);
		logo.setIcon(resizedLogo);

		return logo;
	}

	public void show() {
		this.frame.setVisible(true);
	}

	public void hide() {
		this.frame.setVisible(false);
	}

	public void dispose() {
		this.frame.dispose();
	}
}
