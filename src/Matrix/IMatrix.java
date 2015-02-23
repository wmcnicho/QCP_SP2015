package Matrix;

public interface IMatrix {


	public static DenseMatrix Add(DenseMatrix a, DenseMatrix b) {
		return null;
	}
	public static DenseComplexMatrix Add(DenseComplexMatrix a, DenseMatrix b) {
		return null;
	}
	public static DenseComplexMatrix Add(DenseComplexMatrix a, DenseComplexMatrix b) {
		return null;
	}

	public static DenseMatrix Multiply(DenseMatrix a, DenseMatrix b) {
		return null;
	}
	public static DenseComplexMatrix Multiply(DenseComplexMatrix a, DenseComplexMatrix b) {
		return null;
	}
	public static DenseComplexMatrix Multiply(DenseComplexMatrix a, DenseMatrix b) {
		return null;
	}
	
	
	public abstract void multiplyBy(DenseMatrix a);
}