package Gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import Main.Starter;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class AdminGui extends JFrame {

	private JPanel contentPane;
	public JTable table;
	private JTextField textField;
	private JButton btnNewButton;
	private DefaultTableModel tb;
	Starter Controller = null;

	public AdminGui(Starter temp) {
		setResizable(false);
		Controller = temp;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 814, 490);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 31, 778, 409);
		contentPane.add(scrollPane);
		
		tb = new DefaultTableModel() 
		{
			@Override
	    	public Class<Integer> getColumnClass(int column) 
	    	{
	    		return Integer.class;
	    	}
		};
		
		table = new JTable(tb) {
	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
	    };
		table.setAutoCreateRowSorter(true);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(tb);
		table.setRowSorter(sorter);
		scrollPane.setViewportView(table);
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				sorter.setRowFilter(RowFilter.regexFilter("(?i)"+textField.getText()));
			}
		});
		textField.setBounds(702, 4, 86, 23);
		contentPane.add(textField);
		textField.setColumns(10);
		
		btnNewButton = new JButton("Clienti");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				try {
					Controller.RiempiTabellaAdmin(tb, "select * from cliente_admin", "cliente_admin");
				} catch (SQLException e1) {
					System.out.println(e1);
				}
			}
		});
		btnNewButton.setBounds(105, 4, 70, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_6 = new JButton("Info Dipendenti");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Controller.RiempiTabellaAdmin(tb, "select * from dipendente_introito", "dipendente_introito");
				} catch (SQLException e1) {
					System.out.println(e1);
				}
			}
		});
		btnNewButton_6.setBounds(349, 4, 120, 23);
		contentPane.add(btnNewButton_6);
		
		JButton btnNewButton_7 = new JButton("Clienti Punti Categoria");
		btnNewButton_7.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Controller.RiempiTabellaAdmin(tb, "select * from punti", "punti");
				} catch (SQLException e1) {
					System.out.println(e1);
				}
			}
		});
		btnNewButton_7.setBounds(185, 4, 154, 23);
		contentPane.add(btnNewButton_7);
		
		JButton btnNewButton_8 = new JButton("Info Dipendenti \r\nPeriodo");
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					String query = Controller.ChiediData();
					if(query!= null && query.isEmpty()!=true) 
					Controller.RiempiTabellaAdmin(tb, query, "dipendente_introito");
				} catch (SQLException e1) {
					System.out.println(e1);
				}
			}
		});	
		btnNewButton_8.setBounds(479, 4, 168, 23);
		contentPane.add(btnNewButton_8);
		JLabel lblNewLabel = new JLabel("Cerca");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(657, 5, 50, 20);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Torna al Login\r\n");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AdminGui.this.setVisible(false);
				Controller.AccendiGui();
			}
		});
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setForeground(Color.BLUE);
		lblNewLabel_1.setBounds(10, 4, 101, 25);
		contentPane.add(lblNewLabel_1);
	}
}
