package Matrix;

public interface IMatrix {


	public DenseMatrix Add(DenseMatrix a, DenseMatrix b);
	public  DenseComplexMatrix Add(DenseComplexMatrix a, DenseMatrix b);
	public  DenseComplexMatrix Add(DenseComplexMatrix a, DenseComplexMatrix b);

	public  DenseMatrix Multiply(DenseMatrix a, DenseMatrix b);
	public  DenseComplexMatrix Multiply(DenseComplexMatrix a, DenseComplexMatrix b);
	public  DenseComplexMatrix Multiply(DenseComplexMatrix a, DenseMatrix b);
	
	
	public abstract void multiplyBy(DenseMatrix a);
}