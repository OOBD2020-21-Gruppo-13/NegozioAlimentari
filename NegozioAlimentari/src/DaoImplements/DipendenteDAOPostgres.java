package DaoImplements;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

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

@Override
public int RicavaColonne(String table) throws SQLException 
{
	PreparedStatement st = con.prepareStatement("SELECT Count(*) FROM INFORMATION_SCHEMA.Columns where TABLE_NAME = ? ");
	st.setString(1, table);
	
	ResultSet rs = st.executeQuery();
	rs.next();
	return rs.getInt(1);
}

@Override
public int RicavaRighe(String table) throws SQLException 
{
	String query ="SELECT COUNT(*) FROM ";
	Statement st = con.createStatement();															
	ResultSet rs = st.executeQuery(query+table);
	rs.next();
	return rs.getInt(1);
}

@Override
public Object[][] RiempiDati(String table,String parziale) throws SQLException 
{
	int righe = RicavaRighe(parziale);
	int colonne = RicavaColonne(parziale);
	Object[][] Dati = new Object[righe][colonne];
	
	Statement st = con.createStatement();
	ResultSet rs = st.executeQuery(table);
	
	int i=0;
	while(rs.next()) 
	{
		for(int j = 0; j<colonne; j++)
		    {
		    	Dati[i][j] = rs.getObject(j+1);
		    }
		i++;
	}
	
	if(Dati.length!=i) {
	Dati = Arrays.copyOf(Dati, i);}
	
	return Dati;
}

@Override
public Object[] RiempiColonne(String parziale) throws SQLException 
{
	int colonne = RicavaColonne(parziale);
	PreparedStatement st = con.prepareStatement("select column_name from information_schema.columns where table_name= ? ");
	st.setString(1, parziale);
	
	ResultSet rs = st.executeQuery();
	Object[] Colonne = new Object[colonne];
	
	int i=0;
	while(rs.next() && i<colonne) 
	{
		Colonne[i] = rs.getObject(1);
		i++;
	}
	return Colonne;
}

}

