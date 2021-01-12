package Gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Main.Starter;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegisterGui extends JFrame {

	private JPanel contentPane;
	Starter Controller;
	private JTextField NomeField;
	private JTextField CognomeField;
	private JPasswordField PasswordField;


	public RegisterGui(Starter temp) {
		Controller = temp;
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		NomeField = new JTextField();
		NomeField.setBounds(174, 48, 86, 20);
		contentPane.add(NomeField);
		NomeField.setColumns(10);
		
		JButton RegisterButton = new JButton("Registrati");
		RegisterButton.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				Controller.getDAO2().Register(NomeField.getText(), CognomeField.getText(), PasswordField.getText());
				RegisterGui.this.setVisible(false);
				Controller.AccendiGui();
			}
		});
		RegisterButton.setBounds(174, 196, 89, 23);
		contentPane.add(RegisterButton);
		
		CognomeField = new JTextField();
		CognomeField.setBounds(174, 91, 86, 20);
		contentPane.add(CognomeField);
		CognomeField.setColumns(10);
		
		PasswordField = new JPasswordField();
		PasswordField.setBounds(174, 132, 86, 20);
		contentPane.add(PasswordField);
		
		JLabel LabelNome = new JLabel("Nome");
		LabelNome.setBounds(42, 51, 86, 14);
		contentPane.add(LabelNome);
		
		JLabel LabelCognome = new JLabel("Cognome");
		LabelCognome.setBounds(42, 94, 86, 14);
		contentPane.add(LabelCognome);
		
		JLabel LabelPassword = new JLabel("Password");
		LabelPassword.setBounds(42, 135, 86, 14);
		contentPane.add(LabelPassword);
		
		JLabel LabelRitornoLogin = new JLabel("Ritorno Al Login");
		LabelRitornoLogin.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				RegisterGui.this.setVisible(false);
				Controller.AccendiGui();
			}
		});
		LabelRitornoLogin.setForeground(Color.BLUE);
		LabelRitornoLogin.setBounds(42, 200, 122, 14);
		contentPane.add(LabelRitornoLogin);
	}
}
