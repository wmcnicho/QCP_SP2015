package Matrix;


import java.text.*;

public class ComplexMatrix extends Matrix{

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
		isComplex = true;
	}

	//accessor methods
	public void setElement(int i, int j, double real, double imag){
		if (i >= 0 && j >= 0){
			reMatrix[i*column+j] = real;
			imMatrix[i*column+j] = imag;
		}
	}
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

	//class methods
	public static boolean canAdd(RealMatrix a, RealMatrix b){
		return a.row == b.row && a.column == b.column;
	}

	public static boolean canMultiply(RealMatrix a, RealMatrix b){
		return a.column == b.row;
	}
	
	public Matrix transpose(){
		return new TComplexMatrix(this);
	}
}