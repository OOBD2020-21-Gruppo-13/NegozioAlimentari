package DaoImplements;

import java.sql.*;
import java.util.*;

import ClassiDB.Cliente;
import Dao.ClienteDAO;
import Main.Starter;


public class ClienteDAOPostgres implements ClienteDAO {

	Connection con = null;
    ArrayList<Cliente> Clienti = new ArrayList<Cliente>();
    Starter Controller = null;

public ClienteDAOPostgres(Connection connection, Starter temp) { 
    this.con=connection;
    this.Controller= temp;
        
  }

@Override
public void CopiaDB() 
{
	try 
    {
    PreparedStatement st = con.prepareStatement("SELECT * FROM cliente ORDER BY idtessera");
    ResultSet rs = st.executeQuery();


    while(rs.next())
    {
         String Nome = rs.getString("nome");
         String Cognome = rs.getString("cognome");
         int id = rs.getInt("idtessera");
         Double Punti = rs.getDouble("puntifedelta");
         Double Saldo = rs.getDouble("saldo");

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

public boolean Register(String Nome,String Cognome,String Password)
{
    try {
	    PreparedStatement st = con.prepareStatement("INSERT INTO cliente VALUES (?,?,?,0,?,?)");
	    st.setInt(1,RicavoId());
	    st.setString(2,Nome);
	    st.setString(3,Cognome);
	    st.setString(4,Password);
	    st.setDouble(5,Controller.Random(1000));
	    st.executeUpdate();
	    return true;
    }catch(SQLException e) 
    {
        System.out.println(e);
        return false;
    }
}

public int Login(int Username,String Password) throws SQLException 
{
	PreparedStatement st = con.prepareStatement("SELECT * FROM cliente WHERE idtessera = ? AND password = ? ");
	st.setInt(1, Username);
	st.setString(2, Password);
	ResultSet rs = st.executeQuery();
	if(rs.next()) 
	{
		System.out.println("Sei loggato con successo");
		return Username;
	}else return 0;
}

public ArrayList<Cliente> getClienti() {
	return Clienti;
}

public void setClienti(ArrayList<Cliente> clienti) {
	Clienti = clienti;
}




	

}
