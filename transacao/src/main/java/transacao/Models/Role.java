package transacao.Models;

public enum Role {

	ADMIN("ADMIN"),
	USER("USER");
	
	private String nome;
	
	Role(String nome){
		this.nome = nome;
	}
	
	public String getNome() {
		return this.nome;
	}
}
