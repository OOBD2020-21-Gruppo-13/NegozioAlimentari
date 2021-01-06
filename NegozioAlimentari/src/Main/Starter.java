package Main;
import java.sql.*;

import DaoImplements.AcquistiDAOPostgres;
import Database.DBConnection;

public class Starter {

	DBConnection dbconn = null;
    Connection connection = null;
    AcquistiDAOPostgres DAO1 = null;
	
	public Starter() throws SQLException 
	{
		dbconn = DBConnection.getInstance();
        connection = dbconn.getConnection();
        DAO1 = new AcquistiDAOPostgres(connection);
	}
	
	public static void main(String[] args) throws SQLException 
	{
		Starter s = new Starter();
		System.out.println("Hello Database");
		System.out.println(s.DAO1.getAcquisti());
	}


	
}
