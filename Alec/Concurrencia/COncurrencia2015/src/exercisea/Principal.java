package exercisea;

public class Principal {
	
	public static void main(String[] args){
		Bandeja b = new Bandeja();
		CNormal[] cNormal = new CNormal[5];
		CPremium[] cPremium = new CPremium[5];
		Reponedor r = new Reponedor(b);
		for (int i = 0; i<5; i++){
			cNormal[i] = new CNormal(i,b);
			cPremium[i] = new CPremium(i,b);
		}
		r.start();
		for (int i = 0; i<5; i++){
			cNormal[i].start();
			cPremium[i].start();
		}
	}

}
