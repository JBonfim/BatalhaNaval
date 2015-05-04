package batalhanaval.teste;

import java.util.HashMap;
import java.util.Map;

import batalhanaval.io.Persistence;
import batalhanaval.io.PersistenciaException;

public class PercistenciaTeste {
	
	
	
	public static void main(String[] args) {
		Persistence persistence = new Persistence();
		try {
			Map<String, int[]> dadosembarcacoes = persistence.readerEmbarcaJogador("j1.txt");
			
			for (String key : dadosembarcacoes.keySet()) {
				int coordenadas[] = dadosembarcacoes.get(key);
				System.out.println("Embarcacao: "+key+" , Coordenadas: "+coordenadas[0]+" "+coordenadas[1]+" "+coordenadas[2]+" "+coordenadas[3]+" ");
			}
			
		} catch (PersistenciaException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
		}
	}

}
