package src.components;

import java.io.IOException;

import java.awt.FontFormatException;
import java.awt.Insets;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

public class GenericButton extends JButton {
	private final Color mainColor;
	private final Color whiteColor;
	private final Font mainFont;

	public GenericButton(String label) throws FontFormatException, IOException {
		super(label);

		mainColor = new Color(101, 88, 245);
		whiteColor = new Color(255, 255, 255);
		mainFont = new Assets().getMainFont();

		this.setMargin(new Insets(6, 16, 6, 16));
		this.setBackground(this.mainColor);
		this.setForeground(this.whiteColor);
		this.setFont(this.mainFont.deriveFont(Font.BOLD, 19));
		this.setFocusPainted(false);
	}
}