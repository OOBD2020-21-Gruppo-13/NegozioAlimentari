package ClassiDB;
import java.util.*;
import java.sql.Date;

public class Prodotto {

	 private String Nome, Tipo;
	 private Double Prezzo;
	 private Integer Quantita,IdProdotto;
	 private Date DataScadenza;
	 private Date DataRaccolta;
	 private Date DataProduzione;
	 private Date DataMungitura;

	
	
	 
	public Prodotto(int quantita, int idProdotto, double prezzo) {
		super();
		Quantita = quantita;
		IdProdotto = idProdotto;
		this.Prezzo= prezzo;
	}

	public Prodotto(String nome, String tipo, Double prezzo, int quantita, int idProdotto, Date dataScadenza) {
		super();
		Nome = nome;
		Tipo = tipo;
		Prezzo = prezzo;
		Quantita = quantita;
		IdProdotto = idProdotto;
		DataScadenza = dataScadenza;
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
	public Date getDataRaccolta() {
		return DataRaccolta;
	}
	public void setDataRaccolta(Date dataRaccolta) {
		DataRaccolta = dataRaccolta;
	}
	public Date getDataProduzione() {
		return DataProduzione;
	}
	public void setDataProduzione(Date dataProduzione) {
		DataProduzione = dataProduzione;
	}
	public Date getDataMungitura() {
		return DataMungitura;
	}
	public void setDataMungitura(Date dataMungitura) {
		DataMungitura = dataMungitura;
	}

	
}
