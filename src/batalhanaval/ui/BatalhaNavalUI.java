package batalhanaval.ui;

import java.util.Scanner;

public class BatalhaNavalUI {
	
	public void exibir_mensagem(String msg){
		System.out.println(msg);
	}
	public String entrada_de_dados(){
		Scanner entrada = new Scanner(System.in);
		return entrada.nextLine();
	}
	
}
