package concurrencia;


public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int Cap = 10;
		int N = 5;
		TableroTareas t = new TableroTareas(Cap);
		TipoA jefeA;
		TipoB jefeB;
		Trab tr[] =new Trab[N];
		for (int i = 0; i<tr.length; i++){
			tr[i] = new Trab(t,i);
		}
		
		
			jefeB = new TipoB(t,0);
			jefeA = new TipoA(t,1);
		
		for (int i = 0; i<tr.length; i++){
			tr[i].start();
		}
			jefeB.start();
			
			jefeA.start();
	}

}
