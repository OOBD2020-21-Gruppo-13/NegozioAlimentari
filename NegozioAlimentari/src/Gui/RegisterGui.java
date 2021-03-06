package Gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import Main.Starter;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class RegisterGui extends JFrame {

	private JPanel contentPane;
	private JButton RegisterButton;
	private JLabel LabelNome;
	private JLabel LabelCognome;
	private JLabel LabelPassword;
	private JLabel LabelRitornoLogin;
	private JTextField NomeField;
	private JTextField CognomeField;
	private JPasswordField PasswordField;
	private Starter Controller;

	public RegisterGui(Starter temp){
		setTitle("Register");
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
		
		RegisterButton = new JButton("Registrati");
		RegisterButton.addActionListener(new ActionListener(){
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				try {
					if(!Controller.RegisterButtonGui(NomeField.getText(), CognomeField.getText(), PasswordField.getText())) {
						JOptionPane.showMessageDialog(null, "Dati Non Validi");	
					}
				} catch (SQLException e1) {	
					System.out.println(e1);
				}		
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
		LabelNome = new JLabel("Nome");
		LabelNome.setBounds(42, 51, 86, 14);
		contentPane.add(LabelNome);
		LabelCognome = new JLabel("Cognome");
		LabelCognome.setBounds(42, 94, 86, 14);
		contentPane.add(LabelCognome);
		LabelPassword = new JLabel("Password");
		LabelPassword.setBounds(42, 135, 86, 14);
		contentPane.add(LabelPassword);
		LabelRitornoLogin = new JLabel("Ritorno Al Login");
		LabelRitornoLogin.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				RegisterGui.this.setVisible(false);
				Controller.AccendiPrimaGui();
			}
		});
		LabelRitornoLogin.setForeground(Color.BLUE);
		LabelRitornoLogin.setBounds(42, 200, 122, 14);
		contentPane.add(LabelRitornoLogin);
	}
}
