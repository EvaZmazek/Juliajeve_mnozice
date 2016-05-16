package gui;

import java.awt.GridBagLayout;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Okno extends JFrame {
	
	private Platno platno;
	
	public Okno() {
//		this.getContentPane().add(new HydraApplet(), BorderLayout.CENTER);
		this.setTitle("Julia set");
		this.setLayout(new GridBagLayout());
		platno=new Platno(this);
		this.getContentPane().add(platno);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		JFrame w = new Okno();
		w.pack();
		w.setVisible(true);
	}
}
