package simulator.mrep;

public class TestMatrix {
	public static void main (String [] args){
		DenseMatrix a = new DenseMatrix(2,2);
		a.setReElement(new double [] {8,2,3,6});
		a.setImElement(new double [] {1,7,9,2});
		
		DenseMatrix b = new DenseMatrix(2,2);
		b.setReElement(new double [] {3,4,5,1});
		b.setImElement(new double [] {1,6,2,7});
		
		b.preMultiplyBy(a);
		b.printMatrix();
	}
}
