package p2_monitores;

import liliput.HoraDeComer;

public class PrincipalMonitores {
	
	public static void main(String[] args){
		int S = 20;
		int N = 7;
		RecipienteMonitores recipiente = new RecipienteMonitores(S);
		HoraDeComer.ejecutar(recipiente, N);
	}

}
