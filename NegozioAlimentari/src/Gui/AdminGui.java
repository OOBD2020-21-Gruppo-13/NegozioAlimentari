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

	private JPanel ContentPane;
	private JLabel CercaLabel;
	private JLabel IndietroLabel;
	private JTable Table;
	private JScrollPane ScrollPane;
	private JTextField CercaField;
	private JButton ClienteButton;
	private JButton PuntiButton;
	private JButton DipendenteButton;
	private JButton PeriodoButton;
	private DefaultTableModel Tb;
	private Starter Controller;

	public AdminGui(Starter temp) {
		setTitle("Admin");
		Controller = temp;
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 814, 490);
		ContentPane = new JPanel();
		ContentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(ContentPane);
		ContentPane.setLayout(null);
		ScrollPane = new JScrollPane();
		ScrollPane.setBounds(10, 31, 778, 409);
		ContentPane.add(ScrollPane);
		
		Tb = new DefaultTableModel() 
		{
			@Override
	    	public Class<Integer> getColumnClass(int column) 
	    	{
	    		return Integer.class;
	    	}
		};
		
		Table = new JTable(Tb) 
		{
	        public boolean isCellEditable(int row, int column) 
	        {
	        	return false;               
	        };
	    };
		Table.setAutoCreateRowSorter(true);
		Table.getTableHeader().setReorderingAllowed(false);
		Table.getTableHeader().setResizingAllowed(false);
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(Tb);
		Table.setRowSorter(sorter);
		ScrollPane.setViewportView(Table);
		CercaField = new JTextField();
		CercaField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				sorter.setRowFilter(RowFilter.regexFilter("(?i)"+CercaField.getText()));
			}
		});
		CercaField.setBounds(702, 4, 86, 23);
		ContentPane.add(CercaField);
		CercaField.setColumns(10);
		ClienteButton = new JButton("Clienti");
		ClienteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				try {
					Controller.RiempiTabellaAdmin(Tb, "select * from cliente_admin", "cliente_admin");
				} catch (SQLException e1) {
					System.out.println(e1);
				}
			}
		});
		ClienteButton.setBounds(105, 4, 70, 23);
		ContentPane.add(ClienteButton);
		DipendenteButton = new JButton("Info Dipendenti");
		DipendenteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Controller.RiempiTabellaAdmin(Tb, "select * from dipendente_introito", "dipendente_introito");
				} catch (SQLException e1) {
					System.out.println(e1);
				}
			}
		});
		DipendenteButton.setBounds(349, 4, 120, 23);
		ContentPane.add(DipendenteButton);
		
		PuntiButton = new JButton("Clienti Punti Categoria");
		PuntiButton.setFont(new Font("Tahoma", Font.BOLD, 10));
		PuntiButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Controller.RiempiTabellaAdmin(Tb, "select * from punti", "punti");
				} catch (SQLException e1) {
					System.out.println(e1);
				}
			}
		});
		PuntiButton.setBounds(185, 4, 154, 23);
		ContentPane.add(PuntiButton);
		
		PeriodoButton = new JButton("Info Dipendenti \r\nPeriodo");
		PeriodoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = Controller.ChiediData();
					if(query!= null && query.isEmpty()!=true) 
					Controller.RiempiTabellaAdmin(Tb, query, "dipendente_introito");
				} catch (SQLException e1) {
					System.out.println(e1);
				}
			}
		});	
		PeriodoButton.setBounds(479, 4, 168, 23);
		ContentPane.add(PeriodoButton);
		CercaLabel = new JLabel("Cerca");
		CercaLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		CercaLabel.setBounds(657, 5, 50, 20);
		ContentPane.add(CercaLabel);
		IndietroLabel = new JLabel("Torna al Login\r\n");
		IndietroLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AdminGui.this.setVisible(false);
				Controller.AccendiPrimaGui();
			}
		});
		IndietroLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		IndietroLabel.setForeground(Color.BLUE);
		IndietroLabel.setBounds(10, 4, 101, 25);
		ContentPane.add(IndietroLabel);
	}
}
