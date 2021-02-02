package ClassiDB;

import java.sql.Date;
import java.util.List;

public class Acquisto {

	private List<Prodotto> Articoli;
	private Cliente Acquirente;
	private Dipendente Dipendente;
	private int IdAcquisto;
	private Date DataAcquisto;
	private Double PrezzoTotale, PuntiTotale;
	
	public Acquisto(List<Prodotto> articoli, Cliente acquirente, ClassiDB.Dipendente dipendente, int idAcquisto, Double prezzoTotale, Double puntiTotale) {
		Articoli = articoli;
		Acquirente = acquirente;
		Dipendente = dipendente;
		IdAcquisto = idAcquisto;
		DataAcquisto = new java.sql.Date(System.currentTimeMillis());
		PrezzoTotale = prezzoTotale;
		PuntiTotale = puntiTotale;
	}

	public List<Prodotto> getArticoli() {
		return Articoli;
	}
	public Cliente getAcquirente() {
		return Acquirente;
	}
	public Dipendente getDipendente() {
		return Dipendente;
	}
	public int getIdAcquisto() {
		return IdAcquisto;
	}
	public Date getDataAcquisto() {
		return DataAcquisto;
	}
	public Double getPrezzoTotale() {
		return PrezzoTotale;
	}
	public Double getPuntiTotale() {
		return PuntiTotale;
	}
	
}
