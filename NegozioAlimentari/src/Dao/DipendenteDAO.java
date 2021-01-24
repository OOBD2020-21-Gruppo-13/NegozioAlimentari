package Dao;

import java.sql.SQLException;

import ClassiDB.Dipendente;

public interface DipendenteDAO {

	public Dipendente CopiaDB(int Id) throws SQLException;
	
	public int NumeroDipendente() throws SQLException; 
}
