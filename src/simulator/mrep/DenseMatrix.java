package simulator.mrep;

import java.text.NumberFormat;


public class DenseMatrix {
	protected int row;
	protected int column;
	protected double[] reMatrix;
	protected double[] imMatrix;
	private NumberFormat nf = NumberFormat.getNumberInstance();

	//constructors
	public DenseMatrix(int m, int n){
		if (m < 0) m = 0;
		row = m;
		if (n < 0) n = 0;
		column = n;
		reMatrix = new double[row*column];
		imMatrix = new double[row*column];
		initMatrix();
		//keeps java array indexing convention
		//for formatting text output
		nf.setMaximumFractionDigits(2);
		nf.setMinimumFractionDigits(2);
	}
	
	public void initMatrix(){
		for (int i = 0; i < row*column; i++){
			reMatrix[i] = 0.0;
			imMatrix[i] = 0.0;
		}
	}

	//accessor methods
	public void setElement(int i, int j, double[] value){
		if (i >= 0 && j >= 0){
			reMatrix[i*column+j] = value[0];
			imMatrix[i*column+j] = value[1];
		}
	}
	
	public void setElement(int i, int j, double real, double imag){
		if (i >= 0 && j >= 0){
			reMatrix[i*column+j] = real;
			imMatrix[i*column+j] = imag;
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
	
	public void setReElement(double [] value){
		int max = value.length;
		if (max > reMatrix.length){
			max = reMatrix.length;
		}
		for (int i = 0; i < max; i++){
			reMatrix[i] = value[i];
		}
	}
	
	public void setImElement(double [] value){
		int max = value.length;
		if (max > imMatrix.length){
			max = imMatrix.length;
		}
		for (int i = 0; i < max; i++){
			imMatrix[i] = value[i];
		}
	}

	public double getReElement(int i, int j){return reMatrix[i*column+j];}
	public double getImElement(int i, int j){return imMatrix[i*column+j];}
	public double[] getElement(int i, int j){
		double[] out ={reMatrix[i*column+j], imMatrix[i*column+j]};
		return out;
	}

	public int getRowDimension(){return row;}
	public int getColumnDimension(){return column;}

	public void printReMatrix(){
		for (int i = 0; i < row; i++){
			for (int j = 0; j < column; j++){
				System.out.printf("%.2f\t",reMatrix[i*column+j]);
			}
			System.out.println();
		}
	}

	public void printImMatrix(){
		for (int i = 0; i < row; i++){
			for (int j = 0; j < column; j++){
				System.out.printf("%.2f\t",imMatrix[i*column+j]);
			}
			System.out.println();
		}
	}
	
	public void printMatrix(){
		for (int i = 0; i < row; i++){
			for (int j = 0; j < column; j++){
				System.out.printf("%.2f + %.2fi\t",reMatrix[i*column+j],imMatrix[i*column+j]);
			}
			System.out.println();
		}
	}

	//matrix algebra methods
	public void addBy(DenseMatrix m){
		if (canAdd(m,this)){
			for (int i = 0; i < row*column; i++){
				reMatrix[i] += m.reMatrix[i];
				imMatrix[i] += m.imMatrix[i];
			}
		}
	}
	
	public void multiplyBy(double k){
		for (int i = 0; i < row*column; i++){
			reMatrix[i] *= k;
			imMatrix[i] *= k;
		}
	}
	
	public void preMultiplyBy(DenseMatrix m){	
		if (canMultiply(m,this)){
			
			double [] real = new double [m.row * this.column];
			double [] imag = new double [m.row * this.column];
			
			for(int global = 0; global<m.row * this.column; global++){
				int i = global / this.column;
				int k = global % this.column;
				double reValue = 0;
				double imValue = 0;
				for(int j = 0; j < this.row; j++){
					int eleA = i * m.column + j;
					int eleB = j * this.column + k;
					reValue += m.reMatrix[eleA] * this.reMatrix[eleB] - m.imMatrix[eleA] * this.imMatrix[eleB];
					imValue += m.reMatrix[eleA] * this.imMatrix[eleB]+  m.imMatrix[eleA] * this.reMatrix[eleB];
				}
				real[i * this.column + k] = reValue;
				imag[i * this.column + k] = imValue;

			}
			reMatrix = real;
			imMatrix = imag;
			
			//update row and column length
			this.row = m.row;
		}
	}

	//class methods
	public static boolean canAdd(DenseMatrix a, DenseMatrix b){
		return a.row == b.row && a.column == b.column;
	}

	public static boolean canMultiply(DenseMatrix a, DenseMatrix b){
		return a.column == b.row;
	}
}
