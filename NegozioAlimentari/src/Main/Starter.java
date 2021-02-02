package Main;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import ClassiDB.Acquisto;
import ClassiDB.Cliente;
import ClassiDB.Dipendente;
import ClassiDB.Prodotto;
import DaoImplements.*;
import Database.DBConnection;
import Gui.AdminGui;
import Gui.CarrelloGui;
import Gui.LoginGui;
import Gui.NegozioGui;
import Gui.RegisterGui;

public class Starter {

	private DBConnection dbconn = null;
	private Connection connection = null;
	private LoginGui Login;
	private RegisterGui Register;
	private NegozioGui Negozio;
	private CarrelloGui Carrello;
	private AdminGui Admin;
	private DipendenteDAOPostgres DAO1;
	private ClienteDAOPostgres DAO2;
	private ProdottoDAOPostgres DAO3;
	private AcquistiDAOPostgres DAO4;
	private List<Prodotto> Magazzino;
	private Cliente IlCliente;
	private Dipendente IlDipendente;
	private Acquisto acquisto; 
   
	public Starter() throws SQLException 
	{
		dbconn = DBConnection.getInstance();
        connection = dbconn.getConnection();
        DAO1 = new DipendenteDAOPostgres(connection, this);
	    DAO2 = new ClienteDAOPostgres(connection, this);
	    DAO3 = new ProdottoDAOPostgres(connection, this);
	    DAO4 = new AcquistiDAOPostgres(connection, this);
        AccendiGui();
	}
	public static void main(String[] args) throws SQLException 
	{
		new Starter();		
	}
	public void AccendiGui(){
		Login= new LoginGui(this);
		Login.setLocationRelativeTo(null);
		Login.setVisible(true);
	}
	public void AccediRegister() {
		Login.setVisible(false);
		Register = new RegisterGui(this);
		Register.setLocationRelativeTo(null);
		Register.setVisible(true);
	}
	public void AccendiNegozioInfoCliente() 
	{
		Login.setVisible(false);
		Negozio = new NegozioGui(this);
		Negozio.setLocationRelativeTo(null);
		Negozio.setVisible(true);
		Negozio.getProfiloLabel().setText("Ciao "+IlCliente.getNome()+" il tuo Saldo è di "+IlCliente.getSaldo()+"€");
	}
	public void AccendiCarrello(){
		Negozio.setVisible(false);
		Carrello = new CarrelloGui(this);
		Carrello.setLocationRelativeTo(null);
		Carrello.setVisible(true);
	}
	public void AccendiAdmin() {
		
		String input =JOptionPane.showInputDialog("Inserisci password per accendere");
        if(input!= null && input.isEmpty()!=true ) 
        {
            if(input.equals("admin")) 
            {
				Login.setVisible(false);
				Admin = new AdminGui(this);
				Admin.setLocationRelativeTo(null);
				Admin.setVisible(true);
            }else JOptionPane.showMessageDialog(null, "Password Sbagliata");
        }
	}
	public void SpegniCarrello() 
    {
        Carrello.setVisible(false);
        Negozio.setVisible(true);
    }
	public int Random(int range) 
    {
        Random rand = new Random();
        return(1+rand.nextInt(range));
    }
	public double Round(Double x) 
	{
		return Math.round(x * 100.0) / 100.0;
	}
	public void LogOutNegozio() {
		IlCliente.getCarrello().removeAll(IlCliente.getCarrello());
		Negozio.setVisible(false);
		Login.setVisible(true);
	}
	public void Reboot() {
		IlCliente.getCarrello().removeAll(IlCliente.getCarrello());
		Carrello.setVisible(false);
		Login.setVisible(true);
	}
	public boolean LoginButtonGui(String Username, String Password) {
		if(Username!= null && Username.isEmpty()!=true && Password!= null && Password.isEmpty()!=true) {
			try {
				if(DAO2.Login(Integer.parseInt(Username), Password) > 0) {
					Magazzino = DAO3.CopiaDB();
					IlCliente = DAO2.CopiaDB(Integer.parseInt(Username));
					AccendiNegozioInfoCliente();
					return true;
				}
			} catch (NumberFormatException nfe) {
				return false;	
			}catch (SQLException e1) {
				System.out.println(e1);
				return false;
			}
		}
		return false;
	}
	public boolean RegisterButtonGui(String Nome, String Cognome, String Password) throws SQLException {
		if(Nome!= null && Nome.isEmpty()!=true && Cognome!= null && Cognome.isEmpty()!=true && Password!= null && Password.isEmpty()!=true) {
			if(DAO2.Register(Nome, Cognome, Password)) {
				JOptionPane.showMessageDialog(null, "Ti è stata assegnata la tessera numero: "+ String.valueOf(DAO2.RicavoId()-1) +" per accedere usa questo numero","Registrazione effettuata",JOptionPane.PLAIN_MESSAGE);
				Register.setVisible(false);
				AccendiGui();
				return true;
			}else return false;		
		}
	return false;
	}
	public void RiempiTabellaNegozio(DefaultTableModel x) 
	{
		for(ClassiDB.Prodotto e : Magazzino) 
		{
			Object o[] = {e.getNome(),e.getPrezzo(),e.getQuantita(),e.getDataProdRacc(),e.getDataScadenza(),e.getDataMungitura(),e.getTipo(),e.getIdProdotto()};
			x.addRow(o);
		}	
	}
	public void RiempiTabellaCarrello(DefaultTableModel x) 
    {
        for(ClassiDB.Prodotto e : IlCliente.getCarrello()) 
        {
            Object o[] = {e.getNome(),e.getPrezzo(),e.getQuantita(),e.getIdProdotto()};
            x.addRow(o);
        }
    }
	public void RiempiTabellaAdmin(DefaultTableModel x,String table,String parziale) throws SQLException 
    {
        Object[] Colonne = DAO1.RiempiColonne(parziale);
        Object[][] Dati = DAO1.RiempiDati(table, parziale);
        x.setDataVector(Dati, Colonne);
    }
	public void SpegniColonna(String Colonna,JTable t) 
	{
		if(t.getColumn(Colonna).getWidth()>0) 
		{
			t.getColumn(Colonna).setMinWidth(0); 
			t.getColumn(Colonna).setMaxWidth(0);
			t.getColumn(Colonna).setWidth(0);
		}
	}
	public void RinominaTabella(int index,String Nome,JTable t) 
	{
		t.getTableHeader().getColumnModel().getColumn(index).setHeaderValue(Nome);
		t.getTableHeader().repaint();
	}
	public void InserisciProdottoCarrello(int id,int quantita) 
	{
		Prodotto p = new Prodotto (quantita,id,Magazzino.get(id-1).getPrezzo(),Magazzino.get(id-1).getNome());
        if(UnisciQuantitaProdotto(quantita, Magazzino.get(id-1).getIdProdotto())==false) {
            IlCliente.getCarrello().add(p);
        }
        Magazzino.get(id-1).setQuantita(Magazzino.get(id-1).getQuantita()-quantita);
	}
	public boolean UnisciQuantitaProdotto(int quantita,int idprodotto) 
    {
        if((IlCliente.getCarrello().isEmpty())!=true) 
        {
            Iterator<Prodotto> i = IlCliente.getCarrello().iterator();
            while(i.hasNext())
            {
                Prodotto temp = i.next();
                if(idprodotto == temp.getIdProdotto()) 
                {
                    temp.setQuantita(temp.getQuantita()+quantita);
                    return true;
                }
            }
        }
        return false;
    }
	public void SorterColonna(String Tipo,int index,JTable table,TableRowSorter<DefaultTableModel> sorter) 
	{
		table.setRowSorter(sorter);
		sorter.setRowFilter(RowFilter.regexFilter(Tipo, index));
	}
	public void InserimentoCarrello(JTable table) 
    {
        int index = table.getSelectedRow();
        int id = (int) table.getValueAt(index, 7);
        int decisone = JOptionPane.showConfirmDialog(null,"Vuoi aggiungere "+Magazzino.get(id-1).getNome()+" al carrello?","Aggiungi carrello",JOptionPane.YES_NO_OPTION);
        if(decisone == 0) 
        {
            int quantita = ChiediQuantita(id);
            if(quantita>0)
            InserisciProdottoCarrello(id, quantita);
        }
    }
    public int ChiediQuantita(int id) 
    {
        String temp=null;
        int quantita=0;
        do {
            temp = JOptionPane.showInputDialog(null,"Inserisci quantita");
            if(temp!= null && temp.isEmpty()!=true) 
            {
                try 
                {
                    quantita = Integer.parseInt(temp);
                }catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Hai inserito dei valori sbagliati");
                    break;
                }
                if(quantita<=0) 
                {
                    JOptionPane.showMessageDialog(null, "Hai inserito una quantita non valida");
                }else if(quantita<=Magazzino.get(id-1).getQuantita()) {
                    return quantita;
                }else {
                    JOptionPane.showMessageDialog(null, "Hai inserito una quantita non valida");
                }
            }else break;
        } while (true);
        return 0;
    }
    public void RemoveTable(DefaultTableModel x) 
    {
     int righe = x.getRowCount();
     if(righe>0) 
     {
      for(int a=righe;a>0;a--) 
      {
       x.removeRow(a-1);
      }
     }
    }
    public void RimuoveElementoCarello(int id,DefaultTableModel x) 
	{
		int quantita=0;
			do {
				String temp = JOptionPane.showInputDialog(null,"Inserisci quantita da eliminare");
				if(temp!= null && temp.isEmpty()!=true) 
				{
					try {
						quantita = Integer.parseInt(temp);
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null, "Hai inserito dei valori sbagliati");
						break;
					}
					if(quantita<=IlCliente.getCarrello().get(id).getQuantita()) 
					{
						int idprodotto = (int) x.getValueAt(id, 3);
						Magazzino.get(idprodotto-1).setQuantita(Magazzino.get(idprodotto-1).getQuantita()+quantita);
						IlCliente.getCarrello().get(id).setQuantita(IlCliente.getCarrello().get(id).getQuantita()-quantita);
						if(IlCliente.getCarrello().get(id).getQuantita()==0) 
						{
							IlCliente.getCarrello().remove(id);
						}
						RemoveTable(x);
						RiempiTabellaCarrello(x);
						break;
					}else {
							JOptionPane.showMessageDialog(null, "Hai inserito una quantita non valida");
					}
				}else break;
			} while (true);
	}
    public double CalcoloCarrello() 
	{
	 double x=0;
		for(ClassiDB.Prodotto p: IlCliente.getCarrello()) 
		{
			x = x+ ((p.getPrezzo())*(p.getQuantita()));
		}
		return Round(x);
	}
    public void PagaButton() 
    {
        if(IlCliente.getCarrello().isEmpty()!=true) 
        {
            try {
                int decisione = JOptionPane.showConfirmDialog(null,"Il tuo saldo è: "+IlCliente.getSaldo()+"€ a fronte di: " +CalcoloCarrello()+"€","Vuoi pagare?",JOptionPane.YES_NO_OPTION);
                if(decisione == 0) 
                {
                	Double Prezzo = CalcoloCarrello();
                    if(IlCliente.getSaldo()>=Prezzo) 
                    {
	                    IlDipendente = DAO1.CopiaDB(Random(DAO1.NumeroDipendente()));
	                    CreazioneDBAcquisto(Prezzo);
	                    JOptionPane.showMessageDialog(null, "Pagamento avvenuto con successo\nIl tuo ordine è stato portato a termine da: "+IlDipendente.getNome()+" "+IlDipendente.getCognome()+"\nArriverderci e torna a trovarci!");
	                    Reboot();
                    }else JOptionPane.showMessageDialog(null, "Non hai abbastanza soldi, rimuovi qualche prodotto dal carrello");
                }
            } catch (SQLException e1) {
                System.out.println(e1);
            }
        }else JOptionPane.showMessageDialog(null, "Il carrello è vuoto, aggiungi qualche prodotto dal negozio");
    }
    public Double CalcoloPuntiTotale() 
    {
        return Round((CalcoloCarrello()*10)/100);
    }
    public void CreazioneDBAcquisto(Double prezzo) throws SQLException 
    {
    	acquisto = new Acquisto(IlCliente.getCarrello(), IlCliente, IlDipendente, DAO4.RicavoIdAcquisto(), prezzo, CalcoloPuntiTotale());
        DAO4.SalvaAcquisto(acquisto);
        IlCliente.getCarrello().removeAll(IlCliente.getCarrello());
    }
    public String ChiediData() 
	{
		String pattern = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[1-9])|(1[0-2]))\\:([0-5][0-9])((\\s)|(\\:([0-5][0-9])\\s))([AM|PM|am|pm]{2,2})))?$";
		String Inizio = null;	 		
		String Fine = null; 	 		
		do {
			Inizio = JOptionPane.showInputDialog(null, "Inserisci data iniziale (Formato yyyy-mm-dd)");
			Fine = JOptionPane.showInputDialog(null, "Inserisci data finale (Formato yyyy-mm-dd)");
			if(Inizio!= null && Inizio.isEmpty()!=true && Fine!= null && Fine.isEmpty()!=true) 
			{
				if (Inizio.matches(pattern) && Fine.matches(pattern)) {
					if (Fine.compareTo(Inizio) >= 0) {
						return "SELECT nome, cognome,SUM(introito)AS introito, COUNT(idacquisto)AS vendite FROM dipendente_periodo WHERE data_acquisto BETWEEN '"+Inizio+"'  AND '"+Fine+"' GROUP BY nome, cognome";
					} else
						JOptionPane.showMessageDialog(null, "Periodo non valido");
				} else
					JOptionPane.showMessageDialog(null, "Date non valide");
			}else break;
		} while (true);
		return null;	
	}
}
