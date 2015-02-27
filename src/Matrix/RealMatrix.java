package Matrix;


import java.text.*;

public class RealMatrix extends Matrix{

	protected int row;
	protected int column;
	protected double [] reMatrix;
	private NumberFormat nf = NumberFormat.getNumberInstance();

	//constructors
	public RealMatrix(int m, int n){
		if (m < 0) m = 0;
		row = m;
		if (n < 0) n = 0;
		column = n;
		reMatrix = new double [row*column];
		//row-=1; column-=1;
		//for formatting text output
		nf.setMaximumFractionDigits(2);
		nf.setMinimumFractionDigits(2);
	}

	//accessor methods
	public void setElement(int i, int j, double value[]){
		if (i >= 0 && j >= 0){
			reMatrix[i*column+j] = value[0];
		}
	}
	public double[] getElement(int i, int j){
		return new double[]{reMatrix[i*column+j],0};
	}

	//public int getRowDimension(){return matrix.length;}
	//public int getColumnDimension(){return matrix[0].length;}

	public String toString(){
		String result = "";

		for (int i = 0; i < row; i++){
			for (int j = 0; j < column; j++){
				result += nf.format(reMatrix[i*row+j]) + " ";
			}
			result += "\n";
		}
		return result;
	}
	public void printMatrix(){
		for (int i = 0; i < row; i++){
			for (int j = 0; j < column; j++){
				System.out.print(nf.format(reMatrix[i*column+j]) + "\t");
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
	public void setReElement(int i, int j, double value) {
		reMatrix[i*column+j]=value;		
	}

	@Override
	public void setImElement(int i, int j, double value) {		
	}

	@Override
	public double getReElement(int i, int j) {
		return reMatrix[i*column+j];
	}

	@Override
	public double getImElement(int i, int j) {
		return 0;
	}

	@Override
	public Matrix addBy(Matrix m) {
		return Matrix.Add(this, m);
	}

	@Override
	public Matrix multiplyBy(Matrix m) {
		return Matrix.Add(this, m);
	}
}
