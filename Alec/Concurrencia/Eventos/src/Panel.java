package amigosc;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import amigosPublish.Amigos;

import java.util.*;
import java.util.List;
public class Panel extends JPanel{
	
	private JLabel etiqueta = new JLabel("cuántos amigos quieres?");
	private JLabel mensaje = new JLabel("GUI Creada");
	private JTextField numero = new JTextField(3);
	
	private JTextArea listaAmigos = new JTextArea(10,40);
	private JScrollPane scroll = new JScrollPane(listaAmigos);
	private JButton fin = new JButton("Cancelar");
	
	
	public Panel(){
		this.setLayout(new BorderLayout());
		JPanel norte = new JPanel();
		norte.add(etiqueta);norte.add(numero);
		norte.add(fin);
		this.add(BorderLayout.NORTH, norte);
	    this.add(BorderLayout.CENTER,  scroll);
	    this.add(BorderLayout.SOUTH,  mensaje);
	}
	
	public void controlador(ActionListener ctr){
		
	}
	public int numero() {
		return 0;
	}

	public void escribeAmigos(List<amigos.Amigos> list){
		
	}
	public void limpiaArea(){
		
	}
	public void mensaje(String str){
		
	}
	
}
