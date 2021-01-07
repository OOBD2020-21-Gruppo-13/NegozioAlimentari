package ClassiDB;
import java.util.*;

public class Cliente {

	private String Nome,Cognome;
	private int IdTessera;
	private double saldo;
	private double PuntiFedelta;

	public Cliente(String nome, String cognome, int idTessera, double saldo, double puntiFedelta) {
		super();
		Nome = nome;
		Cognome = cognome;
		IdTessera = idTessera;
		this.saldo = saldo;
		PuntiFedelta = puntiFedelta;
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


	@Override
	public String toString() {
		return "Cliente [Nome=" + Nome + ", Cognome=" + Cognome + ", IdTessera=" + IdTessera + ", saldo=" + saldo
				+ ", PuntiFedelta=" + PuntiFedelta + "]";
	}	
}
