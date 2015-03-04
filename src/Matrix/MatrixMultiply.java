package Matrix;

public class MatrixMultiply {



	public static Matrix RRMultiply(Matrix a, Matrix b){
		int row = a.row;
		int column = b.column;
		Matrix result = MatrixFactory.create(row, column, "");
		for(int i=0; i<row; i++){
			for(int j=0; j<column; j++){
				double reSum = 0;
				for(int k=0; k<b.row; k++){
					reSum+=a.getReElement(i, k)*b.getReElement(k,j);
				}
				result.setReElement(i, j, reSum);
			}
		}
		return result;
		/*
		for(int global = 0; global<row*column; global++){
			int i = global /row;
			int j = global % row;
			double value = 0;
			for(int k = 0; k < row; k++){
				value += a.reMatrix[k + i * a.column] * b.reMatrix[k * b.column + j];
			}
			result.reMatrix[i * a.column + j] = value;
		}
		return result;*/
	}

	public static Matrix CCMultiply(Matrix a, Matrix b){
		int row = a.row;
		int column = b.column;
		Matrix result = MatrixFactory.create(row, column, "complex");
		for(int i=0; i<row; i++){
			for(int j=0; j<column; j++){
				double reSum = 0;
				double imSum = 0;
				for(int k=0; k<b.row; k++){
					reSum+=a.getReElement(i, k)*b.getReElement(k,j);
					reSum-=a.getImElement(i, k)*b.getImElement(k, j);
					imSum+=a.getImElement(i, k)*b.getReElement(k,j);
					imSum+=a.getReElement(i, k)*b.getImElement(k, j);
				}
				result.setReElement(i, j, reSum);
				result.setImElement(i, j, imSum);
			}
		}
		return result;
		/*
		for(int global = 0; global<row*column; global++){
			int i = global /row;
			int j = global % row;
			double reValue = 0;
			double imValue = 0;
			for(int k = 0; k < row; k++){

				int eleA = k + i * a.column;
				int eleB = k * b.column + j;
				reValue += a.reMatrix[eleA]*b.reMatrix[eleB]-a.imMatrix[eleA]*b.imMatrix[eleB];
				imValue += a.reMatrix[eleA]*b.imMatrix[eleB]+a.imMatrix[eleA]*b.reMatrix[eleB];

			}
			result.reMatrix[i * a.column + j] = reValue;
			result.imMatrix[i * a.column + j] = imValue;
		}
		return result;*/
	}

	public static Matrix CRMultiply(Matrix a, Matrix b){
		int row = a.row;
		int column = b.column;
		Matrix result = MatrixFactory.create(row, column, "complex");
		for(int i=0; i<row; i++){
			for(int j=0; j<column; j++){
				double reSum = 0;
				double imSum = 0;
				for(int k=0; k<b.row; k++){
					reSum+=a.getReElement(i, k)*b.getReElement(k,j);
					imSum+=a.getImElement(i, k)*b.getReElement(k,j);
				}
				result.setReElement(i, j, reSum);
				result.setImElement(i, j, imSum);
			}
		}
		return result;
		/*
		for(int global = 0; global<row*column; global++){
			int i = global /row;
			int j = global % row;
			double reValue = 0;
			double imValue = 0;
			for(int k = 0; k < row; k++){

				int eleA = k + i * a.column;
				int eleB = k * b.column + j;
				imValue += a.imMatrix[eleA]*b.reMatrix[eleB];
				reValue += a.reMatrix[eleA]*b.reMatrix[eleB];

			}
			result.reMatrix[i * a.column + j] = reValue;
			result.imMatrix[i * a.column + j] = imValue;
		}
		return result;*/
	}

	public static Matrix RCMultiply(Matrix a, Matrix b){
		int row = a.row;
		int column = b.column;
		Matrix result = MatrixFactory.create(row, column, "complex");
		for(int i=0; i<row; i++){
			for(int j=0; j<column; j++){
				double reSum = 0;
				double imSum = 0;
				for(int k=0; k<b.row; k++){
					reSum+=a.getReElement(i, k)*b.getReElement(k,j);
					imSum+=a.getReElement(i, k)*b.getImElement(k, j);
				}
				result.setReElement(i, j, reSum);
				result.setImElement(i, j, imSum);
			}
		}
		return result;
		/*
		for(int global = 0; global<row*column; global++){
			int i = global /row;
			int j = global % row;
			double reValue = 0;
			double imValue = 0;
			for(int k = 0; k < row; k++){

				int eleA = k + i * a.column;
				int eleB = k * b.column + j;
				imValue += a.reMatrix[eleA]*b.imMatrix[eleB];
				reValue += a.reMatrix[eleA]*b.reMatrix[eleB];
			}
			result.reMatrix[i * a.column + j] = reValue;
			result.imMatrix[i * a.column + j] = imValue;
		}
		return result;*/
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
		Matrix out = MatrixFactory.create(a.row, b.column, "sparse");

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

	public static Matrix SCMultiply(Matrix a, Matrix b) {
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
