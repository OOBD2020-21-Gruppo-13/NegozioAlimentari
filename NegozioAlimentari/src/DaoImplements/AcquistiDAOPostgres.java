package DaoImplements;

import java.sql.*;
import java.util.ArrayList;

import ClassiDB.Acquisto;
import Dao.AcquistiDAO;

public class AcquistiDAOPostgres implements AcquistiDAO {

	Connection con = null;
	ArrayList<Acquisto> Acquisti = new ArrayList<Acquisto>();
	
public  AcquistiDAOPostgres(Connection connection) { 
	this.con=connection;
    try 
    {										
    PreparedStatement st = con.prepareStatement("SELECT * FROM acquisto");
    ResultSet rs = st.executeQuery();

    while(rs.next())
    {
     	int IdA = rs.getInt("id");
     	int IdC = rs.getInt("idcliente");
     	int DP = rs.getInt("iddipendente");
     	Double IdP = rs.getDouble("prezzototale");
     	
     	Acquisto a = new Acquisto(IdA, IdC, DP, IdP);
    	this.Acquisti.add(a);
    }
    
    rs.close();
    st.close();
    
    }
    catch(SQLException e) 
    {
        System.out.println("SQL Exception: \n"+e);
    }
	
    
    
  }


public ArrayList<Acquisto> getAcquisti() {
	return Acquisti;
}


public void setAcquisti(ArrayList<Acquisto> acquisti) {
	Acquisti = acquisti;
}



}

