package gui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import algoritmi.Slika;

@SuppressWarnings("serial")
public class Platno extends JPanel implements MouseMotionListener {
	
	private Okno okno;
	Slika slika;
	int sirina = 100;
	double x=this.getX();
	double y= this.getY();
	
	public Platno(Okno glavno){
		super();
		this.setBackground(Color.gray);
		this.addMouseMotionListener(this); //da sprobavam koordinate
		this.okno=glavno;
//		this.setLayout(null);
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
		sirina = (int) c;
		return new Dimension((int) c, (int)c); //hydra.dimension.height));
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		//TODO
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		System.out.println(e.getX());
		System.out.println(e.getY());
		
	}
}
