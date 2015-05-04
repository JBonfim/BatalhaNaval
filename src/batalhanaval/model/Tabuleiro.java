package batalhanaval.model;

import batalhanaval.util.Validador;

public class Tabuleiro {
	private int largura;
	private int altura;
	private  ItemTabuleiro [][] tabuleiro;
	private Validador validador;
	private int vidas;
	
	public Tabuleiro(int lar, int alt){
		this.largura = lar;
		this.altura = alt;
		this.tabuleiro = new ItemTabuleiro [largura][altura];
		this.validador = new Validador();
		
		for(int x=0; x < tabuleiro.length; x++){
			for(int y=0; y <tabuleiro[0].length; y++){
				
				tabuleiro[x][y]= new Agua();
			}
		}
	}
	public void setVidas(int vd){
		this.vidas = vd;
	}
	public int getVida(){
		return vidas;
	}
	
	public String atirar(int x, int y){
		String tiro;
		
		if (!validador.validar_tiro(x, y)) { 
			tiro = "JOGADA INVALIDA";
		} else if (tabuleiro[x][y] == null) {
			tiro = "TIRO JÁ EXECUTADO";
		} else {
			tiro = tabuleiro[x][y].levarTiro();
			if(tiro.equals("AGUA")==false){
				Embarcacao e = (Embarcacao) tabuleiro[x][y];
				if(e.getTamanho()==0){
					tiro="AFUNDOU "+e.getNome();
				}
				tabuleiro[x][y] = null;
				vidas--;
			}
			
		}
		return tiro;
	}
	public void add(ItemTabuleiro item,int x1, int y1, int x2, int y2){
		Embarcacao e = (Embarcacao) item;
		int tamanho;
		if(x1!=x2 && y1==y2){
			if(x1>x2){
				tamanho = (x1-x2)+1;
				if(e.getTamanho()==tamanho){
					while(x1<=x2){
						tabuleiro[x1][y1]= e;
						x1++;
					}
				}else{
				}
			}
			if(x1<x2){
				tamanho = (x2-x1)+1;
				if(e.getTamanho()==tamanho){
					while(x1<=x2){
						tabuleiro[x1][y1]= e;
						x1++;
					}
				}else{
				}
			}
			
		}
		else if(y1!=y2 && x1==x2){
			if(y1>y2){
				tamanho = (y1-y2)+1;
				if(e.getTamanho()==tamanho){
					while(y1<=y2){
						tabuleiro[x1][y1]= e;
						y1++;
					}
				}else{
				}
			}
			if(y1<y2){
				tamanho = (y2-y1)+1;
				if(e.getTamanho()==tamanho){
					while(y1<=y2){
						tabuleiro[x1][y1]= e;
						y1++;
					}
				}else{
				}
			}
			
		}
	}
	
	
	

}
