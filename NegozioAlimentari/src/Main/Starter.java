package Main;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import ClassiDB.*;
import DaoImplements.*;
import Database.DBConnection;
import Gui.*;

public class Starter {

	private DBConnection Dbconn;
	private Connection Connection;
	private LoginGui Login;
	private RegisterGui Register;
	private NegozioGui Negozio;
	private CarrelloGui Carrello;
	private AdminGui Admin;
	private DipendenteDAOPostgres DAODipendente;
	private ClienteDAOPostgres DAOCliente;
	private ProdottoDAOPostgres DAOProdotto;
	private AcquistiDAOPostgres DAOAcquisti;
	private List<Prodotto> Magazzino;
	private Cliente IlCliente;
	private Dipendente IlDipendente;
	private Acquisto Acquisto; 
   
	public Starter() throws SQLException 
	{
		Dbconn = DBConnection.getInstance();
        Connection = Dbconn.getConnection();
        DAODipendente = new DipendenteDAOPostgres(Connection, this);
	    DAOCliente = new ClienteDAOPostgres(Connection, this);
	    DAOProdotto = new ProdottoDAOPostgres(Connection, this);
	    DAOAcquisti = new AcquistiDAOPostgres(Connection, this);
        AccendiPrimaGui();
	}
	public static void main(String[] args) throws SQLException 
	{
		new Starter();	
	}
	public void AccendiPrimaGui(){
		Login= new LoginGui(this);
		Login.setLocationRelativeTo(null);
		Login.setVisible(true);
	}
	public void AccendiRegister() {
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
		String Input =JOptionPane.showInputDialog("Inserisci password per accendere");
        if(Input!= null && Input.isEmpty()!=true ) 
        {
            if(Input.equals("admin")) 
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
	public int Random(int Range) 
    {
        Random Rand = new Random();
        return(1+Rand.nextInt(Range));
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
				if(DAOCliente.Login(Integer.parseInt(Username), Password)) 
				{
					Magazzino = DAOProdotto.CopiaDB();
					IlCliente = DAOCliente.CopiaDB(Integer.parseInt(Username));
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
			if(DAOCliente.Register(Nome, Cognome, Password)) {
				JOptionPane.showMessageDialog(null, "Ti è stata assegnata la tessera numero: "+ String.valueOf(DAOCliente.RicavoId()-1) +" per accedere usa questo numero","Registrazione effettuata",JOptionPane.PLAIN_MESSAGE);
				Register.setVisible(false);
				AccendiPrimaGui();
				return true;
			}else return false;		
		}
	return false;
	}
	public void RiempiTabellaNegozio(DefaultTableModel x) 
	{
		for(Prodotto e : Magazzino) 
		{
			Object o[] = {e.getNome(),e.getPrezzo(),e.getQuantita(),e.getDataProdRacc(),e.getDataScadenza(),e.getDataMungitura(),e.getTipo(),e.getIdProdotto()};
			x.addRow(o);
		}	
	}
	public void RiempiTabellaCarrello(DefaultTableModel x) 
    {
        for(Prodotto e : IlCliente.getCarrello()) 
        {
            Object o[] = {e.getNome(),e.getPrezzo(),e.getQuantita(),e.getIdProdotto()};
            x.addRow(o);
        }
    }
	public void RiempiTabellaAdmin(DefaultTableModel x,String Table,String Parziale) throws SQLException 
    {
        x.setDataVector(DAODipendente.RiempiDati(Table, Parziale), DAODipendente.RiempiColonne(Parziale));
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
	public void RinominaColonna(int Index,String Nome,JTable t) 
	{
		t.getTableHeader().getColumnModel().getColumn(Index).setHeaderValue(Nome);
		t.getTableHeader().repaint();
	}
	public void InserisciProdottoCarrello(int Id,int Quantita) 
	{
		Prodotto p = new Prodotto (Quantita,Id,Magazzino.get(Id-1).getPrezzo(),Magazzino.get(Id-1).getNome());
        if(UnisciQuantitaProdotto(Quantita, Magazzino.get(Id-1).getIdProdotto())==false) {
            IlCliente.getCarrello().add(p);
        }
        Magazzino.get(Id-1).setQuantita(Magazzino.get(Id-1).getQuantita()-Quantita);
	}
	public boolean UnisciQuantitaProdotto(int Quantita,int IdProdotto) 
    {
        if((IlCliente.getCarrello().isEmpty())!=true) 
        {
            for(Prodotto Temp: IlCliente.getCarrello()) 
            {
            	if(IdProdotto == Temp.getIdProdotto()) 
                {
            		Temp.setQuantita(Temp.getQuantita()+Quantita);
                    return true;
                }
            }
        }
        return false;
    }
	public void SorterColonna(String Tipo,int Index,JTable Table,TableRowSorter<DefaultTableModel> Sorter) 
	{
		Table.setRowSorter(Sorter);
		Sorter.setRowFilter(RowFilter.regexFilter(Tipo, Index));
	}
	public void InserimentoCarrello(JTable Table) 
    {
        int Index = Table.getSelectedRow();
        int Id = (int) Table.getValueAt(Index, 7);
        int Decisione = JOptionPane.showConfirmDialog(null,"Vuoi aggiungere "+Magazzino.get(Id-1).getNome()+" al carrello?","Aggiungi carrello",JOptionPane.YES_NO_OPTION);
        if(Decisione == 0) 
        {
            int Quantita = ChiediQuantita(Id);
            if(Quantita>0)
            InserisciProdottoCarrello(Id, Quantita);
        }
    }
    public int ChiediQuantita(int Id) 
    {
        String Temp=null;
        int Quantita=0;
        do 
        {
            Temp = JOptionPane.showInputDialog(null,"Inserisci quantita");
            if(Temp!= null && Temp.isEmpty()!=true) 
            {
                try 
                {
                    Quantita = Integer.parseInt(Temp);
                }catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Hai inserito dei valori sbagliati");
                    break;
                }
                if(Quantita<=0) 
                {
                    JOptionPane.showMessageDialog(null, "Hai inserito una quantita non valida");
                }else if(Quantita<=Magazzino.get(Id-1).getQuantita()) {
                    return Quantita;
                }else {
                    JOptionPane.showMessageDialog(null, "Hai inserito una quantita non valida");
                }
            }else break;
        } while (true);
        return 0;
    }
    public void ResetTable(DefaultTableModel x) 
    {
    	int Righe = x.getRowCount();
    	if(Righe>0) 
    	{
    		for(int a=Righe;a>0;a--) 
    		{
    			x.removeRow(a-1);
    		}
    	}
    }
    public void RimuoviElementoCarello(int Id,DefaultTableModel x) 
	{
    	int Quantita=0;
		do 
		{
			String Temp = JOptionPane.showInputDialog(null,"Inserisci quantita da eliminare");
			if(Temp!= null && Temp.isEmpty()!=true) 
			{
				try {
					Quantita = Integer.parseInt(Temp);
				}catch (NumberFormatException e){
					JOptionPane.showMessageDialog(null, "Hai inserito dei valori sbagliati");
					break;
				}
				if(Quantita<=IlCliente.getCarrello().get(Id).getQuantita() && Quantita>0) 
				{
					int Idprodotto = (int) x.getValueAt(Id, 3);
					Magazzino.get(Idprodotto-1).setQuantita(Magazzino.get(Idprodotto-1).getQuantita()+Quantita);
					IlCliente.getCarrello().get(Id).setQuantita(IlCliente.getCarrello().get(Id).getQuantita()-Quantita);
					if(IlCliente.getCarrello().get(Id).getQuantita()==0) 
					{
						IlCliente.getCarrello().remove(Id);
					}
					ResetTable(x);
					RiempiTabellaCarrello(x);
					break;
				}else JOptionPane.showMessageDialog(null, "Hai inserito una quantita non valida");
			}else break;
		} while (true);
	}
    public double CalcoloCarrello() 
	{
    	double x=0;
		for(Prodotto p: IlCliente.getCarrello()) 
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
                int Decisione = JOptionPane.showConfirmDialog(null,"Il tuo saldo è: "+IlCliente.getSaldo()+"€ a fronte di: " +CalcoloCarrello()+"€","Vuoi pagare?",JOptionPane.YES_NO_OPTION);
                if(Decisione == 0) 
                {
                	Double Prezzo = CalcoloCarrello();
                    if(IlCliente.getSaldo()>=Prezzo) 
                    {
	                    IlDipendente = DAODipendente.CopiaDB(Random(DAODipendente.NumeroDipendente()));
	                    CreazioneDBAcquisto(Prezzo);
	                    JOptionPane.showMessageDialog(null, "Pagamento avvenuto con successo\nIl tuo ordine è stato portato a termine da: "+IlDipendente.getNome()+" "+IlDipendente.getCognome()+"\nArriverderci e torna a trovarci!");
	                    Reboot();
                    }else JOptionPane.showMessageDialog(null, "Non hai abbastanza soldi, rimuovi qualche prodotto dal carrello");
                }
            }catch (SQLException e1) {
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
    	Acquisto = new Acquisto(IlCliente.getCarrello(), IlCliente, IlDipendente, DAOAcquisti.RicavoIdAcquisto(), prezzo, CalcoloPuntiTotale());
        DAOAcquisti.SalvaAcquisto(Acquisto);
        IlCliente.getCarrello().removeAll(IlCliente.getCarrello());
    }
    public String ChiediData() 
	{
		String Pattern = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[1-9])|(1[0-2]))\\:([0-5][0-9])((\\s)|(\\:([0-5][0-9])\\s))([AM|PM|am|pm]{2,2})))?$";
		String Inizio = null;	 		
		String Fine = null; 	 		
		do 
		{
			Inizio = JOptionPane.showInputDialog(null, "Inserisci data iniziale (Formato yyyy-mm-dd)");
			Fine = JOptionPane.showInputDialog(null, "Inserisci data finale (Formato yyyy-mm-dd)");
			if(Inizio!= null && Inizio.isEmpty()!=true && Fine!= null && Fine.isEmpty()!=true) 
			{
				if (Inizio.matches(Pattern) && Fine.matches(Pattern)) 
				{
					if (Fine.compareTo(Inizio) >= 0) 
					{
						return "SELECT nome, cognome,SUM(introito)AS introito, COUNT(idacquisto)AS vendite FROM dipendente_periodo WHERE data_acquisto BETWEEN '"+Inizio+"'  AND '"+Fine+"' GROUP BY nome, cognome";
					} else JOptionPane.showMessageDialog(null, "Periodo non valido");
				} else JOptionPane.showMessageDialog(null, "Date non valide");
			}else break;
		} while (true);
		return null;	
	}
}
