package qcv1;

public interface IMatrix {

	public void setElement(int i, int j, double value);
	public double getElement(int i, int j);
	
	public static DenseMatrix Add(DenseMatrix a, SparseMatrix b){
		return null;
	};
	public static DenseMatrix Add(SparseMatrix a, SparseMatrix b) {
		return null;
	}
	public static DenseMatrix Multiply(DenseMatrix a, DenseMatrix b) {
		return null;
	}
	public static DenseMatrix Multiply(DenseMatrix a, SparseMatrix b) {
		return null;
	}
	public static DenseMatrix Multiply(SparseMatrix a, SparseMatrix b) {
		return null;
	}
}