package ClassiDB;

public class Acquisto {

	private int IdAcquisto,IdCliente,IdDipendente;
	private Double PrezzoTotale;

	
	public Acquisto(int idAcquisto, int idCliente, int iddipendente,Double prezzototale) {
		super();
		IdAcquisto = idAcquisto;
		IdCliente = idCliente;
		PrezzoTotale = prezzototale;
		IdDipendente = iddipendente;
	}


	public int getIdAcquisto() {
		return IdAcquisto;
	}


	public void setIdAcquisto(int idAcquisto) {
		IdAcquisto = idAcquisto;
	}


	public int getIdCliente() {
		return IdCliente;
	}


	public void setIdCliente(int idCliente) {
		IdCliente = idCliente;
	}


	public int getIdDipendente() {
		return IdDipendente;
	}


	public void setIdDipendente(int idDipendente) {
		IdDipendente = idDipendente;
	}


	public Double getPrezzoTotale() {
		return PrezzoTotale;
	}


	public void setPrezzoTotale(Double prezzoTotale) {
		PrezzoTotale = prezzoTotale;
	}


	@Override
	public String toString() {
		return "Acquisto [IdAcquisto=" + IdAcquisto + ", IdCliente=" + IdCliente + ", IdDipendente=" + IdDipendente
				+ ", PrezzoTotale=" + PrezzoTotale + "]";
	}	
}
