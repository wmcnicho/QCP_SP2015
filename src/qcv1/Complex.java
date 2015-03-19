package qcv1;

/**
 * A static class that performs basic complex algebra. Each returned complex number
 * is stored as a double array (i.e. [real, imag])
 * @author Michael
 *
 */
public abstract class Complex {
	/**
	 * Add two complex numbers
	 * @param z1 Complex number z1
	 * @param z2 Complex number z2
	 * @return Sum of the two complex numbers
	 */
	public static double [] add(double [] z1, double [] z2){
		return new double [] {z1[0] + z2[0], z1[1] + z2[1]};
	}
	/**
	 * Add two complex numbers
	 * @param re1 Real part of complex number z1
	 * @param im1 Imaginary part of complex number z1
	 * @param re2 Real part of complex number z2
	 * @param im2 Imaginary part of complex number z2
	 * @return Sum of the two complex numbers
	 */
	public static double [] add(double re1, double im1, double re2, double im2){
		return new double [] {re1 + re2, im1 + im2};
	}
	
	/**
	 * Multiply two complex numbers
	 * @param z1 Complex number z1
	 * @param z2 Complex number z2
	 * @return Product of the two complex numbers
	 */
	public static double [] multiply(double [] z1, double [] z2){
		return new double [] {z1[0]*z2[0] - z1[1]*z2[1], z1[0]*z2[1] + z1[1]*z2[0]};
	}
	
	/**
	 * Multiply two complex numbers
	 * @param re1 Real part of complex number z1
	 * @param im1 Imaginary part of complex number z1
	 * @param re2 Real part of complex number z2
	 * @param im2 Imaginary part of complex number z2
	 * @return Product of the two complex numbers
	 */
	public static double [] multiply(double re1, double im1, double re2, double im2){
		return new double [] {re1*re2 - im1*im2, re1*im2 + im1*re2};
	}
	
	/**
	 * Multiply a complex number by a scalar factor
	 * @param k Scalar factor k
	 * @param z Complex number z
	 * @return The complex number z scaled by k
	 */
	public static double [] multiply(double k, double [] z){
		return new double [] {k*z[0], k*z[1]};
	}
	
	/**
	 * Multiply a complex number by a scalar factor
	 * @param k Scalar factor k
	 * @param re Real part of complex number z
	 * @param im Imaginary part of complex number z
	 * @return The complex number z scaled by k
	 */
	public static double [] multiply(double k, double re, double im){
		return new double [] {k*re, k*im};
	}
	
	/**
	 * Get the magnitude of a complex number
	 * @param z Complex number z
	 * @return The magnitude of z
	 */
	public static double magnitude(double [] z){
		return Math.sqrt(z[0]*z[0] + z[1]*z[1]);
	}
	
	/**
	 * Get the magnitude of a complex number
	 * @param re Real part of complex number z
	 * @param im Imaginary part of complex number z
	 * @return The magnitude of z
	 */
	public static double magnitude(double re, double im){
		return Math.sqrt(re*re + im*im);
	}
	
	/**
	 * Get the squared magnitude of a complex number
	 * @param z Complex number z
	 * @return The squared magnitude of z
	 */
	public static double magSquare(double [] z){
		return z[0]*z[0] + z[1]*z[1];
	}
	
	/**
	 * Get the squared magnitude of a complex number
	 * @param re Real part of complex number z
	 * @param im Imaginary part of complex number z
	 * @return The squared magnitude of z
	 */
	public static double magSquare(double re, double im){
		return re*re + im*im;
	}
}
