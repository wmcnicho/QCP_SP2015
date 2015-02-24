package complex;

import simulator.frep.Complex;

public class TestComplex {
	public static void main (String [] args){
		Complex z = new Complex(1,2);
		Complex w = new Complex(3,4);
		Complex.multiply(z, w).printComplex();
	}
}
