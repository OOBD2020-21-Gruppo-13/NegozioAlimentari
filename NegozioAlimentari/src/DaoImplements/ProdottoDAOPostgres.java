package DaoImplements;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ClassiDB.Prodotto;
import Dao.ProdottoDAO;
import Main.Starter;

public class ProdottoDAOPostgres implements ProdottoDAO{

	private Connection Con=null;
	private Starter Controller= null;
	
	public ProdottoDAOPostgres(Connection connection, Starter temp) 
	{ 
		this.Con=connection;
		this.Controller=temp;
	}
	
	@Override
	public List<Prodotto> CopiaDB() throws SQLException
	{										
	    PreparedStatement st = Con.prepareStatement("SELECT * FROM prodotto ORDER BY idprodotto");
	    ResultSet rs = st.executeQuery();
	    ArrayList<Prodotto> Magazzino = new ArrayList<Prodotto>();
	    while(rs.next())
	    {
	    	String tipo = rs.getString("Tipo");
	    	Prodotto p = new Prodotto(rs.getString("nome"),tipo,rs.getDouble("prezzo"),rs.getInt("quantitamag"),rs.getInt("idprodotto"),rs.getDate("datascadenza"),rs.getDate("dataprodracc"));
	    	switch(tipo) 
	    	{
				case "Farinaceo","Confezionato","Uova","Frutta","Verdura":
					Magazzino.add(p);
					break;
				case "Latticino":
					p.setDataMungitura(rs.getDate("datamungitura"));
					Magazzino.add(p);
					break;
	    	}	
	    }
	    rs.close();
	    st.close();
	    return Magazzino;
	    }

	public Starter getController() {
		return Controller;
	}

	public void setController(Starter controller) {
		Controller = controller;
	} 

}
