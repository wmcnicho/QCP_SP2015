package Matrix;

public class MatrixMultiply {



	public static Matrix RRMultiply(Matrix a, Matrix b){
		int row = a.row;
		int column = b.column;
		Matrix result = MatrixFactory.create(row, column, "");

		for(int global = 0; global<row*column; global++){
			int i = global /row;
			int j = global % row;
			double value = 0;
			for(int k = 0; k < row; k++){
				value += a.reMatrix[k + i * a.column] * b.reMatrix[k * b.column + j];
			}
			result.reMatrix[i * a.column + j] = value;
		}
		return result;
	}

	public static Matrix CCMultiply(Matrix a, Matrix b){
		return MatrixMultiply.Multiply(a, b);
	}

	public static Matrix CRMultiply(Matrix a, Matrix b){
		return MatrixMultiply.Multiply(a, b);
	}

	public static Matrix RCMultiply(Matrix a, Matrix b){
		return MatrixMultiply.Multiply(a, b);
	}

	public static Matrix CSMultiply(Matrix a, Matrix b){
		Matrix out = MatrixFactory.create(a.row, b.column, "complex");

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

	public static Matrix GCMultiply(Matrix a, Matrix b){
		Matrix out = MatrixFactory.create(a.row, b.column, "complex");

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

	public static Matrix RSMultiply(Matrix a, Matrix b){
		Matrix out = MatrixFactory.create(a.row, b.column, "complex");

		for(int i=0; i<a.row; i++){
			for(int j=0; j<b.column; j++){
				double reSum = 0.0;
				double imSum = 0.0;
				for(int k=0; k<b.row; k++){
					reSum+=a.getReElement(i, k)*b.getReElement(k,j);
					imSum+=a.getReElement(i, k)*b.getImElement(k, j);
				}
				out.setReElement(i, j, reSum);
				out.setImElement(i, j, imSum);
			}
		}
		return out;
	}

	public static Matrix SSMultiply(Matrix a, Matrix b){
		return MatrixMultiply.Multiply(a, b);
	}

	public static Matrix SCMultiply(Matrix a, Matrix b) {
		
		Matrix out = MatrixFactory.create(a.row, b.column, "complex");
		int rowIndex;
		for(int j=0; j<a.column; j++){
			double reSum = 0.0;
			double imSum = 0.0;
			int currentColIndex = 2*j;
			
			rowIndex = a.rowIndex[currentColIndex];
			reSum += b.reMatrix[rowIndex]*a.reMatrix[currentColIndex];
			reSum -= b.imMatrix[rowIndex]*a.imMatrix[currentColIndex];
			imSum -= b.reMatrix[rowIndex]*a.imMatrix[currentColIndex];
			imSum -= b.imMatrix[rowIndex]*a.reMatrix[currentColIndex];

			currentColIndex++;
			rowIndex = a.rowIndex[currentColIndex];

			reSum += b.reMatrix[rowIndex]*a.reMatrix[currentColIndex];
			reSum -= b.imMatrix[rowIndex]*a.imMatrix[currentColIndex];
			imSum -= b.reMatrix[rowIndex]*a.imMatrix[currentColIndex];
			imSum -= b.imMatrix[rowIndex]*a.reMatrix[currentColIndex];

			out.reMatrix[j]=reSum;
			out.imMatrix[j]=imSum*-1;
		}

		return out;
	}

	public static Matrix Multiply(Matrix a, Matrix b){
		Matrix out = MatrixFactory.create(a.row, b.column, "complex");
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

}
