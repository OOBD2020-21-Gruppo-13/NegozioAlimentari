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

public ArrayList<Cliente> getClienti() {
	return Clienti;
}

public void setClienti(ArrayList<Cliente> clienti) {
	Clienti = clienti;
}
	

}
