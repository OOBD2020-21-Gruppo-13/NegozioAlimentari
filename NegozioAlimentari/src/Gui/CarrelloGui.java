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

	private JPanel ContentPane;
	private JScrollPane ScrollPane;
	private JLabel CarrelloLabel;
	private JLabel EsciLabel;
	private JLabel PagaLabel;
	private JTable Table;
	private DefaultTableModel Tb;
	private Starter Controller;
	
	public CarrelloGui(Starter Temp) {
		setTitle("Carrello");
		Controller=Temp;
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 690, 470);
		ContentPane = new JPanel();
		ContentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(ContentPane);
		ContentPane.setLayout(null);
		
		ScrollPane = new JScrollPane();
		ScrollPane.setBounds(10, 34, 654, 386);
		ContentPane.add(ScrollPane);
		
		Tb = new DefaultTableModel() ;
		Table = new JTable(Tb) {
	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
	    };
		Table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Controller.RimuoviElementoCarello(Table.getSelectedRow(), Tb);
			}
		});
		ScrollPane.setViewportView(Table);
		
		CarrelloLabel = new JLabel("Carrello");
		CarrelloLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		CarrelloLabel.setBounds(298, 2, 72, 31);
		ContentPane.add(CarrelloLabel);
		
		EsciLabel = new JLabel("Esci dal Carrello");
		EsciLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Controller.SpegniCarrello();
			}
		});
		EsciLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		EsciLabel.setForeground(Color.BLUE);
		EsciLabel.setBounds(565, 8, 101, 25);
		ContentPane.add(EsciLabel);
		
		PagaLabel = new JLabel("Paga");
		PagaLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Controller.PagaButton();
			}
		});
		PagaLabel.setForeground(Color.BLUE);
		PagaLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		PagaLabel.setBounds(10, 8, 101, 25);
		ContentPane.add(PagaLabel);
		
		Tb.setColumnCount(4);
		Controller.RinominaColonna(0,"Nome Prodotto",Table);
		Controller.RinominaColonna(1,"Prezzo",Table);
		Controller.RinominaColonna(2,"Quantita",Table);
		Controller.RinominaColonna(3,"Id",Table);   
		Controller.SpegniColonna("Id",Table);
		Table.getTableHeader().setReorderingAllowed(false);
		Controller.RiempiTabellaCarrello(Tb);
	}

}
