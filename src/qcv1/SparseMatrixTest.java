package qcv1;

public class SparseMatrixTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		double[][] dou1 = {{0,2,3,0,3,0,0,0,0,5,0,0},{2,0,0,0,0,0,4,0,0,0,8,0}};
		double[][] dou2 = {{2,0,0,0},{0,4,0,0}};
	
		
		SparseMatrix m1 = new SparseMatrix(4, dou1);
		SparseMatrix m2 = new SparseMatrix(4, 1, dou2);
		
		System.out.println("Größe der Matrix: "+m1.getNrow()+"x"+m1.getNcol());
		
		m1.printMatrix();
		m2.printMatrix();
		
		m1.Rescale(2);
		m2.Rescale(0.5);
		
		SparseMatrix m3 = SparseMatrix.Multiply(m1, m2);

		m3.printMatrix();

	}

}
