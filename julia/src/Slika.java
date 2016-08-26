import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Slika extends JPanel{
	
	public int potenca(int koliko, int na) {
		int i = 0;
		int stevilo = 1;
		while (i<na) {
			stevilo = stevilo * koliko;
			i += 1;
		};
		return stevilo;
	};
	
// Parametri:
	static String mnozica;
	static Color barva;
	static int xkomponenta;
	static int ykomponenta;
	static int steviloKlikov;
	static int xmin;
	static int ymin;
	static int sirina;
	


	protected static int dimensions;

	//	za Juliajevo
	static double re; 
	static double im;  
	double btemp;
	boolean diverge;
	int i;	
	protected final int maxJuliajeva = 570;

	//	za Mandelbrot
	private final int maxMandelbrot = 570;
	private double zx, zy, cX, cY, tmp;
	
	//  za IFS
	protected final int maxIFS = 10500;
//...........................................................................
	

// Getters / Setters:
	public static Color getBarva() {
		return barva;
	}

	public static void setBarva(Color barva) {
		Slika.barva = barva;
	}

	public String getMnozica() {
		return mnozica;
	}

	public static void setMnozica(String mnozica) {
		Slika.mnozica = mnozica;
	}

	public static int getDimensions() {
		return dimensions;
	}	
	
	public static void setDimensions(int d) {
		dimensions = d;
	}

	public static double getRe() {
		return re;
	}

	public static void setRe(double realjuliaconst) {
		Slika.re = realjuliaconst;
	}

	public static double getIm() {
		return im;
	}

	public static void setIm(double imagjuliaconst) {
		Slika.im = imagjuliaconst;
	}
	
	public static int getXkomponenta() {
		return xkomponenta;
	}

	public static void setXkomponenta(int xkomponenta) {
		Slika.xkomponenta = xkomponenta;
	}

	public static int getYkomponenta() {
		return ykomponenta;
	}

	public static void setYkomponenta(int ykomponenta) {
		Slika.ykomponenta = ykomponenta;
	}
	
	public static int getSteviloKlikov() {
		return steviloKlikov;
	}

	public static void setSteviloKlikov(int steviloKlikov) {
		Slika.steviloKlikov = steviloKlikov;
	}
	
	public static int getXmin() {
		return xmin;
	}

	public static void setXmin(int xmin) {
		Slika.xmin = xmin;
	}

	public static int getYmin() {
		return ymin;
	}

	public static void setYmin(int ymin) {
		Slika.ymin = ymin;
	}

	public static int getSirina() {
		return sirina;
	}

	public static void setSirina(int sirina) {
		Slika.sirina = sirina;
	}
//.......................................................................................................



	public Slika() {
		super();
	}
	
	// Funkciji, ki pretvarjata piksel v koordinato
	public double pretvoriX(int xxx){
		return ((double)(xxx*3*sirina/dimensions)/dimensions)-(1.5*sirina/dimensions);
	}

	public double pretvoriY(int yyy){
		return -pretvoriX(yyy);
	}
	
	public double pretvoriXM(int x){
		return ((double)(x+sirina/2)*3/dimensions-1.5) ;
	}

	public double pretvoriYM(int y){
		return -pretvoriXM(y);
	}
	
	
	// Inverzni funkciji, ki ju uporabimo pri IFS fraktalih:
	public int pikselX(double x){
		return (int) Math.floor((x+1.5)*((double) dimensions*dimensions/sirina)/15) + dimensions*dimensions/sirina/3;
	}

	public int pikselY(double y){
		return dimensions*dimensions/sirina - (int) Math.floor((y + 1.5)*((double) dimensions*dimensions/sirina)/15);
	}
	
	
	// Funkcija, ki zgenerirano sliko nariše.
	public void paint(Graphics g){
		if(mnozica == null){
			g.drawString("Navodila:", 20, 20);
			g.drawString("* izberi množico, ki jo želiš prikazati", 20, 40);
			g.drawString("* pritisni gumb nariši", 20, 60);
			g.drawString("* če želiš izbrati barvo slike, stisni gumb barvaj in izberi barvo", 20, 80);
			g.drawString("* če želiš shraniti svojo slikico, stisni gumb shrani in poimenuj svojo sliko", 20, 100);
			g.drawString("Fraktali:", 20, 220);
			g.drawString("Madelbrotova množica je v množica točk "
					+ "v kompleksni ravnini, ", 40, 240);
			g.drawString("katere meja tvori fraktal. To je množica kompleksnih vrednosti c,", 40, 260);
			g.drawString("za katere orbita vrednosti 0 pod iteracijo kompleksnega kvadratnega", 40, 280);
			g.drawString("polinoma zn+1 = zn2 + c ostaja omejena.", 40, 300);
		}
		if(mnozica=="Juliajeva"){
			sprehodJulia(g);
		}
		if(mnozica=="Mandelbrotova"){
			sprehodMandelbrotova(g);
		}
		if(mnozica=="IFS"){
			sprehodIFS(g);
		}
	}
	
	
// Sprehodi: (funkcije, ki generirajo sliko za posamezen fraktal - kliče jih paint)
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
				double a = pretvoriX(l) + pretvoriXM(xmin);
				double b = pretvoriY(j) + pretvoriYM(ymin);
				i = 0;
				while (i < maxJuliajeva) {
					btemp = 2*a*b;
					a = a*a - b*b + re;
					b = btemp + im;
					if ( (a*a + b*b) > 4 ) {diverge=true; break;} 
					i++;
				} 				
				if (diverge==true) {
					if (i<20) 
						g.setColor(Color.white);
					if (i>19 && i<40) 
						g.setColor(new Color(R, G, B, alpha/3));
					if (i>39 && i<60) 
						g.setColor(new Color(R, G, B, alpha));
					if (i>59 && i<80) 
						g.setColor(new Color(R, G, B, alpha/6));
					if (i>79) 
						g.setColor(new Color(R, G, B, alpha));
					g.drawRect(l, j, 0, 0);
				}
			}
		}
		System.out.println(xmin + "          " + ymin);
	}
	
	

	private void sprehodMandelbrotova(Graphics g) {
		if(barva == null){
			barva = Color.black;
		}
		
		float R = barva.getRed()/255;
		float G = barva.getGreen()/255;
		float B = barva.getBlue()/255;
		float alpha = barva.getAlpha()/255;
		
		for (int y = 0; y < dimensions; y++) {
			for (int x = 0; x < dimensions; x++) {
				zx = zy = 0;
				cX = pretvoriX(x) + pretvoriXM(xmin);
				cY = pretvoriY(y) + pretvoriYM(ymin);
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
	

	//Definiramo množico z indeksi preslikav:
	Integer[] items = {1,2,3,4};
	
	
	// Napišemo funkcijo, ki vrača njihove uteži/verjetnosti, da bomo izvedli to preslikavo:
	private double utez(int i){
		if (i == 1) {return 0.01;}
		else if (i == 2) {return 0.85;}
		else if (i == 3) {return 0.07;}
		else if (i == 4) {return 0.07;}
		else {return 0;}
	}
	
	// Funkcija, ki žreba eno izmed preslikav in upošteva dane verjetnosti:
	private int zrebaj() {
		int rez = -1;
		double random = Math.random();
		for (int i : items){
		    random -= utez(i);
		    if (random <= 0.0d)
		    {
		        rez = i;
		        break;
		    }
		}
		return rez;	
	}	
	
	//Definicija preslikav:....................................................	
	protected double preslikava1x (double x, double y) {
		return 0.0;
	}
	
	protected double preslikava1y (double x, double y) {
		return 0.16*y;
	}
	
	protected double preslikava2x (double x, double y) {
		return 0.85*x + 0.04*y;
	}
	
	protected double preslikava2y (double x, double y) {
		return -0.04*x + 0.85*y +1.60;
	}
	
	protected double preslikava3x (double x, double y) {
		return 0.20*x - 0.26*y;
	}
	
	protected double preslikava3y (double x, double y) {
		return 0.23*x + 0.22*y +1.60;
	}
	
	protected double preslikava4x (double x, double y) {
		return -0.15*x + 0.28*y;
	}
	
	protected double preslikava4y (double x, double y) {
		return 0.26*x + 0.24*y +0.44;
	}
	//...........................................................................

	
	private void sprehodIFS(Graphics g) {
		g.setColor(barva);
		double zacetenX = 1.5;
		double zacetenY = 1.5;
		int rek = 0;
		while (rek < maxIFS) {
			g.drawRect(pikselX(zacetenX) + xmin, pikselY(zacetenY) + ymin, 1, 1);
			int i = zrebaj();
			if (i==1){
				zacetenX = preslikava1x(zacetenX, zacetenY);
				zacetenY = preslikava1y(zacetenX, zacetenY);
				g.drawRect(pikselX(zacetenX) + xmin , pikselY(zacetenY) + ymin, 1, 1);
			} else if (i==2){
				zacetenX = preslikava2x(zacetenX, zacetenY);
				zacetenY = preslikava2y(zacetenX, zacetenY);
				g.drawRect(pikselX(zacetenX) + xmin, pikselY(zacetenY) + ymin , 1, 1);
			} else if (i==3){
				zacetenX = preslikava3x(zacetenX, zacetenY);
				zacetenY = preslikava3y(zacetenX, zacetenY);
				g.drawRect(pikselX(zacetenX) + xmin , pikselY(zacetenY) + ymin , 1, 1);
			} else if (i==4){
				zacetenX = preslikava4x(zacetenX, zacetenY);
				zacetenY = preslikava4y(zacetenX, zacetenY);
				g.drawRect(pikselX(zacetenX) + xmin , pikselY(zacetenY) + ymin , 1, 1);
			}
			rek +=1;
		}
	}



}
