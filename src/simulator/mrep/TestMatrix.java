package simulator.mrep;

public class TestMatrix {
	public static void main (String [] args){
		SparseMatrix a = new SparseMatrix(2,2);
		a.set(0,new double [] {8,1});
		a.set(1,new double [] {2,7});
		a.set(2,new double [] {3,9});
		a.set(3,new double [] {6,2});
		
		SparseMatrix b = new SparseMatrix(2,2);
		b.set(0,new double [] {3,1});
		b.set(1,new double [] {4,6});
		b.set(2,new double [] {5,2});
		b.set(3,new double [] {1,7});
		
		b.preMultiplyBy(a);
		b.printMatrix();
		
	}
}
