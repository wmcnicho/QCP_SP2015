package Matrix;


import java.text.*;

public class ComplexMatrix extends Matrix{

	protected int row;
	protected int column;
	protected double[] reMatrix;
	protected double[] imMatrix;
	private NumberFormat nf = NumberFormat.getNumberInstance();

	//constructors
	public ComplexMatrix(int m, int n){
		if (m < 0) m = 0;
		row = m;
		if (n < 0) n = 0;
		column = n;
		reMatrix = new double[row*column];
		imMatrix = new double[row*column];
		//keeps java array indexing convention
		//for formatting text output
		nf.setMaximumFractionDigits(2);
		nf.setMinimumFractionDigits(2);
	}

	//accessor methods
	public void setElement(int i, int j, double[] value){
		if (i >= 0 && j >= 0){
			reMatrix[i*column+j] = value[0];
			imMatrix[i*column+j] = value[1];
		}
	}

	public void setReElement(int i, int j, double value){
		if (i >= 0 && j >= 0){
			reMatrix[i*column+j] = value;
		}
	}

	public void setImElement(int i, int j, double value){
		if (i >= 0 && j >= 0){
			imMatrix[i*column+j] = value;
		}
	}

	public double getReElement(int i, int j){return reMatrix[i*column+j];}
	public double getImElement(int i, int j){return imMatrix[i*column+j];}
	public double[] getElement(int i, int j){
		double[] out ={reMatrix[i*column+j], imMatrix[i*column+j]};
		return out;
	}


	//public int getRowDimension(){return matrix.length;}
	//public int getColumnDimension(){return matrix[0].length;}

	public void printReMatrix(){
		for (int i = 0; i < row; i++){
			for (int j = 0; j < column; j++){
				System.out.print(nf.format(reMatrix[i*column+j]) + "\t");
			}
			System.out.println();
		}
	}

	public void printImMatrix(){
		for (int i = 0; i < row; i++){
			for (int j = 0; j < column; j++){
				System.out.print(nf.format(imMatrix[i*column+j]) + "\t");
			}
			System.out.println();
		}
	}

	public void printMatrix(){
		for (int i = 0; i < row; i++){
			for (int j = 0; j < column; j++){
				System.out.print(nf.format(reMatrix[i*column+j]) + " + "+ nf.format(imMatrix[i*column+j])+"i " + "\t");
			}
			System.out.println();
		}
	}

	//matrix algebra methods

	public void addBy(RealMatrix m){
		for (int i = 0; i < row; i++){
			reMatrix[i] += m.reMatrix[i];
		}
	}

	public void addBy(ComplexMatrix m){
		for (int i = 0; i < row; i++){
			reMatrix[i] += m.reMatrix[i];
			imMatrix[i] += m.imMatrix[i];
		}
	}

	public void multiplyBy(SparseMatrix gate){
		reMatrix = Matrix.Multiply(this, gate).getElements();
	}

	public void multiplyBy(RealMatrix m){
		reMatrix = Matrix.Multiply(this, m).getElements();
	}

	private double[] getElements() {
		return reMatrix;
	}

	//class methods
	public static boolean canAdd(RealMatrix a, RealMatrix b){
		return a.row == b.row && a.column == b.column;
	}

	public static boolean canMultiply(RealMatrix a, RealMatrix b){
		return a.column == b.row;
	}

	@Override
	public Matrix addBy(Matrix m) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Matrix multiplyBy(Matrix b) {

		ComplexMatrix a = this;
		ComplexMatrix out = new ComplexMatrix(a.row, b.column);
		
		if(b.isSparse){
	
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
		else{
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
			//		reValue += a.reMatrix[eleA]*b.reMatrix[eleB]-a.imMatrix[eleA]*b.imMatrix[eleB];
			//		imValue += a.reMatrix[eleA]*b.imMatrix[eleB]+a.imMatrix[eleA]*b.reMatrix[eleB];
				}
				result.reMatrix[i * a.column + j] = reValue;
				result.imMatrix[i * a.column + j] = imValue;
			}
			return result;
		}
	}
}
