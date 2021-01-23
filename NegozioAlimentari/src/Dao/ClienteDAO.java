package Dao;

import java.sql.SQLException;
import java.util.ArrayList;

import ClassiDB.Cliente;
import ClassiDB.Prodotto;

public interface ClienteDAO {

	public int RicavoId() throws SQLException;
	
	public boolean Register(String Nome,String Cognome,String Password);
	
	public Cliente CopiaDB(int Id) throws SQLException;
	
	public void CreaAcquisto(int IdAcquisto, int IdCliente, Double PrezzoTotale, double PuntiTotale,ArrayList<Prodotto> Carrello) throws SQLException;
}
