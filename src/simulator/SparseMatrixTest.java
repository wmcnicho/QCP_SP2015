package simulator;

public class SparseMatrixTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		double[][] dou1 = {{0,2,3,0,3,0,0,0,0,5,0,0},{2,0,0,0,0,0,4,0,0,0,8,0}};
		double[][] dou2 = {{2,0,0,0},{0,4,0,0}};
	
		
		SparseMatrix m1 = new SparseMatrix(4, dou1);
		SparseMatrix m2 = new SparseMatrix(4, 1, dou2);
		
		System.out.println("Gre der Matrix: "+m1.getNrow()+"x"+m1.getNcol());
		
		System.out.println(m1.getRealPart(2,1));
		
		m1.printMatrix();
		m2.printMatrix();
		
		m1.rescale(2);
		m2.rescale(0.5);
		
		SparseMatrix m3 = SparseMatrix.Multiply(m1, m2);

		m3.printMatrix();

	}

}
