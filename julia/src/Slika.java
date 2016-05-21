import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Slika extends JPanel{
	
	public void paint(Graphics g){
		Graphics2D h=(Graphics2D) g;
		h.drawRect(100, 100, 2, 2);
	}
	
	public Double razdalja_na_kvadrat(Complex x, Complex y){
		Double a = x.re() - y.re();
		Double b = x.im() - y.im();
		return Math.abs(a)+Math.abs(b);
		}

}
