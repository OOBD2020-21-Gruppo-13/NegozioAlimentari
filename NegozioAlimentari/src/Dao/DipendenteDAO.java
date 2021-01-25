package Dao;

import java.sql.SQLException;

import ClassiDB.Dipendente;

public interface DipendenteDAO {

	public Dipendente CopiaDB(int Id) throws SQLException;
	
	public int NumeroDipendente() throws SQLException; 
	
	public int RicavaColonne(String table) throws SQLException;
	
	public int RicavaRighe(String table) throws SQLException;
	
	public Object[][] RiempiDati(String table,String parziale) throws SQLException;
	
	public Object[] RiempiColonne(String parziale) throws SQLException;
}
