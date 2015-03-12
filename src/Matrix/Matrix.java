package Matrix;

/**
 * Use MatrixFactory to create matrices. To create sparse gate matrices use "gate" as type
 * @author Gennaro
 *
 */

public abstract class Matrix {

	protected int row;
	protected int column;
	protected int[] rowIndex = null;
	protected boolean isSparse = false;
	protected boolean isComplex = false;
	protected boolean isGate = false;
	protected double[] reMatrix;
	protected double[] imMatrix;

	/**
	 * Sets the real and imaginary parts of the Matrix element (i,j)
	 * @param i Row index of the element
	 * @param j	Column index of the element
	 * @param value Array containing the real and imaginary parts of the Matrix in the form [real, imaginary]
	 */
	public abstract void setElement(int i, int j, double[] value);
	
	/**
	 * Sets the real and imaginary parts of the Matrix element (i,j)
	 * @param i    Row index of the element
	 * @param j    Column index of the element
	 * @param real The value of the real component
	 * @param imag The value of the imaginary component
	 */
	public abstract void setElement(int i, int j, double real, double imag);
	
	/**
	 * Sets the real part of the Matrix element (i,j)
	 * @param i    Row index of the element
	 * @param j    Column index of the element
	 * @param value Value of the real part of the Matrix
	 */
	public abstract void setReElement(int i, int j, double value);
	
	/**
	 * Sets the imaginary part of the Matrix element (i,j)
	 * @param i    Row index of the element
	 * @param j    Column index of the element
	 * @param value Value of the imaginary part of the Matrix
	 */
	public abstract void setImElement(int i, int j, double value);
	
	/**
	 * Returns the element (i,j) as an array in the form [real, imaginary]
	 * @param i Row index of the element
	 * @param j	Column index of the element
	 * @return The array containing the element
	 */
	public abstract double[] getElement(int i, int j);
	
	/**
	 * Returns the real part of element (i,j)
	 * @param i Row index of the element
	 * @param j	Column index of the element
	 * @return The value of the real part element
	 */
	public abstract double getReElement(int i, int j);
	
	/**
	 * Returns the imaginary part of element (i,j)
	 * @param i Row index of the element
	 * @param j	Column index of the element
	 * @return The value of the imaginary part element
	 */
	public abstract double getImElement(int i, int j);

	protected Matrix(){	
	}

	/**
	 * multiplies this instance of Matrix by the passed matrix
	 * <p>
	 * The method decides the specific implementation of Matrix to return. Generally a dense complex matrix is returned unless both matrices are real or both are sparse.
	 * 
	 * @param multiplyByThis 
	 * 
	 */
	public void multiplyBy(Matrix multiplyByThis) {
		Matrix out = null;
		out = Matrix.Multiply(multiplyByThis, this);	

		this.row = out.row;
		this.column = out.column;
		this.rowIndex = out.rowIndex;
		this.isSparse = out.isSparse;
		this.isComplex = out.isComplex;
		this.reMatrix = out.reMatrix;
		this.imMatrix = out.imMatrix;
	}

	/**
	 * Multiplies Matrix A and B and returns a matrix C, C=AB
	 * <p>
	 * The method decides the specific implementation of Matrix to return. Generally a dense complex matrix is returned unless both matrices are real or both are sparse.
	 * @param  a Matrix A
	 * @param  b Matrix B
	 * @return   the product of the 2 Matrices 
	 */
	public static Matrix Multiply(Matrix a, Matrix b){
		Matrix out = null;

		//both sparse
		if(a.isSparse){
			if(b.isSparse){
				return MatrixMultiply.SSMultiply(a, b);
			}
			else if(b.isComplex){
				return MatrixMultiply.SCMultiply(a,b);
			}
			else{
				return MatrixMultiply.Multiply(a,b);
			}
		}
		else if(a.isComplex && !a.isSparse){
			if(b.isSparse){
				return MatrixMultiply.CSMultiply(a, b);
			}
			else if(b.isComplex){
				return MatrixMultiply.CCMultiply(a,b);
			}
			else if(!b.isComplex){
				return MatrixMultiply.CRMultiply(a, b);
			}
			else{
				return MatrixMultiply.Multiply(a,b);
			}
		}
		else{
			return MatrixMultiply.Multiply(a, b);
		}
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
