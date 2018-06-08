package p1_semaforos;

import liliput.HoraDeComer;

public class PrincipalSemaforos {
	
	public static void main(String[] args){
		int S = 20;
		int N = 7;
		RecipienteSemaforos recipiente = new RecipienteSemaforos(S);
		HoraDeComer.ejecutar(recipiente, N);
	}

}
