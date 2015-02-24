package simulator.frep;

//a class for complex numbers algebra
//		z = x = iy
public class Complex {
	
	private double x;
	private double y;
	
	public Complex(){
		this.x = 0.0;
		this.y = 0.0;
	}
	public Complex(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	//accessor methods
	public void setReal(double x){
		this.x = x;
	}
	public void setImag(double y){
		this.y = y;
	}
	public void set(Complex z){
		this.x = z.x;
		this.y = z.y;
	}
	public double getReal(){return x;}
	public double getImag(){return y;}
	public Complex get(){
		return new Complex(x,y);
	}
	public void printComplex(){
		System.out.printf("(%.2f, %.2f)\t",x,y);
	}
	
	//complex algebra
	public void add(Complex z){
		x += z.x;
		y += z.y;
	}
	public void multiply(double k){
		x *= k;
		y *= k;
	}
	public void multiply(Complex z){
		x = x*z.x - y*z.y;
		y = x*z.y + y*z.x;
	}
	public double amplitude(){
		return Math.sqrt(x*x+y*y);
	}
	public double ampSquare(){
		return x*x + y*y;
	}
	
	//class methods
	public static Complex add(Complex z1, Complex z2){
		return new Complex(z1.x + z2.x, z1.y + z2.y);
	}
	public static Complex multiply(Complex z1, Complex z2){
		return new Complex(z1.x * z2.x - z1.y * z2.y, z1.x * z2.y + z1.y * z2.x);
	}
	
	public static double [] add(double [] z1, double [] z2){
		return new double [] {z1[0] + z2 [0], z1[1] + z2[1]};
	}
	public static double [] multiply(double [] z1, double [] z2){
		return new double [] {z1[0] * z2[0] - z1[1] * z2[1], z1[0] * z2[1] + z1[1] * z2[0]};
	}
}
