package Matrix;

public class Matrix {


	//add and multiply
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
	}
	
	public static RealMatrix Multiply(RealMatrix a, RealMatrix b){
		int row = a.row;
		int column = b.column;
		RealMatrix result = new RealMatrix(row, column);
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
	
	public static ComplexMatrix Multiply(ComplexMatrix a, ComplexMatrix b){
		int row = a.row;
		int column = b.column;
		ComplexMatrix result = new ComplexMatrix(row, column);
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
	
	public static ComplexMatrix Multiply(ComplexMatrix a, RealMatrix b){
		int row = a.row;
		int column = b.column;
		ComplexMatrix result = new ComplexMatrix(row, column);
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
	
	public static ComplexMatrix Multiply(ComplexMatrix a, SparseMatrix b){
		ComplexMatrix out = new ComplexMatrix(a.row, b.column);
		for(int j=0; j<b.column; j++){
			double reSum = 0.0;
			double imSum = 0.0;

			reSum+=a.reMatrix[b.rowIndex[2*j]]*b.reElements[j*2];
			reSum+=a.reMatrix[b.rowIndex[2*j+1]]*b.reElements[j*2+1];
			reSum-=a.imMatrix[b.rowIndex[2*j]]*b.imElements[j*2];
			reSum-=a.imMatrix[b.rowIndex[2*j+1]]*b.imElements[j*2+1];
			out.reMatrix[j]=reSum;
			
			imSum+=a.reMatrix[b.rowIndex[2*j]]*b.imElements[j*2];
			imSum+=a.imMatrix[b.rowIndex[2*j+1]]*b.reElements[j*2+1];
			imSum+=a.imMatrix[b.rowIndex[2*j]]*b.reElements[j*2];
			imSum+=a.reMatrix[b.rowIndex[2*j+1]]*b.imElements[j*2+1];
			out.imMatrix[j]=imSum;
		}
		return out;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
