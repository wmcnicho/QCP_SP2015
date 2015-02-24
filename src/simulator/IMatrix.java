package simulator;

public interface IMatrix {

	public void setElement(int i, int j, double value);
	public double getElement(int i, int j);
	
	public DenseMatrix add(DenseMatrix a, SparseMatrix b);
	public DenseMatrix add(SparseMatrix a, SparseMatrix b);
	public DenseMatrix multiply(DenseMatrix a, DenseMatrix b);
	public DenseMatrix multiply(DenseMatrix a, SparseMatrix b);
	public DenseMatrix multiply(SparseMatrix a, SparseMatrix b);
}