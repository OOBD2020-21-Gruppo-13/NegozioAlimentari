package Main;
import java.sql.*;


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
    AcquistiDAOPostgres DAO1 = null;
    ClienteDAOPostgres DAO2 = null;
    ProdottoDAOPostgres DAO3 = null;
    int IdLogin;
   
	
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
		System.out.println("Hello Database");
		System.out.println(s.DAO1.getAcquisti());
		System.out.println(s.DAO2.getClienti());
		System.out.println(s.DAO3.getMagazzino());
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

	public int getIdLogin() {
		return IdLogin;
	}

	public void setIdLogin(int idLogin) {
		IdLogin = idLogin;
	}

}
