package Gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import Main.Starter;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class LoginGui extends JFrame {

	private JPanel ContentPane;
	private JTextField UsernameField;
	private JPasswordField PasswordField;
	private JButton AccediButton;
	private JLabel UsernameLabel;
	private JLabel PasswordLabel;
	private JLabel CreaAccountLabel;
	private JLabel AmministrazioneLabel;
	private Starter Controller;
	
	public LoginGui(Starter temp) {
		setTitle("Login");
		Controller = temp;
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		ContentPane = new JPanel();
		ContentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(ContentPane);
		ContentPane.setLayout(null);
	
		UsernameField = new JTextField();
		UsernameField.setBounds(179, 84, 86, 20);
		ContentPane.add(UsernameField);
		UsernameField.setColumns(10);
		
		PasswordField = new JPasswordField();
		PasswordField.setBounds(179, 129, 86, 20);
		ContentPane.add(PasswordField);
		
		AccediButton = new JButton("Accedi");
		AccediButton.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e){
				if(Controller.LoginButtonGui(UsernameField.getText(),PasswordField.getText())) {
					UsernameField.setText(null);
					PasswordField.setText(null);
				}else JOptionPane.showMessageDialog(null, "Credenziali Non Valide");
			}
		});
		AccediButton.setBounds(179, 160, 89, 23);
		ContentPane.add(AccediButton);
		
		UsernameLabel = new JLabel("IdTessera");
		UsernameLabel.setBounds(201, 67, 86, 14);
		ContentPane.add(UsernameLabel);
		
		PasswordLabel = new JLabel("Password");
		PasswordLabel.setBounds(201, 115, 104, 14);
		ContentPane.add(PasswordLabel);
		
		CreaAccountLabel = new JLabel("Crea Account");
		CreaAccountLabel.setForeground(Color.BLUE);
		CreaAccountLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Controller.AccendiRegister();
			}
		});
		CreaAccountLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		CreaAccountLabel.setBounds(179, 201, 111, 20);
		ContentPane.add(CreaAccountLabel);
		
		AmministrazioneLabel = new JLabel("Amministrazione");
		AmministrazioneLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Controller.AccendiAdmin();
			}
		});
		AmministrazioneLabel.setForeground(Color.BLUE);
		AmministrazioneLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		AmministrazioneLabel.setBounds(10, 11, 111, 20);
		ContentPane.add(AmministrazioneLabel);
	}
}
