package src.components;

import java.awt.*;
import java.io.IOException;

import javax.swing.*;

public class SecondStepGenericScreen {
	private final Color mainColor = new Color(101, 88, 245);
	private final Color whiteColor = new Color(255, 255, 255);
	private final ImageIcon icon;
	private final Font mainFont;
	
	private final JFrame frame;
	private final JPanel mainPanel;
	private JButton mainButton;

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
	
		this.mainButton = this.getCommonButton("");

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

	public void setFirstFormInput(String labelName) { // Muda o texto do primeiro form input
		this.firstFormInput = this.getFormInput(labelName);
	}

	public void setSecondFormInput(String labelName) { // Muda o texto do primeiro form input
		this.secondFormInput = this.getFormInput(labelName);
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
	
	private JButton getCommonButton(String label) {	
		JButton commonButton = new JButton(label);
		
		commonButton.setMargin(new Insets(6, 16, 6, 16));
		commonButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
		commonButton.setBackground(this.mainColor);
		commonButton.setForeground(this.whiteColor);
		commonButton.setFont(this.mainFont.deriveFont(Font.BOLD, 19));
		
		return commonButton;
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

	public static void main(String[] args) throws FontFormatException, IOException {
		SecondStepGenericScreen frame = new SecondStepGenericScreen("CRIAR SERVIDOR");
		frame.setFirstFormInput("Nome:            ");
		frame.setSecondFormInput("Porta:             ");
		frame.setButtonLabel("Criar");
		frame.show();
	}
}
