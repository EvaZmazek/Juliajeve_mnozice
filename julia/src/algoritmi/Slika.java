package algoritmi;

import gui.Platno;

public class Slika {
	Platno platno;
	
	public Double razdalja_na_kvadrat(Complex x, Complex y){
		Double a = x.re() - y.re();
		Double b = x.im() - y.im();
		return Math.abs(a)+Math.abs(b);
		}
	
	public void pretvori(int x, int y){
		double a = platno.getWidth();
		System.out.println(a);
		}
}
