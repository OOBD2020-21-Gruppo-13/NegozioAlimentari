package ClassiDB;
import java.util.ArrayList;

public class Cliente {

	private String Nome,Cognome;
	private int IdTessera;
	private double Saldo;
	private double PuntiFedelta;
	private ArrayList<Prodotto> Carrello = new ArrayList<Prodotto>();
	
	public Cliente(String Nome, String Cognome, int IdTessera, double Saldo, double PuntiFedelta) {
		this.Nome = Nome;
		this.Cognome = Cognome;
		this.IdTessera = IdTessera;
		this.Saldo = Saldo;
		this.PuntiFedelta = PuntiFedelta;
	}
	
	public String getNome() {
		return Nome;
	}
	public void setNome(String nome) {
		Nome = nome;
	}
	public String getCognome() {
		return Cognome;
	}
	public void setCognome(String cognome) {
		Cognome = cognome;
	}
	public int getIdTessera() {
		return IdTessera;
	}
	public void setIdTessera(int idTessera) {
		IdTessera = idTessera;
	}
	public double getPuntiFedelta() {
		return PuntiFedelta;
	}
	public void setPuntiFedelta(double puntiFedelta) {
		PuntiFedelta = puntiFedelta;
	}
	public double getSaldo() {
		return Saldo;
	}
	public void setSaldo(double saldo) {
		this.Saldo = saldo;
	}
	public ArrayList<Prodotto> getCarrello() {
		return Carrello;
	}
	public void setCarrello(ArrayList<Prodotto> carrello) {
		Carrello = carrello;
	}
	
}
