package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static DBConnection instance;
    private Connection connection = null;

    private DBConnection() throws SQLException {
        try
        {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://kandula.db.elephantsql.com/zytyplng?user=zytyplng&password=ZEO-23R6FOcUUp44Lyoj0VizFHMFERl0");
        }
        catch(ClassNotFoundException e)
        {
            System.out.println("Classe non trovata: \n"+e);
        }
        catch(SQLException e) 
        {
            System.out.println("SQL Exception: \n"+e);
        }

    }

    public Connection getConnection() {
        return connection;
    }

    public static DBConnection getInstance() throws SQLException {
        if (instance == null)
        {
            instance = new DBConnection();
        }
        else
            if (instance.getConnection().isClosed())
            {
                instance = new DBConnection();
            }

        return instance;
    }
}

