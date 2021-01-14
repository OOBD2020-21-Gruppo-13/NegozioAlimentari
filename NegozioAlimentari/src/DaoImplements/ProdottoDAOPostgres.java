package DaoImplements;
import java.sql.*;
import java.util.ArrayList;
import ClassiDB.Prodotto;
import Dao.ProdottoDAO;
import Main.Starter;

public class ProdottoDAOPostgres implements ProdottoDAO{

	Connection con=null;
	ArrayList<Prodotto> Magazzino = new ArrayList<>();
	Starter Controller= null;
	
public ProdottoDAOPostgres(Connection connection, Starter temp) { 
	this.con=connection;
	this.Controller=temp;
   
    try 
    {										
    PreparedStatement st = con.prepareStatement("SELECT * FROM prodotto ORDER BY idprodotto");
    ResultSet rs = st.executeQuery();

    while(rs.next())
    {
    	String nome=rs.getString("nome");
    	String tipo=rs.getString("Tipo");
    	double prezzo=rs.getDouble("prezzo");
    	int quantita=rs.getInt("quantitamag");
    	int idProdotto=rs.getInt("idprodotto");
    	Date dataScadenza=rs.getDate("datascadenza");
    	Prodotto p = new Prodotto(nome,tipo,prezzo,quantita,idProdotto,dataScadenza);

    	switch(tipo) 
    	{
			case "farinaceo","confezionato","uova":
				Date DataProduzione = rs.getDate("dataproduzione");
				p.setDataProduzione(DataProduzione);
				Magazzino.add(p);
				break;
			case "frutta","verdura":
				Date DataRaccolta = rs.getDate("dataraccolta");
				p.setDataRaccolta(DataRaccolta);
				Magazzino.add(p);
				break;
			case "latticino":
				Date DataProduzione1 = rs.getDate("dataproduzione");
				Date DataMungitura = rs.getDate("datamungitura");
				p.setDataMungitura(DataMungitura);
				p.setDataProduzione(DataProduzione1);
				Magazzino.add(p);
				break;
    	}	
    }
    
    rs.close();
    st.close();
   
    }
    catch(SQLException e) 
    {
        System.out.println("SQL Exception: \n"+e);
    }
	
  }

public ArrayList<Prodotto> getMagazzino() {
	return Magazzino;
}


	

}
