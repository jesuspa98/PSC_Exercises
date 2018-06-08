package p2_locks;

import liliput.HoraDeComer;

public class PrincipalLocks {
	
	public static void main(String[] args){
		int S = 20;
		int N = 7;
		RecipienteLocks recipiente = new RecipienteLocks(S);
		HoraDeComer.ejecutar(recipiente, N);
	}

}
