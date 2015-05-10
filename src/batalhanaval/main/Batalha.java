package batalhanaval.main;

import java.util.Map;

import batalhanaval.io.PersistenciaException;
import batalhanaval.model.Embarcacao;
import batalhanaval.ui.BatalhaNavalUI;
import batalhanaval.util.TipoEmbarcacao;
import batalhanaval.util.Validador;



public class Batalha {
	
	//Para iniciar o Jogo pelo prompt de comando basta digitar: java batalhanaval.main.Batalha j1.txt j2.txt
	
	private BatalhaNavalFacade facade;
	private BatalhaNavalUI ui;
	private Validador validador;
	private String JOGADOR1;
	private String JOGADOR2;
	
	
	public Batalha() {
		this.facade = new BatalhaNavalFacade();
		this.validador = new Validador();
		ui = new BatalhaNavalUI();
	}
	
	public void iniciarJogo(){
		ui.exibir_mensagem("\nIniciando as Jogadas ");
		boolean vez_j1 = true;
		boolean vez_j2 = false;
		boolean start_jogo = true;
		while(start_jogo){
			if(vez_j1){
				ui.exibir_mensagem("J1>");
				String[] entrada = ui.entrada_de_dados().split(" ");
				if(validador.validar_entrada(entrada)){
					int x=Integer.parseInt(entrada[0]);
					int y=Integer.parseInt(entrada[1]);
					String tiro =facade.atirar(JOGADOR2, x, y);
					if(tiro.equals("AGUA")){
						ui.exibir_mensagem(tiro);
						vez_j2=true;
						vez_j1=false;
						
					}
					else{
						if(facade.quantidade_de_vida(JOGADOR2)==0){
							ui.exibir_mensagem(tiro);
							ui.exibir_mensagem("FIM DE JOGO");
							ui.exibir_mensagem("VENCEDOR: "+JOGADOR1);
							start_jogo=false;
						}else{
							ui.exibir_mensagem(tiro);
						}
					}
				}else{
					ui.exibir_mensagem("JOGADA INVALIDA");
					vez_j2=false;
					vez_j1=true;
					
				}
				
				
			}else if(vez_j2){
				ui.exibir_mensagem("J2>");
				String[] entrada = ui.entrada_de_dados().split(" ");
				if(validador.validar_entrada(entrada)){
					int x=Integer.parseInt(entrada[0]);
					int y=Integer.parseInt(entrada[1]);
					String tiro =facade.atirar(JOGADOR1, x, y);
					if(tiro.equals("AGUA")){
						ui.exibir_mensagem(tiro);
						vez_j1=true;
						vez_j2=false;
						
					}
					else{
						if(facade.quantidade_de_vida(JOGADOR1)==0){
							ui.exibir_mensagem(tiro);
							ui.exibir_mensagem("FIM DE JOGO");
							ui.exibir_mensagem("VENCEDOR: "+JOGADOR2);
							start_jogo=false;
						}else{
							ui.exibir_mensagem(tiro);
						}
						
					}
					
				}else{
					ui.exibir_mensagem("JOGADA INVALIDA");
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
				ui.exibir_mensagem("TABULEIROS CARREGADOS COM SUCESSO \n");
				return true;
			}
		} catch (PersistenciaException e) {
			// TODO Auto-generated catch block
			ui.exibir_mensagem(e.getMessage());
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
			new BatalhaNavalUI().exibir_mensagem("Erro ao Iniciar o Jogo \n "
					+ "Verifique as Possiveis causas e corrija: \n 1- É necessario ter dois Jogadores \n 2- Para iniciar o Jogo pelo prompt de comando basta digitar: java batalhanaval.main.Batalha j1.txt j2.txt");
		}
		
		
	}

}
