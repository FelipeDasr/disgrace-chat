package src.screens.SecondStepGeneric;

import java.awt.*;
import java.io.IOException;

import javax.swing.*;

import src.components.GenericButton;

public class SecondStepGenericScreen {
	private final Color whiteColor = new Color(255, 255, 255);
	private final ImageIcon icon;
	private final Font mainFont;
	
	private final JFrame frame;
	private final JPanel mainPanel;
	private GenericButton mainButton;

	private final JLabel panelTitle;
	private JPanel firstFormInput;
	private JPanel secondFormInput;
	
	public SecondStepGenericScreen(String panelTitle) throws FontFormatException, IOException {
		this.frame = new JFrame("DisGrace");
		this.mainPanel = new JPanel();
		this.mainPanel.setBackground(this.whiteColor);
        
        this.icon = new ImageIcon("src/assets/images/DisgraceIcon1.png");
		
		frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
		this.mainPanel.setLayout(new BoxLayout(this.mainPanel, BoxLayout.Y_AXIS));
		this.mainPanel.setMaximumSize(new Dimension(743, 558));
		frame.setIconImage(this.icon.getImage());

		this.mainFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("src/assets/fonts/MainFont.ttf"));

		// Texto que irá aparecer no panel
		this.panelTitle = new JLabel(panelTitle);
		this.panelTitle.setFont(this.mainFont.deriveFont(Font.BOLD, 30));
		this.panelTitle.setAlignmentX(JTextField.CENTER_ALIGNMENT); // CENTRALIZA O TEXTO NO MEIO DO PANEL
	
		this.mainButton = new GenericButton("");
		this.firstFormInput = this.getFormInput("");
		this.secondFormInput = this.getFormInput("");

		this.mainPanel.add(Box.createVerticalStrut(55));
		this.mainPanel.add(this.panelTitle);

		this.mainPanel.add(Box.createVerticalStrut(55));
		this.mainPanel.add(this.firstFormInput);

		this.mainPanel.add(Box.createVerticalStrut(25));
		this.mainPanel.add(this.secondFormInput);

		this.mainPanel.add(Box.createVerticalStrut(111));
		this.mainPanel.add(mainButton);

		this.mainPanel.add(Box.createVerticalStrut(250));

		this.frame.add(this.mainPanel);
		this.frame.setSize(743, 558);
		this.frame.setLocationRelativeTo(null);
		this.frame.setResizable(false);
		this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public void setButtonLabel(String label) { // Muda o texto do botão
		this.mainButton.setText(label);
	}

	public void setFirstFormInputLabel(String labelName) { // Muda o texto do primeiro form input
		JLabel label = (JLabel) this.firstFormInput.getComponent(0);
		label.setText(labelName);
	}

	public void setSecondFormInputLabel(String labelName) { // Muda o texto do primeiro form input
		JLabel label = (JLabel) this.secondFormInput.getComponent(0);
		label.setText(labelName);
	}

	public JTextField getFirstFormInputTextField() {
		return (JTextField) this.firstFormInput.getComponent(1);
	}

	public JTextField getSecondFormInputTextField() {
		return (JTextField) this.secondFormInput.getComponent(1);
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

	private JPanel getFormInput(String labelName){
		JPanel formInputPanel = new JPanel();
		formInputPanel.setLayout(new BoxLayout(formInputPanel, BoxLayout.Y_AXIS));
		formInputPanel.setBackground(this.whiteColor);

		formInputPanel.setMaximumSize(new Dimension(200, 70));
		formInputPanel.setMaximumSize(new Dimension(200, 35));

		// Adiciona dois JTextField e formara seu tamanho
		JTextField textInput = new JTextField();
		textInput.setMaximumSize(new Dimension(300, 70));
        textInput.setMinimumSize(new Dimension(300, 35));
		
		//text endress and port
		JLabel label = new JLabel(labelName);
		label.setFont(this.mainFont.deriveFont(Font.BOLD, 18));
		label.setAlignmentX(JTextField.RIGHT_ALIGNMENT);

		formInputPanel.add(label);
		formInputPanel.add(textInput);
		return formInputPanel;

	}
}
