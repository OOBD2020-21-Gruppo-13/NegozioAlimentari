package Main;
import java.sql.*;

import DaoImplements.*;
import Database.DBConnection;

public class Starter {

	DBConnection dbconn = null;
    Connection connection = null;
    AcquistiDAOPostgres DAO1 = null;
    ClienteDAOPostgres DAO2 = null;
	
	public Starter() throws SQLException 
	{
		dbconn = DBConnection.getInstance();
        connection = dbconn.getConnection();
        DAO1 = new AcquistiDAOPostgres(connection);
        DAO2 = new ClienteDAOPostgres(connection);
	}
	
	public static void main(String[] args) throws SQLException 
	{
		Starter s = new Starter();
		System.out.println("Hello Database");
		System.out.println(s.DAO1.getAcquisti());
		System.out.println(s.DAO2.getClienti());
	}


	
}
