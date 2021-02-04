package ClassiDB;

public class Dipendente {

	private String Nome,Cognome;
	private int Id;
	
	public Dipendente(String Nome, String Cognome, int Id) {
		this.Nome = Nome;
		this.Cognome = Cognome;
		this.Id = Id;
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
		return Id;
	}
	public void setId(int id) {
		this.Id = id;
	}
}
