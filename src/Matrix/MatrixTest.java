package Matrix;

public class MatrixTest {
	public static void main(String args[]) throws Throwable{
		int n = 1;
		
				
		Matrix a = MatrixFactory.create(2,2, "sparse");
		Matrix ab= MatrixFactory.create(1, 2, "complex");
		
		a.setReElement(0, 0, 2);
		a.setReElement(1, 0, 1);
		a.setReElement(0, 1, 1);
		a.setReElement(1, 1, 1);
		
		a.setImElement(0, 1, 2);
		a.setImElement(1, 1, 3);
		a.setImElement(0, 0, 2);
		a.setImElement(1, 0, 1);
		
		ab.setReElement(0, 0, -1);
		ab.setReElement(0, 1, -4);


		ab.setImElement(0, 0, 3);
		ab.setImElement(0, 1, 2);


		System.out.println("a");
		((SparseMatrix) a).convert();
		a=((Matrix)a);
		//System.out.println(a.getReElement(1,0));
		System.out.println("ab");

		((ComplexMatrix)ab).printMatrix();
		
		System.out.println("aab");

		ab.multiplyBy(a);
		((ComplexMatrix)ab).printMatrix();
	}
}