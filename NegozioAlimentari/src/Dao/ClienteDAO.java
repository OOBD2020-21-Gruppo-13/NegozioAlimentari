package Dao;

import java.sql.SQLException;

public interface ClienteDAO {

	public int RicavoId() throws SQLException;
	
	public void Register(String Nome,String Cognome,String Password);
	
	public void CopiaDB();
}
