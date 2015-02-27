package Matrix;

public class MatrixTest {
	public static void main(String args[]) throws Throwable{
		int n = 1;
		
		
		SparseMatrix a = new SparseMatrix(2,2);
		ComplexMatrix ab= new ComplexMatrix(1,2);
		
		a.setReElement(0, 0, 2);
		a.setReElement(1, 0, 1);
		a.setReElement(0, 1, 4);
		a.setReElement(1, 1, -1);

		a.setImElement(0, 1, -2);
		a.setImElement(1, 1, 3);

		ab.setReElement(0, 0, -1);
		ab.setReElement(0, 1, -4);


		ab.setImElement(0, 0, 3);
		ab.setImElement(0, 1, 2);


		System.out.println("a");
		a.convert();

		a.print();
		System.out.println("ab");

		ab.printReMatrix();
		System.out.println("aab");

		Matrix.Multiply(ab, a).printMatrix();
	}
}