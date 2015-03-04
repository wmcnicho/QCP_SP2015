package simulator.mrep;

public abstract class Matrix {
	//abstract methods
	public abstract void setReal(long index, double real);
	public abstract void setReal(int i, int j, double real);
	public abstract void setImag(long index, double imag);
	public abstract void setImag(int i, int j, double imag);
	public abstract void set(long index, double [] z);
	public abstract void set(int i, int j, double [] z);
	public abstract void set(long index, double real, double imag);
	public abstract void set(int i, int j, double real, double imag);
	
	public abstract double getReal(long index);
	public abstract double getImag(long index);
	public abstract double getReal(int i, int j);
	public abstract double getImag(int i, int j);
	public abstract double [] get(long index);
	public abstract double [] get(int i, int j);
	
	public abstract int getColumnDimension();
	public abstract int getRowDimension();
	public abstract long getSize();
	
	public abstract void printMatrix();
	public abstract void printRealMatrix();
	public abstract void printImagMatrix();
	
	public abstract void addBy(Matrix m);
	public abstract void preMultiplyBy(Matrix m);
	public abstract void postMultiplyBy(Matrix m);
		
	public static Matrix create(MatrixType type, int row, int column){
		switch(type){
		case SPARSE:
			return new SparseMatrix(row, column);
		default://Dense case
			return new DenseMatrix(row, column);
		}
	}
	
	public static Matrix create(MatrixType type, int row, int column, double [][] values){
		switch(type){
		case SPARSE:
			return new SparseMatrix(row, column, values);
		default:
			return new DenseMatrix(row, column);
		}
	}
	
	public static Matrix copy(Matrix m){
		if (m instanceof SparseMatrix){
			return new SparseMatrix((SparseMatrix) m);
		} else {// m is a DenseMatrix object
			return new DenseMatrix((DenseMatrix) m);
		}
	}
	
	//handles add and multiply operations with different types of matrices
	public static boolean canMultiply(Matrix a, Matrix b){
		return a.getColumnDimension() == b.getRowDimension();
	}
	public static boolean canAdd(Matrix a, Matrix b){
		return a.getRowDimension() == b.getRowDimension() &&
				a.getColumnDimension() == b.getColumnDimension();
	}
	
	public static Matrix add(MatrixType type, SparseMatrix a, DenseMatrix b){
		Matrix m = null;
		if (canAdd(a,b)){
			switch(type){
			case SPARSE:
				m = new SparseMatrix(a);
				m.addBy(b);
				break;
			default:
				m = new DenseMatrix(b);
				m.addBy(a);
				break;
			}
		}
		return m;
	}
		
	public static Matrix multiply(MatrixType type, Matrix a, Matrix b){
		Matrix m = null;
		if (a instanceof SparseMatrix && b instanceof DenseMatrix){
			switch(type){
			case SPARSE:
				m = new SparseMatrix((SparseMatrix) a);
				m.postMultiplyBy(b);
				break;
			default:
				m = new DenseMatrix((DenseMatrix) b);
				m.preMultiplyBy(a);
				break;
			}
		} else if (a instanceof DenseMatrix && b instanceof SparseMatrix){
			switch(type){
			case SPARSE:
				m = new SparseMatrix((SparseMatrix) b);
				m.preMultiplyBy(a);
				break;
			default:
				m = new DenseMatrix((DenseMatrix) a);
				m.postMultiplyBy(b);
				break;
			}
		} else if (a instanceof SparseMatrix && b instanceof SparseMatrix){
			m = SparseMatrix.multiply((SparseMatrix) a, (SparseMatrix) b);
		} else {
			m = DenseMatrix.multiply((DenseMatrix) a, (DenseMatrix) b);
		}
		return m;
	}
}
