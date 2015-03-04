package Matrix;

public class MatrixFactory {

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
