package Gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Main.Starter;

@SuppressWarnings("serial")
public class CarrelloGui extends JFrame {

	private JPanel contentPane;
	Starter Controller;
	private JTable table;
	private DefaultTableModel tb;
	
	public CarrelloGui(Starter Temp) {
		Controller=Temp;
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 690, 470);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 34, 654, 386);
		contentPane.add(scrollPane);
		
		tb = new DefaultTableModel() ;
		table = new JTable(tb) {
	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
	    };
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Controller.RimuoveElementoCarello(table.getSelectedRow(), tb);
			}
		});
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("Carrello");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(298, 2, 72, 31);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Esci dal Carrello");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Controller.SpegniCarrello();
			}
		});
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setForeground(Color.BLUE);
		lblNewLabel_1.setBounds(565, 8, 101, 25);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Paga");
		lblNewLabel_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		lblNewLabel_1_1.setForeground(Color.BLUE);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(10, 8, 101, 25);
		contentPane.add(lblNewLabel_1_1);
		
		tb.setColumnCount(4);
		Controller.RinominaTabella(0,"Nome Prodotto",table);
		Controller.RinominaTabella(1,"Prezzo",table);
		Controller.RinominaTabella(2,"Quantita",table);
		Controller.RinominaTabella(3,"Id",table);   
		Controller.SpegniColonna("Id",table);
		table.getTableHeader().setReorderingAllowed(false);
		Controller.RiempiTabellaCarrello(tb);
	}

}
