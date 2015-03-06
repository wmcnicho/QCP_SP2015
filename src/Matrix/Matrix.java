package Matrix;

/*
 *can call get and set methods
 *multiplyBy
 *for sparseMatrix call this.convert() before multiplying
 *
 *don't call add methods
 *don't do stupid stuff
 *don't multiply matrices that can't be multiplied
 */

public abstract class Matrix {

	protected int row;
	protected int column;
	protected int[] rowIndex = null;
	public boolean isSparse = false;
	public boolean isComplex = false;
	public boolean isGate = false;
	protected double[] reMatrix;
	protected double[] imMatrix;

	public abstract void setElement(int i, int j, double[] value);
	public abstract void setElement(int i, int j, double real, double imag);
	public abstract void setReElement(int i, int j, double value);
	public abstract void setImElement(int i, int j, double value);
	public abstract double[] getElement(int i, int j);
	public abstract double getReElement(int i, int j);
	public abstract double getImElement(int i, int j);

	public void addBy(Matrix m){};

	public Matrix(){	
	}

	public void multiplyBy(Matrix b) {
		Matrix out = null;
		out = Matrix.Multiply(b, this, out);	
		
		this.row = out.row;
		this.column = out.column;
		this.rowIndex = out.rowIndex;
		this.isSparse = out.isSparse;
		this.isComplex = out.isComplex;
		this.reMatrix = out.reMatrix;
		this.imMatrix = out.imMatrix;
	}

	private static Matrix Multiply(Matrix a, Matrix b, Matrix out){
		
		if(!a.isComplex&&!b.isComplex){
			out = MatrixFactory.create(a.row, b.column, "");
		}
		//both sparse
		else if(a.isSparse && b.isSparse){
			out = MatrixFactory.create(a.row, b.column, "sparse");
		}
		else{
			out = MatrixFactory.create(a.row, b.column, "complex");
		}
		for(int i=0; i<a.row; i++){
			for(int j=0; j<b.column; j++){
				double reSum = 0.0;
				double imSum = 0.0;
				for(int k=0; k<b.row; k++){
					reSum+=a.getReElement(i, k)*b.getReElement(k,j);
					reSum-=a.getImElement(i, k)*b.getImElement(k, j);
					imSum+=a.getImElement(i, k)*b.getReElement(k,j);
					imSum+=a.getReElement(i, k)*b.getImElement(k, j);
				}
				out.setReElement(i, j, reSum);
				out.setImElement(i, j, imSum);
			}
		}
		return out;
	}
	
	/*
	public static ComplexMatrix Add(Matrix a, Matrix b){
		return null;
	}

	public static RealMatrix Add(RealMatrix a, RealMatrix b){
		int row = a.row;
		int column = a.column;
		RealMatrix result = new RealMatrix(row, column);
		for (int i = 0; i < row*column; i++){
			result.reMatrix[i] = a.reMatrix[i] + b.reMatrix[i];
		}
		return result;
	}

	public static ComplexMatrix Add(ComplexMatrix a, RealMatrix b){
		int row = a.row;
		int column = a.column;
		ComplexMatrix result = new ComplexMatrix(row, column);
		for (int i = 0; i < row*column; i++){
			result.reMatrix[i] = a.reMatrix[i] + b.reMatrix[i];
			result.reMatrix[i] = a.imMatrix[i];
		}
		return result;
	}

	public static ComplexMatrix Add(ComplexMatrix a, ComplexMatrix b){
		int row = a.row;
		int column = a.column;
		ComplexMatrix result = new ComplexMatrix(row, column);
		for (int i = 0; i < row*column; i++){
			result.reMatrix[i] = a.reMatrix[i] + b.reMatrix[i];
			result.imMatrix[i] = a.imMatrix[i] + b.imMatrix[i];
		}
		return result;
	}*/
}
