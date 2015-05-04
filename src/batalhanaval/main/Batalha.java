package batalhanaval.main;

import java.util.Map;

import batalhanaval.io.PersistenciaException;
import batalhanaval.model.Embarcacao;
import batalhanaval.util.TipoEmbarcacao;
import batalhanaval.util.Validador;



public class Batalha {
	
	private BatalhaNavalFacade facade;
	private Validador validador;
	private String JOGADOR1;
	private String JOGADOR2;
	
	
	public Batalha() {
		this.facade = new BatalhaNavalFacade();
		this.validador = new Validador();
	}
	
	public void iniciarJogo(){
		facade.exibir_MSG_na_tela("\nIniciando as Jogadas ");
		boolean vez_j1 = true;
		boolean vez_j2 = false;
		boolean start_jogo = true;
		while(start_jogo){
			if(vez_j1){
				facade.exibir_MSG_na_tela("J1>");
				String[] entrada = facade.entrada_de_dados().split(" ");
				if(validador.validar_entrada(entrada)){
					int x=Integer.parseInt(entrada[0]);
					int y=Integer.parseInt(entrada[1]);
					String tiro =facade.atirar(JOGADOR2, x, y);
					if(tiro.equals("AGUA")){
						facade.exibir_MSG_na_tela(tiro);
						vez_j2=true;
						vez_j1=false;
						
					}
					else{
						if(facade.quantidade_de_vida(JOGADOR2)==0){
							facade.exibir_MSG_na_tela(tiro);
							facade.exibir_MSG_na_tela("FIM DE JOGO");
							facade.exibir_MSG_na_tela("VENCEDOR: "+JOGADOR1);
							start_jogo=false;
						}else{
							facade.exibir_MSG_na_tela(tiro);
						}
					}
				}else{
					facade.exibir_MSG_na_tela("JOGADA INVALIDA");
					vez_j2=false;
					vez_j1=true;
					
				}
				
				
			}else if(vez_j2){
				facade.exibir_MSG_na_tela("J2>");
				String[] entrada = facade.entrada_de_dados().split(" ");
				if(validador.validar_entrada(entrada)){
					int x=Integer.parseInt(entrada[0]);
					int y=Integer.parseInt(entrada[1]);
					String tiro =facade.atirar(JOGADOR1, x, y);
					if(tiro.equals("AGUA")){
						facade.exibir_MSG_na_tela(tiro);
						vez_j1=true;
						vez_j2=false;
						
					}
					else{
						if(facade.quantidade_de_vida(JOGADOR1)==0){
							facade.exibir_MSG_na_tela(tiro);
							facade.exibir_MSG_na_tela("FIM DE JOGO");
							facade.exibir_MSG_na_tela("VENCEDOR: "+JOGADOR2);
							start_jogo=false;
						}else{
							facade.exibir_MSG_na_tela(tiro);
						}
						
					}
					
				}else{
					facade.exibir_MSG_na_tela("JOGADA INVALIDA");
					vez_j2=true;
					vez_j1=false;
					
				}
			}
		}
		
		
	}
	public void carregar_dados(String jogador1,String jogador2){
		adicionar_Jogadores(jogador1+jogador2);
		if(carregar_dados_jogador(jogador1, jogador2)){
			//ao carregar todos os dados de cada jogador, inicia o jogo.
			iniciarJogo();
		}
		
	}
	public boolean carregar_dados_jogador(String aquivojogador1,String aquivojogador2){
		try {
			if(adicionar_embarcacao_jogador(aquivojogador1,JOGADOR1)&&adicionar_embarcacao_jogador(aquivojogador2,JOGADOR2)){
				facade.exibir_MSG_na_tela("TABULEIROS CARREGADOS COM SUCESSO \n");
				return true;
			}
		} catch (PersistenciaException e) {
			// TODO Auto-generated catch block
			facade.exibir_MSG_na_tela(e.getMessage());
			return false;
		}
		return false;
		
	}
	public void adicionar_Jogadores(String nomesJogadores){
		String[] nomes=nomesJogadores.split(".txt");
		for(int i =0;i<nomes.length;i++){
			this.facade.addJogadores(nomes[i]);
		}
		JOGADOR1 = nomes[0];
		JOGADOR2 = nomes[1];
		
	}
	
	public void infoJogo(){
		
	}
	public boolean adicionar_embarcacao_jogador(String aquivo,String jogador) throws PersistenciaException{
		Map<String, int[]> embacacoes = this.facade.carregarEmbarcao(aquivo);
		int total_de_vidas = 0;
		for (String embarcacao : embacacoes.keySet()) {
			int[] coordenadas = embacacoes.get(embarcacao);
			int tamanho = TipoEmbarcacao.valueOf(embarcacao).getVida();
			total_de_vidas+=tamanho;
			Embarcacao e = new Embarcacao(embarcacao, tamanho);
			facade.addEmbarcacao(jogador, e, coordenadas[0], coordenadas[1], coordenadas[2], coordenadas[3]);
		}
		facade.adicionarVidas(jogador, total_de_vidas);
		return true;
	}
	
	public static void main(String[] args) {
		if(args.length==2){
			Batalha bt = new Batalha();
			bt.carregar_dados(args[0],args[1]);
		}
		else{
			
		}
		
		
	}

}
