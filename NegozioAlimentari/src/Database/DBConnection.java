package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

public class DBConnection {

    private static DBConnection instance;
    private Connection connection = null;

    private DBConnection() throws SQLException {
        try
        {
            Class.forName("org.postgresql.Driver");
            System.out.println("Driver caricato corretamente");
            connection = DriverManager.getConnection("jdbc:postgresql://kandula.db.elephantsql.com/zytyplng?user=zytyplng&password=ZEO-23R6FOcUUp44Lyoj0VizFHMFERl0");
            System.out.println("Collegamento al database eseguito corretamente");
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

