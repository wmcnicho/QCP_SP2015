package simulator.mrep;

public abstract class Matrix {
	public abstract void setElement(int i, int j, double [] z);
	public abstract void setElement(int i, int j, double real, double imag);
	public abstract double [] getElement(int i, int j);
	public abstract void printMatrix();
	public abstract void printRealMatrix();
	public abstract void printImagMatrix();
	public abstract void addBy(DenseMatrix m);
	public abstract void addBy(SparseMatrix m);
	public abstract void multiplyBy(DenseMatrix m);
	public abstract void multiplyBy(SparseMatrix m);
}
