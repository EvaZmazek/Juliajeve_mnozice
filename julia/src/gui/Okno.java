package gui;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class Okno extends JFrame {
	
	private Platno platno;
	
	public Okno() {
		super();
//		this.getContentPane().add(new HydraApplet(), BorderLayout.CENTER);
		this.setTitle("Julia set");
		this.setLayout(new GridBagLayout());
		platno=new Platno(this);
		this.getContentPane().add(platno);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		platno.setBorder(new EmptyBorder(5, 5, 5, 5));
		platno.setLayout(new BorderLayout(0, 0));
	}
	
	public static void main(String[] args) {
		JFrame w = new Okno();
		w.pack();
		w.setVisible(true);
	}
}
