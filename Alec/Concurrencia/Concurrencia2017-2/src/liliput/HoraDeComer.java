package liliput;

public class HoraDeComer {
	
	/**
	 * Este metodo toma un recipiente y un numero de cocineros y debe crear y lanzar un hilo
	 * para Gulliver y uno por cada uno de los N cocineros.
	 *   
	 * 
	 * @param recipiente El Recipiente que usaran Gulliver y los cocineros
	 * @param N          Cantidad de cocineros
	 */
	public static void ejecutar(Recipiente recipiente, int N) {
		Gulliver g = new Gulliver(recipiente);
		Cocinero[] cocineros = new Cocinero[N];
		for(int i = 0; i < N; i++) {
			cocineros[i] = new Cocinero(recipiente, i);
		}
		g.start();
		for(int i = 0; i < N; i++) {
			cocineros[i].start();
		}
	}
}
