package Gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

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

public class NegozioGui extends JFrame {

	private JPanel contentPane;
	private JLabel ProfiloLabel;
	Starter Controller;
	private JTable table;
	private DefaultTableModel tb;
	private TableRowSorter<DefaultTableModel> sorter = null;
	
	
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
		ProfiloLabel.setBounds(10, 11, 277, 23);
		contentPane.add(ProfiloLabel);
		
		JButton ButtonFrutta = new JButton("Frutta");
		ButtonFrutta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller.SorterColonna("Frutta",6,table,sorter);
				Controller.RinominaTabella(3,"DataRaccolta",table); 
				Controller.SpegniColonna("DataMungitura",table);
			}
		});
		ButtonFrutta.setBounds(297, 11, 89, 23);
		contentPane.add(ButtonFrutta);
		
		JButton ButtonVerdura = new JButton("Verdura");
		ButtonVerdura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller.SorterColonna("Verdura",6,table,sorter);
				Controller.RinominaTabella(3,"DataRaccolta",table); 
				Controller.SpegniColonna("DataMungitura",table);
			}
		});
		ButtonVerdura.setBounds(391, 11, 89, 23);
		contentPane.add(ButtonVerdura);
		
		JButton ButtonConfezionati = new JButton("Confezionati");
		ButtonConfezionati.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller.SorterColonna("Confezionato",6,table,sorter);
				Controller.RinominaTabella(3,"DataProduzione",table); 
				Controller.SpegniColonna("DataMungitura",table);
			}
		});
		ButtonConfezionati.setBounds(485, 11, 112, 23);
		contentPane.add(ButtonConfezionati);
		
		JButton ButtonUova = new JButton("Uova");
		ButtonUova.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller.SorterColonna("Uova",6,table,sorter);
				Controller.RinominaTabella(3,"DataProduzione",table); 
				Controller.SpegniColonna("DataMungitura",table);
			}
		});
		ButtonUova.setBounds(602, 11, 89, 23);
		contentPane.add(ButtonUova);
		
		JButton ButtonLatticini = new JButton("Latticini");
		ButtonLatticini.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller.SorterColonna("Latticino",6,table,sorter);
				Controller.RinominaTabella(3,"DataProduzione",table); 
				Controller.SpegniColonna("DataMungitura",table);
			}
		});
		ButtonLatticini.setBounds(696, 11, 89, 23);
		contentPane.add(ButtonLatticini);
		
		JButton ButtonFarinacei = new JButton("Farinacei");
		ButtonFarinacei.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller.SorterColonna("Farinaceo",6,table,sorter);
				Controller.RinominaTabella(3,"DataProduzione",table); 
				Controller.SpegniColonna("DataMungitura",table);
			}
		});
		ButtonFarinacei.setBounds(790, 11, 89, 23);
		contentPane.add(ButtonFarinacei);
		
		JButton ButtonCarrello = new JButton("Carrello");
		ButtonCarrello.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller.AccendiCarrello();
				Controller.PrintaCarrelloDebug();
			}
		});
		ButtonCarrello.setBounds(884, 11, 89, 23);
		contentPane.add(ButtonCarrello);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 45, 1074, 505);
		contentPane.add(scrollPane);
		
		tb = new DefaultTableModel() 
        {
            @Override
            public Class getColumnClass(int column) 
            {
                return Integer.class;
            }
        };
		table = new JTable(tb) {
	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };

	    };
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Controller.InserimentoCarrello(table);				
			}
		});
	    
	    tb.setColumnCount(8);
	    Controller.RinominaTabella(0,"Nome Prodotto",table);
	    Controller.RinominaTabella(1,"Prezzo",table);
	    Controller.RinominaTabella(2,"Quantita",table);
	    Controller.RinominaTabella(3,"Data",table);
	    Controller.RinominaTabella(4,"DataScadenza",table);
	    Controller.RinominaTabella(5,"DataMungitura",table);
	    Controller.RinominaTabella(6,"Tipo",table);
	    Controller.RinominaTabella(7,"Id",table);
	    Controller.SpegniColonna("DataMungitura",table);
	    Controller.SpegniColonna("Tipo",table);
	    Controller.SpegniColonna("Id",table);
	    Controller.RiempiTabellaNegozio(tb);
	    table.setAutoCreateRowSorter(true);
		table.getTableHeader().setReorderingAllowed(false);
		sorter = new TableRowSorter<DefaultTableModel>(tb);
		table.setRowSorter(this.sorter);
		Controller.SorterColonna("/*/",6,table,sorter);
		scrollPane.setViewportView(table);
		
		JLabel LogOutLabel = new JLabel("Esci dal Negozio");
		LogOutLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Controller.LogOutNegozio();
			}
		});
		LogOutLabel.setForeground(Color.BLUE);
		LogOutLabel.setBounds(983, 11, 101, 23);
		contentPane.add(LogOutLabel);
	}
	
	public JLabel getProfiloLabel() 
	{
		return ProfiloLabel;
	}	
}
