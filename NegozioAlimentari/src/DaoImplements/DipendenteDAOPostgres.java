package DaoImplements;

import java.sql.*;
import java.util.ArrayList;

import ClassiDB.Dipendente;
import Dao.DipendenteDAO;
import Main.Starter;

public class DipendenteDAOPostgres implements DipendenteDAO {

	Connection con = null;
	Starter Controller;
	ArrayList<Dipendente> Dipendenti = new ArrayList<Dipendente>();
	
public  DipendenteDAOPostgres(Connection connection,Starter temp) { 
	this.con=connection;
	this.Controller= temp;
    try 
    {										
    PreparedStatement st = con.prepareStatement("select * from dipendente order by iddipendente");
    ResultSet rs = st.executeQuery();

    while(rs.next())
    {
     	int id = rs.getInt("iddipendente");
     	String Nome = rs.getString("nome");
     	String Cognome = rs.getString("cognome");
     	
     	Dipendente d = new Dipendente(Nome, Cognome,id);
    	this.Dipendenti.add(d);
    }
    
    rs.close();
    st.close();
    
    }
    catch(SQLException e) 
    {
        System.out.println("SQL Exception: \n"+e);
    }
	
    
    
  }

public ArrayList<Dipendente> getDipendenti() {
	return Dipendenti;
}

public void setDipendenti(ArrayList<Dipendente> dipendenti) {
	Dipendenti = dipendenti;
}



}

