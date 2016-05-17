package gui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

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
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		System.out.println(width);
		System.out.println(height);
		return new Dimension(
				(int) Math.min(width, height/1.5), //slika.dimension.width),
				(int) Math.max(200, height/1.5)); //hydra.dimension.height));
	}
}
