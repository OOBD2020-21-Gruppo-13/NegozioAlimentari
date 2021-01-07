package DaoImplements;

import java.sql.*;
import java.util.*;

import ClassiDB.Cliente;
import Dao.ClienteDAO;


public class ClienteDAOPostgres implements ClienteDAO {

	Connection con = null;
    ArrayList<Cliente> Clienti = new ArrayList<Cliente>();

public ClienteDAOPostgres(Connection connection) { 
    this.con=connection;

    try 
    {
    PreparedStatement st = con.prepareStatement("SELECT * FROM cliente");
    ResultSet rs = st.executeQuery();


    while(rs.next())
    {
         String Nome = rs.getString("nome");
         String Cognome = rs.getString("cognome");
         int id = rs.getInt("idtessera");
         Double Punti = 10.7;
         Double Saldo = 500.25;

         Cliente c = new Cliente(Nome, Cognome, id,Saldo, Punti);
        this.Clienti.add(c);
    }


    rs.close();
    st.close();

    }
    catch(SQLException e) 
    {
        System.out.println("SQL Exception: \n"+e);
    }
        
  }

public int RicavoId() throws SQLException 
{
    Statement st = con.createStatement();
    ResultSet rs = st.executeQuery("SELECT idtessera FROM cliente ORDER BY idtessera DESC LIMIT 1");
    if(rs.next()) {
        return (rs.getInt("idtessera")+1);
    }else 
    {
        return 1;
    }
}

public ArrayList<Cliente> getClienti() {
	return Clienti;
}

public void setClienti(ArrayList<Cliente> clienti) {
	Clienti = clienti;
}


	

}
