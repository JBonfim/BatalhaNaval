package batalhanaval.main;

import java.util.HashMap;
import java.util.Map;

import batalhanaval.io.Persistence;
import batalhanaval.io.PersistenciaException;
import batalhanaval.model.Embarcacao;
import batalhanaval.model.Tabuleiro;
import batalhanaval.ui.BatalhaNavalUI;

public class BatalhaNavalFacade {
	

	//Declaração das variaveis
	private Persistence persistence;
	private BatalhaNavalUI navalUI;
	private int ALTURA = 15;
	private int LARGURA = 15;
	
	private Map<String, Tabuleiro>  tabuleiros;
	
	public BatalhaNavalFacade() {
		this.persistence = new Persistence();
		this.tabuleiros = new HashMap<String, Tabuleiro>();
		this.navalUI = new BatalhaNavalUI();
	}
	
	public Map<String, int[]> carregarEmbarcao(String jogador) throws PersistenciaException{
		return persistence.readerEmbarcaJogador(jogador);
	}
	public void addJogadores(String jogador){
		this.tabuleiros.put(jogador, new Tabuleiro(LARGURA, ALTURA));
	}
	public Map<String, Tabuleiro> getTabuleiros(){
		return tabuleiros;
	}
	public void addEmbarcacao(String jogador,Embarcacao e,int x1, int y1, int x2, int y2){
		this.tabuleiros.get(jogador).add(e, x1, y1, x2, y2);
	}
	public void adicionarVidas(String jogador,int vd){
		this.tabuleiros.get(jogador).setVidas(vd);
	}
	public int quantidade_de_vida(String jogador){
		return this.tabuleiros.get(jogador).getVida();
	}
	public String atirar(String jogador,int x,int y){
		return this.tabuleiros.get(jogador).atirar(x, y);
	}
	
	//metodos referentes a entrada e saida de dados do jogo.
	public String entrada_de_dados(){
		return navalUI.entrada_de_dados();
	}
	
	public void exibir_MSG_na_tela(String msg){
		this.navalUI.exibir_mensagem(msg);
	}
	
	
	

}
