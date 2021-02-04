package Gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import Main.Starter;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class NegozioGui extends JFrame {

	private JPanel ContentPane;
	private JLabel ProfiloLabel;
	private JLabel LogOutLabel;
	private JTable Table;
	private JScrollPane ScrollPane;
	private DefaultTableModel Tb;
	private JButton FruttaButton;
	private JButton VerduraButton;
	private JButton ConfezionatiButton;
	private JButton LatticiniButton;
	private JButton FarinaceiButton;
	private JButton UovaButton;
	private JButton CarrelloButton;
	private TableRowSorter<DefaultTableModel> Sorter;
	private Starter Controller;
	
	public NegozioGui(Starter Temp) {
		setTitle("Negozio");
		Controller = Temp;
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1110, 600);
		ContentPane = new JPanel();
		ContentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(ContentPane);
		ContentPane.setLayout(null);
		ProfiloLabel = new JLabel("");
		ProfiloLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		ProfiloLabel.setBounds(10, 11, 277, 23);
		ContentPane.add(ProfiloLabel);
		
		FruttaButton = new JButton("Frutta");
		FruttaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller.SorterColonna("Frutta",6,Table,Sorter);
				Controller.RinominaColonna(3,"DataRaccolta",Table); 
				Controller.SpegniColonna("DataMungitura",Table);
			}
		});
		FruttaButton.setBounds(297, 11, 89, 23);
		ContentPane.add(FruttaButton);
		
		VerduraButton = new JButton("Verdura");
		VerduraButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller.SorterColonna("Verdura",6,Table,Sorter);
				Controller.RinominaColonna(3,"DataRaccolta",Table); 
				Controller.SpegniColonna("DataMungitura",Table);
			}
		});
		VerduraButton.setBounds(391, 11, 89, 23);
		ContentPane.add(VerduraButton);
		
		ConfezionatiButton = new JButton("Confezionati");
		ConfezionatiButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller.SorterColonna("Confezionato",6,Table,Sorter);
				Controller.RinominaColonna(3,"DataProduzione",Table); 
				Controller.SpegniColonna("DataMungitura",Table);
			}
		});
		ConfezionatiButton.setBounds(485, 11, 112, 23);
		ContentPane.add(ConfezionatiButton);
		
		UovaButton = new JButton("Uova");
		UovaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller.SorterColonna("Uova",6,Table,Sorter);
				Controller.RinominaColonna(3,"DataProduzione",Table); 
				Controller.SpegniColonna("DataMungitura",Table);
			}
		});
		UovaButton.setBounds(602, 11, 89, 23);
		ContentPane.add(UovaButton);
		
		LatticiniButton = new JButton("Latticini");
		LatticiniButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller.SorterColonna("Latticino",6,Table,Sorter);
				Controller.RinominaColonna(3,"DataProduzione",Table); 
				Controller.SpegniColonna("DataMungitura",Table);
			}
		});
		LatticiniButton.setBounds(696, 11, 89, 23);
		ContentPane.add(LatticiniButton);
		
		FarinaceiButton = new JButton("Farinacei");
		FarinaceiButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller.SorterColonna("Farinaceo",6,Table,Sorter);
				Controller.RinominaColonna(3,"DataProduzione",Table); 
				Controller.SpegniColonna("DataMungitura",Table);
			}
		});
		FarinaceiButton.setBounds(790, 11, 89, 23);
		ContentPane.add(FarinaceiButton);
		
		CarrelloButton = new JButton("Carrello");
		CarrelloButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller.AccendiCarrello();
			}
		});
		CarrelloButton.setBounds(884, 11, 89, 23);
		ContentPane.add(CarrelloButton);
		
		ScrollPane = new JScrollPane();
		ScrollPane.setBounds(10, 45, 1074, 505);
		ContentPane.add(ScrollPane);
		
		Tb = new DefaultTableModel() 
        {
            @Override
            public Class<Integer> getColumnClass(int column) 
            {
                return Integer.class;
            }
        };
		Table = new JTable(Tb) {
	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };

	    };
		Table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Controller.InserimentoCarrello(Table);				
			}
		});
	    
	    Tb.setColumnCount(8);
	    Controller.RinominaColonna(0,"Nome Prodotto",Table);
	    Controller.RinominaColonna(1,"Prezzo",Table);
	    Controller.RinominaColonna(2,"Quantita",Table);
	    Controller.RinominaColonna(3,"Data",Table);
	    Controller.RinominaColonna(4,"DataScadenza",Table);
	    Controller.RinominaColonna(5,"DataMungitura",Table);
	    Controller.RinominaColonna(6,"Tipo",Table);
	    Controller.RinominaColonna(7,"Id",Table);
	    Controller.SpegniColonna("DataMungitura",Table);
	    Controller.SpegniColonna("Tipo",Table);
	    Controller.SpegniColonna("Id",Table);
	    Controller.RiempiTabellaNegozio(Tb);
	    Table.setAutoCreateRowSorter(true);
		Table.getTableHeader().setReorderingAllowed(false);
		Sorter = new TableRowSorter<DefaultTableModel>(Tb);
		Table.setRowSorter(this.Sorter);
		Controller.SorterColonna("/*/",6,Table,Sorter);
		ScrollPane.setViewportView(Table);
		
		LogOutLabel = new JLabel("Esci dal Negozio");
		LogOutLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Controller.LogOutNegozio();
			}
		});
		LogOutLabel.setForeground(Color.BLUE);
		LogOutLabel.setBounds(983, 11, 101, 23);
		ContentPane.add(LogOutLabel);
	}
	
	public JLabel getProfiloLabel() 
	{
		return ProfiloLabel;
	}	
}
