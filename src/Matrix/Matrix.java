package Matrix;

public abstract class Matrix {


	//add and multiply
	public static DenseMatrix Add(DenseMatrix a, DenseMatrix b){
		int row = a.row;
		int column = a.column;
		DenseMatrix result = new DenseMatrix(row, column);
		for (int i = 0; i < row*column; i++){
			result.reMatrix[i] = a.reMatrix[i] + b.reMatrix[i];
		}
		return result;
	}
	
	public static DenseComplexMatrix Add(DenseComplexMatrix a, DenseMatrix b){
		int row = a.row;
		int column = a.column;
		DenseComplexMatrix result = new DenseComplexMatrix(row, column);
		for (int i = 0; i < row*column; i++){
			result.reMatrix[i] = a.reMatrix[i] + b.reMatrix[i];
			result.reMatrix[i] = a.imMatrix[i];

		}
		return result;
	}
	
	public static DenseComplexMatrix Add(DenseComplexMatrix a, DenseComplexMatrix b){
		int row = a.row;
		int column = a.column;
		DenseComplexMatrix result = new DenseComplexMatrix(row, column);
		for (int i = 0; i < row*column; i++){
			result.reMatrix[i] = a.reMatrix[i] + b.reMatrix[i];
			result.imMatrix[i] = a.imMatrix[i] + b.imMatrix[i];
		}
		return result;
	}
	
	


	public static DenseMatrix Multiply(DenseMatrix a, DenseMatrix b){
		int row = a.row;
		int column = b.column;
		DenseMatrix result = new DenseMatrix(row, column);
		for(int global = 0; global<row*column; global++){
			int i = global /row;
			int j = global % row;
			double value = 0;
			for(int k = 0; k < row; k++)
			{
				value += a.reMatrix[k + i * a.column] * b.reMatrix[k * b.column + j];
			}
			result.reMatrix[i * a.column + j] = value;
		}
		return result;
	}
	
	public static DenseComplexMatrix Multiply(DenseComplexMatrix a, DenseComplexMatrix b){
		int row = a.row;
		int column = b.column;
		DenseComplexMatrix result = new DenseComplexMatrix(row, column);
		for(int global = 0; global<row*column; global++){
			int i = global /row;
			int j = global % row;
			double reValue = 0;
			double imValue = 0;
			for(int k = 0; k < row; k++)
			{
				int eleA = k + i * a.column;
				int eleB = k * b.column + j;
				reValue += a.reMatrix[eleA]*b.reMatrix[eleB]-a.imMatrix[eleA]*b.imMatrix[eleB];
				imValue += a.reMatrix[eleA]*b.imMatrix[eleB]+a.imMatrix[eleA]*b.reMatrix[eleB];
			}
			result.reMatrix[i * a.column + j] = reValue;
			result.imMatrix[i * a.column + j] = imValue;
		}
		return result;
	}
	
	public static DenseComplexMatrix Multiply(DenseComplexMatrix a, DenseMatrix b){
		int row = a.row;
		int column = b.column;
		DenseComplexMatrix result = new DenseComplexMatrix(row, column);
		for(int global = 0; global<row*column; global++){
			int i = global /row;
			int j = global % row;
			double reValue = 0;
			double imValue = 0;
			for(int k = 0; k < row; k++)
			{
				int eleA = k + i * a.column;
				int eleB = k * b.column + j;
				reValue += a.reMatrix[eleA]*b.reMatrix[eleB];
				imValue += a.imMatrix[eleA]*b.reMatrix[eleB];
			}
			result.reMatrix[i * a.column + j] = reValue;
			result.imMatrix[i * a.column + j] = imValue;
		}
		return result;
	}
	
	
	public abstract void multiplyBy(DenseMatrix a);
	
}
