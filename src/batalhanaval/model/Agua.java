package batalhanaval.model;

public class Agua implements ItemTabuleiro{
	
	private String nome;

	public Agua() {
		this.nome = "AGUA";
	}

	public String levarTiro(){
		return nome;
	}
	public String getNome(){
		return nome;
	}
	
}
