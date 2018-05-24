package amigosc;

import javax.swing.*;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.CancellationException;
public class Worker extends SwingWorker<java.util.List<Amigos>,Void>{

	private int n;
	private Panel panel;
	public Worker(int n,Panel panel){
		this.n = n; 
		this.panel = panel;
	}
	private boolean sumaDivisores(long a, long b){
		long suma = 0;
		int i = 1;
		while (i<a && suma <= b){
			if (a % i == 0) suma+=i;
			i++;
		}
		return suma == b;
	}
	
	private long sumaDivisores(long a){
		long suma = 1;
		int i = 2;
		while (i<a){
			if (a % i == 0) suma+=i;
			i++;
		}
		return suma;
	}
	@Override
	protected java.util.List<Amigos> doInBackground() throws Exception {
		// TODO Auto-generated method stub
		
		
		return null;
	}
	
	// tratar la excepcion CancellationException
	public void done(){
		
	}

}
