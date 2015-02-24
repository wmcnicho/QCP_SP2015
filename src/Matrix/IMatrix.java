package Matrix;

public interface IMatrix {
	
	public DenseMatrix add(DenseMatrix a, DenseMatrix b);
	public  DenseComplexMatrix add(DenseComplexMatrix a, DenseMatrix b);
	public  DenseComplexMatrix add(DenseComplexMatrix a, DenseComplexMatrix b);

	public  DenseMatrix multiply(DenseMatrix a, DenseMatrix b);
	public  DenseComplexMatrix multiply(DenseComplexMatrix a, DenseComplexMatrix b);
	public  DenseComplexMatrix multiply(DenseComplexMatrix a, DenseMatrix b);
	
	public void multiplyBy(DenseMatrix a);
}