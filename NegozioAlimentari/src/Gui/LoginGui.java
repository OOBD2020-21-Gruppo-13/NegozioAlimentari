package Gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Main.Starter;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JLabel;

public class LoginGui extends JFrame {

	private JPanel contentPane;
	Starter Controller;
	private JTextField UsernameField;
	private JPasswordField PasswordField;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGui frame = new LoginGui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public LoginGui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		UsernameField = new JTextField();
		UsernameField.setBounds(375, 102, 86, 20);
		contentPane.add(UsernameField);
		UsernameField.setColumns(10);
		
		PasswordField = new JPasswordField();
		PasswordField.setBounds(375, 155, 86, 20);
		contentPane.add(PasswordField);
		
		JButton AccediButton = new JButton("Accedi");
		AccediButton.setBounds(372, 211, 89, 23);
		contentPane.add(AccediButton);
		
		JLabel UsernameLabel = new JLabel("Username");
		UsernameLabel.setBounds(385, 76, 86, 14);
		contentPane.add(UsernameLabel);
		
		JLabel PasswordLabel = new JLabel("Password");
		PasswordLabel.setBounds(385, 133, 104, 14);
		contentPane.add(PasswordLabel);
	}
}
