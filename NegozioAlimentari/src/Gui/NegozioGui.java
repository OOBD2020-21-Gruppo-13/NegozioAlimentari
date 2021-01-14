package Gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Main.Starter;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class NegozioGui extends JFrame {

	private JPanel contentPane;
	private JLabel ProfiloLabel;
	Starter Controller;
	private JTable table;
	
	
	public NegozioGui(Starter Temp) {
		Controller = Temp;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1110, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ProfiloLabel = new JLabel("");
		ProfiloLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		ProfiloLabel.setBounds(10, 11, 248, 23);
		contentPane.add(ProfiloLabel);
		
		JButton ButtonFrutta = new JButton("Frutta");
		ButtonFrutta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		ButtonFrutta.setBounds(297, 11, 89, 23);
		contentPane.add(ButtonFrutta);
		
		JButton ButtonVerdura = new JButton("Verdura");
		ButtonVerdura.setBounds(391, 11, 89, 23);
		contentPane.add(ButtonVerdura);
		
		JButton ButtonConfezionati = new JButton("Confezionati");
		ButtonConfezionati.setBounds(485, 11, 112, 23);
		contentPane.add(ButtonConfezionati);
		
		JButton ButtonUova = new JButton("Uova");
		ButtonUova.setBounds(602, 11, 89, 23);
		contentPane.add(ButtonUova);
		
		JButton ButtonLatticini = new JButton("Latticini");
		ButtonLatticini.setBounds(696, 11, 89, 23);
		contentPane.add(ButtonLatticini);
		
		JButton ButtonFarinacei = new JButton("Farinacei");
		ButtonFarinacei.setBounds(790, 11, 89, 23);
		contentPane.add(ButtonFarinacei);
		
		JButton ButtonCarrello = new JButton("Carrello");
		ButtonCarrello.setBounds(884, 11, 89, 23);
		contentPane.add(ButtonCarrello);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 45, 1074, 505);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}
	
	public JLabel getProfiloLabel() 
	{
		return ProfiloLabel;
	}	
}
