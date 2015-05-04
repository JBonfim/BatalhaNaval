package batalhanaval.util;

public enum TipoEmbarcacao {
	
	PORTA_AVIOES(5),
	DESTROYER(4),
	CRUZADOR(4),
	SUBMARINO(3),
	PATRULHA(2);
	
	private int vida;
	
	TipoEmbarcacao(int vida) {
		this.vida = vida;
	}
	
	public int getVida() {
		return vida;
	}
	

}
