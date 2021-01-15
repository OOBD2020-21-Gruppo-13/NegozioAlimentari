package Dao;

import java.sql.SQLException;

public interface ClienteDAO {

	public int RicavoId() throws SQLException;
	
	public boolean Register(String Nome,String Cognome,String Password);
	
	public void CopiaDB();
}
