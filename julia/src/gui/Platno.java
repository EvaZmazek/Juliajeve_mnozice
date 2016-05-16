package gui;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Platno extends JPanel {
	
	private Okno okno;
	
	public Platno(Okno glavno){
		super();
		this.setBackground(Color.gray);
		this.okno=glavno;
	}
	
	public Dimension getPreferredSize(){
		return new Dimension(
				Math.max(300, 900), //hydra.dimension.width),
				Math.max(200, 600)); //hydra.dimension.height));
	}
}
