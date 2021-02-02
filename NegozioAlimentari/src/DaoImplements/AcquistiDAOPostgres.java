package DaoImplements;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import ClassiDB.Acquisto;
import Dao.AcquistiDAO;
import Main.Starter;

public class AcquistiDAOPostgres implements AcquistiDAO {

	Connection con = null;
    Starter Controller = null;

public AcquistiDAOPostgres(Connection connection, Starter temp) { 
    this.con=connection;
    this.Controller= temp;     
  }
	
	@Override
	public int RicavoIdAcquisto() throws SQLException 
	{
	    Statement st = con.createStatement();
	    ResultSet rs = st.executeQuery("SELECT idacquisto FROM acquisto ORDER BY idacquisto DESC LIMIT 1");
	    if(rs.next()) {
	        return (rs.getInt("idacquisto")+1);
	    }else 
	    {
	        return 1;
	    }
	}

	@Override
	public boolean SalvaAcquisto(Acquisto a) throws SQLException 
	{
	    PreparedStatement st = con.prepareStatement("INSERT INTO acquisto VALUES (?,?,?,?,?,?)");
	    st.setInt(1, a.getIdAcquisto());
	    st.setInt(2, a.getAcquirente().getIdTessera());
	    st.setDouble(3, a.getPrezzoTotale());
	    st.setInt(4, a.getDipendente().getId());
	    st.setDouble(5, a.getPuntiTotale());
	    st.setDate(6, new java.sql.Date(System.currentTimeMillis()));
	    st.executeUpdate();
	    st.close();

	    PreparedStatement st1 = con.prepareStatement("INSERT INTO dettagli_acquisto VALUES (?,?,?,?,?)");
	    for(ClassiDB.Prodotto p : a.getArticoli()) 
	    {
	        st1.setInt(1, a.getIdAcquisto());
	        st1.setInt(2, p.getIdProdotto());
	        st1.setInt(3, p.getQuantita());
	        st1.setDouble(4, p.getPrezzo());
	        st1.setDouble(5, (Controller.Round(p.getPrezzo()*p.getQuantita()/10)));
	        st1.executeUpdate(); 
	    }
	    st1.close();
	    return true;
	}
}
