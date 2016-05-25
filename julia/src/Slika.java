import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Slika extends JPanel{

	double realinput;
	double imaginput;

	double realpart; 
	double imagpart;  
	double btemp;
	boolean diverge;
	int i;
	static String mnozica;
	
//	za Mandelbrot
    private final int MAX_ITER = 570;
    private final double ZOOM = 150;
    private BufferedImage I;
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
			System.out.println("rišem Juliajevo množico :) jupi");
		}
		if(mnozica=="Mandelbrotova"){
			sprehodMandelbrotova(g);
			System.out.println("rišem Mandelbrotovo množico :)) jeej");
		}
		if(mnozica=="IFS"){
			sprehodIFS(g);
			System.out.println("rišem IFS množico :))) juhu");
		}



	}

	private void sprehodJulia(Graphics g) {
		for(int l=1;l< dimensions;l+=1){
			for(int j=1;j< dimensions;j+=1){
				double a = pretvoriX(l);
				double b = pretvoriY(j);
				g.setColor(Color.CYAN);
				i = 0;
				while (i < 100) {
					btemp = 2*a*b;
					a = a*a - b*b + realjuliaconst;
					b = btemp + imagjuliaconst;
					if ( (a*a + b*b) > 4 ) {diverge=true; break;} 
					i++;
				} 
				if (diverge==true) {
					if (i<20) 
						g.setColor(Color.white);
					if (i>19 && i<40) 
						g.setColor(Color.ORANGE);
					if (i>39 && i<60) 
						g.setColor(Color.red);
					if (i>59 && i<80) 
						g.setColor(Color.orange);
					if (i>79) 
						g.setColor(Color.RED);
					g.drawRect(l, j, 0, 0);
				}
			}
		}
	}

	private void sprehodMandelbrotova(Graphics g) {
		
//		for(int l=1;l< dimensions;l+=1){
//			for(int j=1;j< dimensions;j+=1){
//				double a = pretvoriX(l);
//				double b = pretvoriY(j);
//				g.setColor(Color.CYAN);
//				i = 0;
//				while (i < 100) {
//					btemp = 2*a*b; 
//					a = a*a - b*b + realjuliaconst;
//					b = btemp + imagjuliaconst;
//					if ( (a*a + b*b) > 4 ) {diverge=true; break;} 
//					i++;
//				}
//				if (diverge==true) {
//					if (i<20) 
//						g.setColor(Color.black);
//					if (i>19 && i<40) 
//						g.setColor(Color.gray);
//					if (i>39 && i<60) 
//						g.setColor(Color.DARK_GRAY); 
//					if (i>59 && i<80) 
//						g.setColor(Color.GRAY);
//					if (i>79) 
//						g.setColor(Color.darkGray);
//					g.drawRect(l, j, 0, 0);
//				}             
//			}
//		}
		for (int y = 0; y < dimensions; y++) {
            for (int x = 0; x < dimensions; x++) {
                zx = zy = 0;
                cX = pretvoriX((x)); // ZOOM;
                cY = pretvoriY((y)); // ZOOM;
                int iter = MAX_ITER;
                while (zx * zx + zy * zy < 4 && iter > 0) {
                    tmp = zx * zx - zy * zy + cX;
                    zy = 2.0 * zx * zy + cY;
                    zx = tmp;
                    iter--;
                }
                g.setColor(Color.black);
                if(iter<8){
                g.drawRect(x, y, 1, 1);
            }}
            }
	}

	private void sprehodIFS(Graphics g) {
		for(int l=1;l< dimensions;l+=20){
			for(int j=1;j< dimensions;j+=20){
				g.setColor(Color.CYAN);
				g.drawRect(l, j, 10, 10);
			}
		}
	}

	public Double razdalja_na_kvadrat(Complex x, Complex y){
		Double a = x.re() - y.re();
		Double b = x.im() - y.im();
		return Math.abs(a)+Math.abs(b);
	}

	public double pretvoriX(int x){
		return (((double)x*3)/dimensions)-1.5;
	}

	public double pretvoriY(int y){
		return -pretvoriX(y);
	}
}
