package Dao;

import java.sql.SQLException;

import ClassiDB.Cliente;

public interface ClienteDAO {

	public int RicavoId() throws SQLException;
	
	public boolean Register(String Nome,String Cognome,String Password);
	
	public Cliente CopiaDB(int Id) throws SQLException;

}
