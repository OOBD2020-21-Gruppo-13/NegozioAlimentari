package Main;
import java.sql.*;
import java.util.*;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import ClassiDB.Cliente;
import ClassiDB.Dipendente;
import ClassiDB.Prodotto;
import DaoImplements.*;
import Database.DBConnection;
import Gui.CarrelloGui;
import Gui.LoginGui;
import Gui.NegozioGui;
import Gui.RegisterGui;

public class Starter {

	DBConnection dbconn = null;
    Connection connection = null;
    LoginGui Login;
    RegisterGui Register;
    NegozioGui Negozio;
    CarrelloGui Carrello;
    DipendenteDAOPostgres DAO1;
    ClienteDAOPostgres DAO2;
    ProdottoDAOPostgres DAO3;
    List<Prodotto> Magazzino;
    Cliente IlCliente;
    Dipendente IlDipendente;
   
	public Starter() throws SQLException 
	{
		dbconn = DBConnection.getInstance();
        connection = dbconn.getConnection();
        DAO1 = new DipendenteDAOPostgres(connection, this);
	    DAO2 = new ClienteDAOPostgres(connection, this);
	    DAO3 = new ProdottoDAOPostgres(connection, this);
        AccendiGui();
	}
	public static void main(String[] args) throws SQLException 
	{
		Starter s = new Starter();		
	}
	public void AccendiGui(){
		Login= new LoginGui(this);
		Login.setVisible(true);
	}
	public void AccediRegister() {
		Login.setVisible(false);
		Register = new RegisterGui(this);
		Register.setVisible(true);
	}
	public void AccendiNegozioInfoCliente() 
	{
		Login.setVisible(false);
		Negozio = new NegozioGui(this);
		Negozio.setVisible(true);
		Negozio.getProfiloLabel().setText("Ciao "+this.IlCliente.getNome()+" il tuo Saldo è di "+this.IlCliente.getSaldo()+"€");
	}
	public void AccendiCarrello(){
		Negozio.setVisible(false);
		Carrello = new CarrelloGui(this);
		Carrello.setVisible(true);
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
		this.IlCliente.getCarrello().removeAll(this.IlCliente.getCarrello());
		Negozio.setVisible(false);
		Login.setVisible(true);
	}
	public boolean LoginButtonGui(String Username, String Password) {
		if(Username!= null && Username.isEmpty()!=true && Password!= null && Password.isEmpty()!=true) {
			try {
				if(this.getDAO2().Login(Integer.parseInt(Username), Password) > 0) {
					this.Magazzino = this.getDAO3().CopiaDB();
					this.IlCliente = this.getDAO2().CopiaDB(Integer.parseInt(Username));
					this.AccendiNegozioInfoCliente();
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
			if(this.getDAO2().Register(Nome, Cognome, Password)) {
				Register.setVisible(false);
				this.AccendiGui();
				return true;
			}else return false;		
		}
	return false;
	}
	public void RiempiTabellaNegozio(DefaultTableModel x) 
	{
		for(ClassiDB.Prodotto e : this.Magazzino) 
		{
			Object o[] = {e.getNome(),e.getPrezzo(),e.getQuantita(),e.getDataProdRacc(),e.getDataScadenza(),e.getDataMungitura(),e.getTipo(),e.getIdProdotto()};
			x.addRow(o);
		}	
	}
	public void RiempiTabellaCarrello(DefaultTableModel x) 
    {
        for(ClassiDB.Prodotto e : this.IlCliente.getCarrello()) 
        {
            Object o[] = {e.getNome(),e.getPrezzo(),e.getQuantita(),e.getIdProdotto()};
            x.addRow(o);
        }
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
		ClassiDB.Prodotto p = new ClassiDB.Prodotto (quantita,id,this.Magazzino.get(id-1).getPrezzo(),this.Magazzino.get(id-1).getNome());
		this.IlCliente.getCarrello().add(p);
		this.Magazzino.get(id-1).setQuantita(this.Magazzino.get(id-1).getQuantita()-quantita);
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
        int decisone = JOptionPane.showConfirmDialog(null,"Vuoi aggiungere "+this.Magazzino.get(id-1).getNome()+" al carrello?","Aggiungi carrello",JOptionPane.YES_NO_OPTION);
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
                }else if(quantita<=this.Magazzino.get(id-1).getQuantita()) {
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
				System.out.println(temp);
				if(temp!= null && temp.isEmpty()!=true) 
				{
					try {
						quantita = Integer.parseInt(temp);
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null, "Hai inserito dei valori sbagliati");
						break;
					}
					if(quantita<=this.IlCliente.getCarrello().get(id).getQuantita()) 
					{
						int idprodotto = (int) x.getValueAt(id, 3);
						this.Magazzino.get(idprodotto-1).setQuantita(this.Magazzino.get(idprodotto-1).getQuantita()+quantita);
						this.IlCliente.getCarrello().get(id).setQuantita(this.IlCliente.getCarrello().get(id).getQuantita()-quantita);
						if(this.IlCliente.getCarrello().get(id).getQuantita()==0) 
						{
							this.IlCliente.getCarrello().remove(id);
						}
						this.RemoveTable(x);
						this.RiempiTabellaCarrello(x);
						break;
					}else {
							JOptionPane.showMessageDialog(null, "Hai inserito una quantita non valida");
					}
				}else break;
			} while (true);
	}
	public DipendenteDAOPostgres getDAO1() {
		return DAO1;
	}
	public ClienteDAOPostgres getDAO2() {
		return DAO2;
	}
	public ProdottoDAOPostgres getDAO3() {
		return DAO3;
	}
	public LoginGui getLogin() {
		return Login;
	}
	public void setLogin(LoginGui login) {
		Login = login;
	}
	public RegisterGui getRegister() {
		return Register;
	}
	public void setRegister(RegisterGui register) {
		Register = register;
	}
	public void PrintaCarrelloDebug() 
	{
		System.out.println(this.IlCliente.getCarrello());
	}
}
