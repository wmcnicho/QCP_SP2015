package Matrix;

import java.util.Arrays;

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

		if(a.isGate){
			if(b.isComplex){
				if(b.column==1){
					//optimised method only for gate-complex-vector
					return MatrixMultiply.GCMultiply(a,b);
				}
			}
		}
		return MatrixMultiply.Multiply(a, b);
	}

	/**
	 * Returns an independent copy of the matrix
	 * @return returns a copy of the matrix
	 */
	public Matrix getClone(){
		Matrix result;
		if(this.isGate){
			result = MatrixFactory.create(this.row, this.column, "gate");
		}
		else if(this.isSparse){
			result = MatrixFactory.create(this.row, this.column, "sparse");
		}
		else if(this.isComplex){
			result = MatrixFactory.create(this.row, this.column, "complex");
		}else{
			result = MatrixFactory.create(this.row, this.column, "");
		}
		result.row = this.row;
		result.column = this.column;
		result.rowIndex = null;
		result.isSparse = this.isSparse;
		result.isComplex = this.isComplex;
		result.isGate = this.isGate;
		if(this.reMatrix!=null){
			result.reMatrix = Arrays.copyOf(reMatrix, this.reMatrix.length);
		}
		if(this.imMatrix!=null){
			result.imMatrix = Arrays.copyOf(imMatrix, this.imMatrix.length);
		}
		if(this.rowIndex!=null){
			result.rowIndex = Arrays.copyOf(rowIndex, this.rowIndex.length);
		}
		return result;
	}
}
