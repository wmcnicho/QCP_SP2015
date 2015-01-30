

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
	public static boolean isSquareMatrix(Matrix m){
		if (m.row == m.column) return true;
		return false;
	}
	
	public static boolean isEqual(Matrix a, Matrix b, double tolerance){
		if (!canAdd(a,b)) return false;
		for (int i = 0; i < a.row; i++){
			for (int j = 0; j < a.column; j++){
				if (Math.abs(a.matrix[i][j] - b.matrix[i][j]) > tolerance) return false;
			}
		}
		return true;
	}
	public static double det(Matrix m){
		if (isSquareMatrix(m)) return cal_det(m);
		return 0;
	}
	private static double cal_det(Matrix m){
		double sum = 0;
		if (m.row == 1 && m.column == 1){
			return m.matrix[0][0];
		} else {
			for (int i = 0; i < m.column; i++){
				sum += Math.pow(-1, i) * m.matrix[0][i] * cal_det(Matrix.reduceRowAndColumn(m, 0, i));
			}
			return sum;
		}
	}
	
	public static Matrix inverse(Matrix m){
		double det = Matrix.det(m);
		Matrix result = new Matrix(m.row, m.column);
		if (det != 0){
			for (int i = 0; i < m.row; i++){
				for (int j = 0; j < m.column; j++){
					result.matrix[i][j] =
							1 / det * Math.pow(-1, i+j) * cal_det(Matrix.reduceRowAndColumn(m, j, i));
				}
			}
			return result;
		} else {
			return new Matrix(0,0);
		}
	}

	
	public static boolean canReduceRow(Matrix a){
		if (a.row > 0) return true;
		return false;
	}
	public static boolean canReduceColumn(Matrix a){
		if (a.column > 0) return true;
		return false;
	}
	public static Matrix reduceRowAndColumn(Matrix a, int row, int col){
		if (canReduceRow(a) && canReduceColumn(a)){
			Matrix result = new Matrix(a.row-1, a.column-1);
			int x = 0, y = 0;
			for (int i = 0; i < a.row; i++){
				if (i != row){
					y = 0;
					for (int j = 0; j < a.column; j++){
						if (j != col){
							result.matrix[x][y] = a.matrix[i][j];
							y++;
						}
					}
					x++;
				}
			}
			return result;
		}
		return new Matrix(0,0);
	}
}
