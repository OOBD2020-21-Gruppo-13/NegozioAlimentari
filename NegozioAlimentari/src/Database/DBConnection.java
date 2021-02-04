package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static DBConnection Instance;
    private Connection Connection = null;

    private DBConnection() throws SQLException 
    {
        try{
        	Class.forName("org.postgresql.Driver");
            Connection = DriverManager.getConnection("jdbc:postgresql://kandula.db.elephantsql.com/zytyplng?user=zytyplng&password=ZEO-23R6FOcUUp44Lyoj0VizFHMFERl0");
        }catch(ClassNotFoundException e){
            System.out.println("Classe non trovata: \n"+e);
        }catch(SQLException e){
            System.out.println("SQL Exception: \n"+e);
        }
    }

    public Connection getConnection() 
    {
        return Connection;
    }

    public static DBConnection getInstance() throws SQLException {
        if (Instance == null)
        {
            Instance = new DBConnection();
        }else if (Instance.getConnection().isClosed()){
        	Instance = new DBConnection();
        }

        return Instance;
    }
}

