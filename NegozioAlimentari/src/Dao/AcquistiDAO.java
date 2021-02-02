package Dao;

import java.sql.SQLException;
import ClassiDB.Acquisto;

public interface AcquistiDAO {

	
	public boolean SalvaAcquisto(Acquisto a) throws SQLException;

	public int RicavoIdAcquisto() throws SQLException;
}
