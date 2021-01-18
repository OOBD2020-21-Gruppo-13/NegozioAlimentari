package Main;
import java.sql.*;
import java.util.*;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

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
    int IdLogin;
   
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
		Negozio.getProfiloLabel().setText("Ciao "+DAO2.getClienti().get(IdLogin-1).getNome()+" il tuo Saldo è di "+ DAO2.getClienti().get(IdLogin-1).getSaldo()+"€");
	}
	public void AccendiCarrello(){
		Negozio.setVisible(false);
		Carrello = new CarrelloGui(this);
		Carrello.setVisible(true);
	}
	public void RiempiDAO()
	{
		DAO1.CopiaDB();
		DAO2.CopiaDB();
		DAO3.CopiaDB();
		System.out.println(DAO1.getDipendenti());
		System.out.println(DAO2.getClienti());
		System.out.println(DAO3.getMagazzino());
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
		this.DAO2.getClienti().get(IdLogin-1).getCarrello().removeAll(this.DAO2.getClienti().get(IdLogin-1).getCarrello());
		this.RemoveDAO();
		Negozio.setVisible(false);
		Login.setVisible(true);
	}
	public void RemoveDAO() {
		DAO1.getDipendenti().removeAll(DAO1.getDipendenti());
		DAO2.getClienti().removeAll(DAO2.getClienti());
		DAO3.getMagazzino().removeAll(DAO3.getMagazzino());
	}
	public boolean LoginButtonGui(String Username, String Password) {
		if(Username!= null && Username.isEmpty()!=true && Password!= null && Password.isEmpty()!=true) {
			try {
				if(this.getDAO2().Login(Integer.parseInt(Username), Password) > 0) {
					this.RiempiDAO();
					this.IdLogin = this.getDAO2().Login(Integer.parseInt(Username), Password);
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
		for(ClassiDB.Prodotto e : this.DAO3.getMagazzino()) 
		{
			Object o[] = {e.getNome(),e.getPrezzo(),e.getQuantita(),e.getDataProdRacc(),e.getDataScadenza(),e.getDataMungitura(),e.getTipo(),e.getIdProdotto()};
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
		ClassiDB.Prodotto p = new ClassiDB.Prodotto (quantita,id,this.DAO3.getMagazzino().get(id-1).getPrezzo(),this.DAO3.getMagazzino().get(id-1).getNome());
		this.DAO2.getClienti().get(this.IdLogin-1).getCarrello().add(p);
		this.DAO3.getMagazzino().get(id-1).setQuantita(this.DAO3.getMagazzino().get(id-1).getQuantita()-quantita);
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
        int decisone = JOptionPane.showConfirmDialog(null,"Vuoi aggiungere "+this.DAO3.getMagazzino().get(id-1).getNome()+" al carrello?","Aggiungi carrello",JOptionPane.YES_NO_OPTION);
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
                }else if(quantita<=this.DAO3.getMagazzino().get(id-1).getQuantita()) {
                    return quantita;
                }else {
                    JOptionPane.showMessageDialog(null, "Hai inserito una quantita non valida");
                }
            }else break;
        } while (true);
        return 0;
    }
	public int getIdLogin() {
		return IdLogin;
	}
	public void setIdLogin(int idLogin) {
		IdLogin = idLogin;
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
	
}
