package qcv1;

//a static class that performs complex algebra
public abstract class Complex {
	public static double [] add(double [] z1, double [] z2){
		return new double [] {z1[0] + z2[0], z1[1] + z2[1]};
	}
	public static double [] add(double re1, double im1, double re2, double im2){
		return new double [] {re1 + re2, im1 + im2};
	}
	
	public static double [] multiply(double [] z1, double [] z2){
		return new double [] {z1[0]*z2[0] - z1[1]*z2[1], z1[0]*z2[1] + z1[1]*z2[0]};
	}
	public static double [] multiply(double re1, double im1, double re2, double im2){
		return new double [] {re1*re2 - im1*im2, re1*im2 + im1*re2};
	}
	
	public static double [] multiply(double k, double [] z){
		return new double [] {k*z[0], k*z[1]};
	}
	public static double [] multiply(double k, double re, double im){
		return new double [] {k*re, k*im};
	}
	
	public static double magnitude(double [] z){
		return Math.sqrt(z[0]*z[0] + z[1]*z[1]);
	}
	public static double magnitude(double re, double im){
		return Math.sqrt(re*re + im*im);
	}
	public static double magSquare(double [] z){
		return z[0]*z[0] + z[1]*z[1];
	}
	public static double magSquare(double re, double im){
		return re*re + im*im;
	}
}
