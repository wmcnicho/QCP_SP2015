package Matrix;


import java.text.*;

public class ComplexMatrix implements IComplexMatrix{

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
}
