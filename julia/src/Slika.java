import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Slika extends JPanel{
	
	 double realinput;
	 double imaginput;
	 
	 double realpart; 
	 double imagpart; 
	 double realinc; 
	 double imaginc;  
	 double storerealpart; 
	 double storeimagpart;
	 double a;
	 double b;
	 double btemp;
	 boolean diverge;
	 int i;
	
	
	
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

	private Graphics2D h;
	Okno okno;

		
	public Slika() {
		super();
	}

	public void paint(Graphics g){
//		h = (Graphics2D) g;
//		System.out.println("Vlki sem " + dimensions);
//		h.drawRect(dimensions-100, dimensions-100, 20, 20);
//		sprehod();
		sprehod(g);


		
	}

	private void sprehod(Graphics g) {
		
		  realpart = -1.5;
		  imagpart = 1.5;
		  realinc = 0.01;
		  imaginc = 0.01;
		 
		  g.setColor(Color.white);
//		  g.drawString(realjuliaconst+" + "+imagjuliaconst+"i",160,42);
		 
		   realpart = realpart - realinc; // this is done once so realpart is at boundary on 1st pass
		   
		     for (int x = 0 ; x < dimensions; x++) {

		     realpart = realpart + realinc;
		     imagpart = 1.51;  // reset to top of window

		     for (int y = 0 ; y < dimensions; y++) {
				 double i=pretvoriX(x);
				 double j=pretvoriY(y);
				 
		       imagpart = imagpart - imaginc;
		 
		       storerealpart = realpart; // store current location in complex plane
		       storeimagpart = imagpart;
		       
		       a = realpart; 
		       b = imagpart; 
		       i = 0; 
		       diverge = false; // a and b are the current point
		 
		         while (i < 100) {
		           btemp = 2*a*b; // store this product otherwise it would also effect value of a
		           a = a*a - b*b + realjuliaconst;
		           b = btemp + imagjuliaconst;
		           if ( (a*a + b*b) > 4 ) {diverge=true; break;} 
		           i++;
		         }


		       if (diverge==true) {
						if (i<20) g.setColor(Color.gray);
		                                if (i>19 && i<40) g.setColor(Color.red);
		                                if (i>39 && i<60) g.setColor(Color.green);
		                                if (i>59 && i<80) g.setColor(Color.yellow);
		                                if (i>79) g.setColor(Color.white);
						g.drawRect(x, y, 0, 0);
		                           } 
		                           
		     
		      realpart = storerealpart; // restore value of current point
		      imagpart = storeimagpart;

		     } // end y loop

		   } // end x loop
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
	
	public void sprehod(){
//		canvas = new BufferedImage(sirina, sirina, BufferedImage.TYPE_INT_ARGB);
		double imkorak=0.01;
		double Rekorak=0.01;
		for(int i=1;i< dimensions;i+=10){
			for(int j=1;j< dimensions;j+=100){
				double a=pretvoriX(i);
				double b=pretvoriY(j);
				i = 0; 
			    diverge = false; // a and b are the current point
			 
			    while (i < 100) {
			    	btemp = 2*a*b; // store this product otherwise it would also effect value of a
			    	a = a*a - b*b + realjuliaconst;
			    	b = btemp + imagjuliaconst;
			    	if ( (a*a + b*b) > 4 ) {diverge=true; break;} 
			    	i++;
			    	}


			       if (diverge==true) {
							if (i<20) h.setColor(Color.gray);
			                                if (i>19 && i<40) h.setColor(Color.red);
			                                if (i>39 && i<60) h.setColor(Color.green);
			                                if (i>59 && i<80) h.setColor(Color.yellow);
			                                if (i>79) h.setColor(Color.white);
							h.drawRect(i, j, 3, 3);
			                           }
			}
		}
	}

}
