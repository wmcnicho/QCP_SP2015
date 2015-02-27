package Matrix;


public abstract class Matrix {

	protected int row;
	protected int column;
	protected int[] rowIndex;
	public boolean isSparse = false;
	
	public abstract void setElement(int i, int j, double[] value);
	public abstract void setReElement(int i, int j, double value);
	public abstract void setImElement(int i, int j, double value);
	public abstract double[] getElement(int i, int j);
	public abstract double getReElement(int i, int j);
	public abstract double getImElement(int i, int j);
	
	public abstract Matrix addBy(Matrix m);
	public abstract Matrix multiplyBy(Matrix m);
	
	
	public Matrix(){	
	}
	
	//some maths
	
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
			
			for(int k=0; k<2; k++){

				int i = b.rowIndex[2*j+k];

				reSum += a.getReElement(0, i)*b.getReElement(i, j);
				reSum -= a.getImElement(0, i)*b.getImElement(i, j);
				
				imSum += a.getReElement(0, i)*b.getImElement(i, j);
				imSum += a.getImElement(0, i)*b.getReElement(i, j);
			
			}
			out.reMatrix[j]=reSum;
			out.imMatrix[j]=imSum;
		}
		return out;
	}
	
	public static ComplexMatrix Multiply(RealMatrix a, SparseMatrix b){
		ComplexMatrix out = new ComplexMatrix(a.row, b.column);
		for(int j=0; j<b.column; j++){
			
			double reSum = 0.0;
			double imSum = 0.0;
			
			for(int k=0; k<2; k++){

				int i = b.rowIndex[2*j+k];
				
				reSum += a.getReElement(0, i)*b.getReElement(i, j);
			
			}
			out.reMatrix[j]=reSum;
			out.imMatrix[j]=imSum;
		}
		return out;
	}
}
