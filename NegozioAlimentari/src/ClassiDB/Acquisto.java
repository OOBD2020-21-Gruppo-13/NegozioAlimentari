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
	
	public Acquisto(List<Prodotto> Articoli, Cliente Acquirente, Dipendente Dipendente, int IdAcquisto, Double PrezzoTotale, Double PuntiTotale) {
		this.Articoli = Articoli;
		this.Acquirente = Acquirente;
		this.Dipendente = Dipendente;
		this.IdAcquisto = IdAcquisto;
		this.DataAcquisto = new java.sql.Date(System.currentTimeMillis());
		this.PrezzoTotale = PrezzoTotale;
		this.PuntiTotale = PuntiTotale;
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
