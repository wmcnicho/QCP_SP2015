package simulator.mrep;


import java.text.*;

public class Matrix {
	
	private int row;
	private int column;
	private double [][] matrix;
	private NumberFormat nf = NumberFormat.getNumberInstance();
	
	//constructors
	public Matrix(){
		new Matrix(0,0);
	}
	public Matrix(int m, int n){
		if (m < 0) m = 0;
		row = m;
		if (n < 0) n = 0;
		column = n;
		matrix = new double [row][column];
		
		//for formatting text output
		nf.setMaximumFractionDigits(2);
		nf.setMinimumFractionDigits(2);
	}
	public Matrix(int m, int n, double [] values){
		if (m < 0) m = 0;
		row = m;
		if (n < 0) n = 0;
		column = n;
		matrix = new double [row][column];
		int k = 0;
		for (int i = 0; i < m; i++){
			for (int j = 0; j < n; j++){
				matrix[i][j] = values[k];
				k++;
			}
		}
		
		//for formatting text output
		nf.setMaximumFractionDigits(2);
		nf.setMinimumFractionDigits(2);
	}
	
	public Matrix(Matrix m){
		this(m.getRowDimension(),m.getColumnDimension());
		this.matrix = new double [m.row][m.column];
		for (int i = 0; i < this.matrix.length; i++){
			for (int j = 0; j < this.matrix[i].length; j++){
				this.matrix[i][j] = m.matrix[i][j];
			}
		}
	}

	//accessor methods
	public void setElement(int i, int j, double value){
		if (i >= 0 && j >= 0){
			matrix[i][j] = value;
		}
	}
	public double getElement(int i, int j){return matrix[i][j];}
	
	public int getRowDimension(){return matrix.length;}
	public int getColumnDimension(){return matrix[0].length;}
	
	public String toString(){
		String result = "";
		
		for (int i = 0; i < row; i++){
			for (int j = 0; j < column; j++){
				result += nf.format(matrix[i][j]) + " ";
			}
			result += "\n";
		}
		return result;
	}
	public void printMatrix(){
		for (int i = 0; i < row; i++){
			for (int j = 0; j < column; j++){
				System.out.print(nf.format(matrix[i][j]) + "\t");
			}
			System.out.println();
		}
	}
	
	//matrix algebra methods
	public void addBy(Matrix m){
		if (canAdd(this,m)){
			for (int i = 0; i < matrix.length; i++){
				for (int j = 0; j < matrix[i].length; i++){
					matrix[i][j] += m.matrix[i][j];
				}
			}
		}
	}
	public void multiplyBy(Matrix m){
		if (canMultiply(this,m)){
			column = m.column;
			double [][] result = new double [row][column];
			for (int i = 0; i < result.length; i++){
				for (int k = 0; k < result[i].length; k++){
					double sum = 0;
					for (int j = 0; j < this.column; j++){
						sum += this.matrix[i][j] * m.matrix[j][k];
					}
					result[i][k] = sum;
				}
			}
			matrix = result;
		}
	}
	
	//class methods
	public static boolean canAdd(Matrix a, Matrix b){
		return a.row == b.row && a.column == b.column;
	}
	public static Matrix add(Matrix a, Matrix b){
		//ensure that the matrices have the same dimensions
		if (canAdd(a,b)){
			Matrix result = new Matrix(a.row, a.column);
			for (int i = 0; i < a.matrix.length; i++){
				for (int j = 0; j < a.matrix[i].length; j++){
					result.matrix[i][j] = a.matrix[i][j] + b.matrix[i][j];
				}
			}
			return result;
		} else {
			return new Matrix(0,0);
		}
	}
	public static boolean canMultiply(Matrix a, Matrix b){
		return a.column == b.row;
	}
	public static Matrix multiply(Matrix a, Matrix b){
		//ensure that the number of columns of matrix a equals to the number of rows of matrix b
		if (canMultiply(a,b)){
			Matrix result = new Matrix(a.row, b.column);
			for (int i = 0; i < result.row; i++){
				for (int k = 0; k < result.column; k++){
					double sum = 0;
					for (int j = 0; j < a.column; j++){
						sum += a.matrix[i][j] * b.matrix[j][k];
					}
					result.matrix[i][k] = sum;
				}
			}
			return result;
		} else {
			return new Matrix(0,0);
		}
	}
}
