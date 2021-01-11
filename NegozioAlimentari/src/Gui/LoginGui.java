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
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginGui extends JFrame {

	private JPanel contentPane;
	Starter Controller;
	private JTextField UsernameField;
	private JPasswordField PasswordField;

	public LoginGui(Starter temp) {
		Controller = temp;
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		UsernameField = new JTextField();
		UsernameField.setBounds(179, 84, 86, 20);
		contentPane.add(UsernameField);
		UsernameField.setColumns(10);
		
		PasswordField = new JPasswordField();
		PasswordField.setBounds(179, 129, 86, 20);
		contentPane.add(PasswordField);
		
		JButton AccediButton = new JButton("Accedi");
		AccediButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Controller.getDAO2().Login(Integer.parseInt(UsernameField.getText()), PasswordField.getText());
				} catch (SQLException e1) {
					System.out.println(e1);
				}
			}
		});
		AccediButton.setBounds(179, 160, 89, 23);
		contentPane.add(AccediButton);
		
		JLabel UsernameLabel = new JLabel("Username");
		UsernameLabel.setBounds(201, 67, 86, 14);
		contentPane.add(UsernameLabel);
		
		JLabel PasswordLabel = new JLabel("Password");
		PasswordLabel.setBounds(201, 115, 104, 14);
		contentPane.add(PasswordLabel);
		
		JLabel CreaAccountButton = new JLabel("Crea Account");
		CreaAccountButton.setForeground(Color.BLUE);
		CreaAccountButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//TODO aggiungere richiamo alla futura gui per il register
			}
		});
		CreaAccountButton.setFont(new Font("Arial", Font.PLAIN, 15));
		CreaAccountButton.setBounds(179, 201, 111, 20);
		contentPane.add(CreaAccountButton);
	}
}
