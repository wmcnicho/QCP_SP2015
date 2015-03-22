package Matrix;

public class MatrixFactory {

	/**
	 * Creates a matrix size (i,j) of the type specified by the string. Default case is real dense matrix.
	 * @param i number of rows
	 * @param j number of columns
	 * @param type string to specify the type of matrix, possible values are "complex", "sparse", "gate".
	 * @return	returns an empty matrix of specified size and type.
	 */
	@SuppressWarnings("deprecation")
	public static Matrix create(int i, int j, String type){
		switch(type){
		case "complex":
			return new ComplexMatrix(i, j);
		case "sparse":
			return new SparseMatrix(i, j);
		case "gate":
			return new SparseGateMatrix(i,j);
		default:
			return new RealMatrix(i,j);
		}
	}
}
