package batalhanaval.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import batalhanaval.util.TipoEmbarcacao;
import batalhanaval.util.Validador;

public class Persistence {
	private Validador validador;
	public Persistence() {
		this.validador = new Validador();
		// TODO Auto-generated constructor stub
	}
	
	
	
	@SuppressWarnings("resource")
	public Map<String, int[]> readerEmbarcaJogador(String jogador) throws PersistenciaException{
		File arquivoEmbarcacao = new File("Arquivos/"+jogador);
		Map<String, int[]> dadosembarcacoes = new HashMap<String, int[]>();
		FileInputStream stream = null;
		String msgErro = "";
		
		if(!arquivoEmbarcacao.exists()){
			//verifica se o arquivo existe, 
			msgErro = "Aquivo: "+jogador+", não existe";
			throw new PersistenciaException("Aquivo: "+jogador+", não existe");
		}
		
		try {
			stream = new FileInputStream(arquivoEmbarcacao);
			BufferedReader reader = new BufferedReader(	new InputStreamReader(stream));
			
			String linha;
			int qtlinha = 1;
			while ((linha = reader.readLine()) != null) {				
				StringTokenizer tokens = new StringTokenizer(linha," ");
				if(tokens.countTokens() != 5){
					msgErro = "Arquivo Corrompido";
					throw new PersistenciaException("Arquivo Corrompido");
				}
				
				String embarcacao = tokens.nextToken();
				int x1= Integer.parseInt(tokens.nextToken());
				int y1= Integer.parseInt(tokens.nextToken());
				int x2= Integer.parseInt(tokens.nextToken());
				int y2= Integer.parseInt(tokens.nextToken());
				
				if(!validador.validar_Coordenadas_Embarcacao(TipoEmbarcacao.valueOf(embarcacao).getVida(), x1, y1, x2, y2)){
					//valida as coordenadas
					msgErro = "ERRO LINHA "+qtlinha+": "+jogador;
					throw new PersistenciaException("ERRO LINHA "+qtlinha+": "+jogador);
					
					
				}
				int[] coordenadas = {x1,y1,x2,y2};
				dadosembarcacoes.put(embarcacao, coordenadas);
				qtlinha++;
				
			}
			reader.close();
			return dadosembarcacoes;
		} catch (Exception e ) {			
			throw new PersistenciaException(msgErro);
			
		}
		
			
			
		
		
		//return embarcacoes;
	}
	
}
