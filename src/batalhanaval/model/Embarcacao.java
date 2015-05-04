package batalhanaval.model;

public class Embarcacao implements ItemTabuleiro{
	private String nome;
	private int tamanho;

	
	public Embarcacao(String nome, int tamanho) {
		
		this.nome = nome;
		this.tamanho = tamanho;
	
	}	
	
	public Embarcacao() {
		// TODO Auto-generated constructor stub
	}
	
	public String levarTiro(){
		tamanho--;
		return "ACERTOU";
	}
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}
	public int getTamanho() {
		return tamanho;
	}
	
}
