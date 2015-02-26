package simulator.mrep;

public class TestMatrix {
	public static void main (String [] args){
		/*DenseMatrix a = new DenseMatrix(2,2);
		a.setReal(new double [] {8,2,3,6});
		a.setImag(new double [] {1,7,9,2});
		
		DenseMatrix b = new DenseMatrix(2,2);
		b.setReal(new double [] {3,4,5,1});
		b.setImag(new double [] {1,6,2,7});
		
		b.preMultiplyBy(a);
		b.printMatrix();*/
		
		int a = (int) Math.pow(2, 20);
		int b = (int) Math.pow(2, 20);
		long c = (long)a * b;
		System.out.println(c);
	}
}
