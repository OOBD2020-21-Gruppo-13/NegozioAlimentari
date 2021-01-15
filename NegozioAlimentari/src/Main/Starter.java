package Main;
import java.sql.*;
import java.util.*;

import javax.swing.JOptionPane;

import DaoImplements.*;
import Database.DBConnection;
import Gui.LoginGui;
import Gui.NegozioGui;
import Gui.RegisterGui;

public class Starter {

	DBConnection dbconn = null;
    Connection connection = null;
    LoginGui Login;
    RegisterGui Register;
    NegozioGui Negozio;
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
		System.out.println("Hello Database");
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

	public void setRegister(RegisterGui register) 
	{
		Register = register;
	}
	
	public void AccendiNegozioInfoCliente() 
	{
		Login.setVisible(false);
		Negozio = new NegozioGui(this);
		Negozio.setVisible(true);
		Negozio.getProfiloLabel().setText("Ciao "+DAO2.getClienti().get(IdLogin-1).getNome()+" il tuo Saldo è di "+ DAO2.getClienti().get(IdLogin-1).getSaldo()+"€");
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

	public int getIdLogin() {
		return IdLogin;
	}

	public void setIdLogin(int idLogin) {
		IdLogin = idLogin;
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
}
