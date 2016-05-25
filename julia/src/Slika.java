import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Slika extends JPanel{
	BufferedImage bi = new BufferedImage(dimensions+1, dimensions+1, BufferedImage.TYPE_INT_ARGB); 
	Graphics g = bi.createGraphics();
	double realinput;
	double imaginput;

	//	za Juliajevo
	double realpart; 
	double imagpart;  
	double btemp;
	boolean diverge;
	int i;	
	private final int maxJuliajeva = 570;

	static String mnozica;
	static Color barva;

	public static Color getBarva() {
		return barva;
	}

	public static void setBarva(Color barva) {
		Slika.barva = barva;
	}

	//	za Mandelbrot
	private final int maxMandelbrot = 570;
	private double zx, zy, cX, cY, tmp;

	public String getMnozica() {
		return mnozica;
	}

	public static void setMnozica(String mnozica) {
		Slika.mnozica = mnozica;
	}

	private static int dimensions;
	private static double realjuliaconst;
	private static double imagjuliaconst;

	public static int getDimensions() {
		return dimensions;
	}

	public static double getRealjuliaconst() {
		return realjuliaconst;
	}

	public static void setRealjuliaconst(double realjuliaconst) {
		Slika.realjuliaconst = realjuliaconst;
	}

	public static double getImagjuliaconst() {
		return imagjuliaconst;
	}

	public static void setImagjuliaconst(double imagjuliaconst) {
		Slika.imagjuliaconst = imagjuliaconst;
	}

	public static void setDimensions(int d) {
		dimensions = d;
	}


	public Slika() {
		super();
	}

	public void paint(Graphics g){
		if(mnozica=="Juliajeva"){
			sprehodJulia(g);
			g.dispose();
			try{ImageIO.write(bi,"png",new File("test.png"));}catch (Exception e) {}
		}
		if(mnozica=="Mandelbrotova"){
			sprehodMandelbrotova(g);
		}
		if(mnozica=="IFS"){
			sprehodIFS(g);
		}
	}

	private void sprehodJulia(Graphics g) {
		if(barva == null){
			barva = Color.yellow;
		}
		float R = barva.getRed()/255;
		float G = barva.getGreen()/255;
		float B = barva.getBlue()/255;
		float alpha = barva.getAlpha()/255;
		
		for(int l=1;l< dimensions;l+=1){
			for(int j=1;j< dimensions;j+=1){
				double a = pretvoriX(l);
				double b = pretvoriY(j);
				g.setColor(Color.white);
				i = 0;
				while (i < maxJuliajeva) {
					btemp = 2*a*b;
					a = a*a - b*b + realjuliaconst;
					b = btemp + imagjuliaconst;
					if ( (a*a + b*b) > 4 ) {diverge=true; break;} 
					i++;
				} 
				if (diverge==true) {
					if (i<20) 
						g.setColor(new Color(R,G,B,alpha/50));
					if (i>19 && i<40) 
						g.setColor(new Color(R,G,B,(float) (alpha/(1.1))));
					if (i>39 && i<60) 
						g.setColor(new Color(R/4,G/4,B/4,alpha));
					if (i>59 && i<80) 
						g.setColor(new Color(R,G,B,alpha/2));
					if (i>79) 
						g.setColor(new Color(R/6,G/6,B/6,alpha));
					g.drawRect(l, j, 0, 0);
				}
			}
		}
	}

	private void sprehodMandelbrotova(Graphics g) {
		if(barva == null){
			barva = Color.black;
		}
		
		float R = barva.getRed()/255;
		float G = barva.getGreen()/255;
		float B = barva.getBlue()/255;
		float alpha = barva.getAlpha()/255;
		
		System.out.println(barva.toString());
		for (int y = 0; y < dimensions; y++) {
			for (int x = 0; x < dimensions; x++) {
				zx = zy = 0;
				cX = pretvoriX((x));
				cY = pretvoriY((y));
				int iter = maxMandelbrot;
				g.setColor(Color.white);
				while (zx * zx + zy * zy < 4 && iter > 0) {
					tmp = zx * zx - zy * zy + cX;
					zy = 2.0 * zx * zy + cY;
					zx = tmp;
					iter--;
				}

				if(iter<8){
					g.setColor(new Color(0,0,0));
				}
				if(iter < 500 && iter >= 8){
					g.setColor(new Color(80,90,60));
				}
				if(iter < 550 && iter >= 500){
					g.setColor(new Color(180,167,127));
				}
				if(iter < 550 && iter >= 500){
					g.setColor(barva);
				}
					
				if(iter >=550){
					float vrednost=(float) ((Math.abs(alpha/(iter-549))));
					if(vrednost*3 < 1){
						vrednost = vrednost*3;
					g.setColor(new Color(R,G,B, vrednost));
				}}
				g.drawRect(x, y, 1, 1);
			}
		}
	}


	private void sprehodIFS(Graphics g) {
		for(int l=1;l< dimensions;l+=20){
			for(int j=1;j< dimensions;j+=20){
				g.setColor(Color.BLUE);
				g.drawRect(l, j, 10, 10);
			}
		}
	}

	
	public double pretvoriX(int x){
		return (((double)x*3)/dimensions)-1.5;
	}

	public double pretvoriY(int y){
		return -pretvoriX(y);
	}
}
