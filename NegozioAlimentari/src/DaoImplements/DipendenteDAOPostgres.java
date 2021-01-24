package DaoImplements;

import java.sql.*;
import java.util.ArrayList;

import ClassiDB.Dipendente;
import Dao.DipendenteDAO;
import Main.Starter;

public class DipendenteDAOPostgres implements DipendenteDAO {

	Connection con = null;
	Starter Controller;
	
public  DipendenteDAOPostgres(Connection connection,Starter temp) { 
	this.con=connection;
	this.Controller= temp;   
  }

@Override
public Dipendente CopiaDB(int Id) throws SQLException
{
											
    PreparedStatement st = con.prepareStatement("select * from dipendente where iddipendente = ? ");
    st.setInt(1, Id);
    ResultSet rs = st.executeQuery();
    Dipendente d = null;

    while(rs.next())
    {
     	d = new Dipendente(rs.getString("nome"), rs.getString("cognome"),rs.getInt("iddipendente"));
    }
    
    rs.close();
    st.close();
    return d;
}

@Override
public int NumeroDipendente() throws SQLException 
{
    Statement st = con.createStatement();
    ResultSet rs = st.executeQuery("SELECT iddipendente FROM dipendente ORDER BY iddipendente DESC LIMIT 1");
    if(rs.next()) {
        return (rs.getInt("iddipendente"));
    }else return 0;
}

}

