package batalhanaval.util;

public class Validador {
	
	public boolean validar_Coordenadas_Embarcacao(int tamanhoEmbarcacao,int x1,int y1,int x2,int y2){
		//System.out.println("DADOS: "+tamanhoEmbarcacao+" ,"+x1+" ,"+y1+" ,"+x2+" ,"+y2);
		if(x1!=x2 && y1!=y2){
			return false;
		}
		else if(x1<0 || x2<0 || y1<0 || y2<0){
			return false;
		}
		else if(x1>14 || x2>14 || y1>14 || y2>14){
			return false;
		}
		return true;
	}
	public boolean validar_tiro(int x,int y){
		if (x < 0 || x > 14 || y < 0 || y > 14) {
			return false;
		}
		return true;
	}
	public boolean validar_entrada(String[] entrada){
		try{
			if(entrada.length()==2){
				int x=Integer.parseInt(entrada[0]);
				int y=Integer.parseInt(entrada[1]);
				return true;
			}else{
				return false;
			}
			
		}catch(NumberFormatException e){
			return false;
		}
	}

}
