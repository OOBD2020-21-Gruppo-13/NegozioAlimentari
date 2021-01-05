package Main;
import java.sql.*;
import Database.DBConnection;

public class Starter {

	DBConnection dbconn = null;
    Connection connection = null;
	
	public Starter() throws SQLException 
	{
		dbconn = DBConnection.getInstance();
        connection = dbconn.getConnection();
	}
	
	public static void main(String[] args) throws SQLException 
	{
		Starter s = new Starter();
		System.out.println("Hello Database");
	}


	
}
