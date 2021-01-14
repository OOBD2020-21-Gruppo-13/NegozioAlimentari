package ClassiDB;

public class Dipendente {

	private String Nome,Cognome;
	private int id;
	
	public Dipendente(String nome, String cognome, int id) {
		Nome = nome;
		Cognome = cognome;
		this.id = id;
	}

	@Override
	public String toString() {
		return "Dipendente [Nome=" + Nome + ", Cognome=" + Cognome + ", id=" + id + "]";
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	

}
