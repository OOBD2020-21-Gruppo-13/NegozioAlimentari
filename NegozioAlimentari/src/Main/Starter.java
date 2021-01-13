package Main;
import java.sql.*;
import java.util.Scanner;

import DaoImplements.*;
import Database.DBConnection;
import Gui.LoginGui;
import Gui.RegisterGui;

public class Starter {

	DBConnection dbconn = null;
    Connection connection = null;
    LoginGui Login;
    RegisterGui Register;
    AcquistiDAOPostgres DAO1 = null;
    ClienteDAOPostgres DAO2 = null;
    ProdottoDAOPostgres DAO3 = null;
   
	
	public Starter() throws SQLException 
	{
		dbconn = DBConnection.getInstance();
        connection = dbconn.getConnection();
        DAO1 = new AcquistiDAOPostgres(connection, this);
        DAO2 = new ClienteDAOPostgres(connection, this);
        DAO3 = new ProdottoDAOPostgres(connection, this);
        AccendiGui();
	}
	
	public static void main(String[] args) throws SQLException 
	{
		Starter s = new Starter();
		Scanner tastiera = new Scanner(System.in);
		System.out.println("Hello Database");
		System.out.println(s.DAO1.getAcquisti());
		System.out.println(s.DAO2.getClienti());
		System.out.println(s.DAO3.getMagazzino());
		System.out.println(s.DAO2.RicavoId());
		System.out.println("Inserisci il nome: ");
		String nome = tastiera.next();
		System.out.println("Inserisci il cognome: ");
		String cognome = tastiera.next();
		System.out.println("Inserisci la password: ");
		String password = tastiera.next();
		s.DAO2.Register(nome, cognome, password);
		System.out.println("Inserisci il id: ");
		int Useraname = tastiera.nextInt();
		System.out.println("Inserisci il password: ");
		String Password = tastiera.next();
		s.DAO2.Login(Useraname, Password);
		
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
	
	public AcquistiDAOPostgres getDAO1() {
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
