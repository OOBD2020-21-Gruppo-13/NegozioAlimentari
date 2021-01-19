package Dao;

import java.sql.SQLException;
import java.util.List;

import ClassiDB.Prodotto;

public interface ProdottoDAO {

	public List<Prodotto> CopiaDB() throws SQLException;

}
