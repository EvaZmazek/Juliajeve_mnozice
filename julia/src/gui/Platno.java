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
		double x = okno.getWidth()-30;
		double y = okno.getHeight()-30;
		System.out.println(x);
		System.out.println(y);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		System.out.println(width);
		System.out.println(height);
		double a = Math.max(300, Math.min(x, y));
		double b = Math.max(300, Math.min(width, height));
		double c = Math.min(a, b);
		return new Dimension((int) c, (int)c); //hydra.dimension.height));
	}
}
