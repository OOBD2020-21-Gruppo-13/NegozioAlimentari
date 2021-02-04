package ClassiDB;

import java.sql.Date;

public class Prodotto {

	 private String Nome, Tipo;
	 private Double Prezzo;
	 private Integer Quantita,IdProdotto;
	 private Date DataScadenza;
	 private Date DataProdRacc;
	 private Date DataMungitura;

	public Prodotto(String Nome, String Tipo, Double Prezzo, int Quantita, int IdProdotto, Date DataScadenza,Date DataProdracc) {
		
		this.Nome = Nome;
		this.Tipo = Tipo;
		this.Prezzo = Prezzo;
		this.Quantita = Quantita;
		this.IdProdotto = IdProdotto;
		this.DataProdRacc = DataProdracc;
		this.DataScadenza = DataScadenza;
	}
	
	public Prodotto(int Quantita, int IdProdotto, double Prezzo,String Nome) {
		
		this.Quantita = Quantita;
		this.IdProdotto = IdProdotto;
		this.Prezzo= Prezzo;
		this.Nome=Nome;
	 }

	public String getNome() {
		return Nome;
	}
	public void setNome(String nome) {
		Nome = nome;
	}
	public String getTipo() {
		return Tipo;
	}
	public void setTipo(String tipo) {
		Tipo = tipo;
	}
	public Double getPrezzo() {
		return Prezzo;
	}
	public void setPrezzo(Double prezzo) {
		Prezzo = prezzo;
	}
	public Integer getQuantita() {
		return Quantita;
	}
	public void setQuantita(int quantita) {
		Quantita = quantita;
	}
	public Integer getIdProdotto() {
		return IdProdotto;
	}
	public void setIdProdotto(int idProdotto) {
		IdProdotto = idProdotto;
	}
	public Date getDataScadenza() {
		return DataScadenza;
	}
	public void setDataScadenza(Date dataScadenza) {
		DataScadenza = dataScadenza;
	}
	public Date getDataProdRacc() {
		return DataProdRacc;
	}
	public void setDataProdRacc(Date dataProdRacc) {
		DataProdRacc = dataProdRacc;
	}
	public void setQuantita(Integer quantita) {
		Quantita = quantita;
	}
	public void setIdProdotto(Integer idProdotto) {
		IdProdotto = idProdotto;
	}
	public Date getDataMungitura() {
		return DataMungitura;
	}
	public void setDataMungitura(Date dataMungitura) {
		DataMungitura = dataMungitura;
	}

}
