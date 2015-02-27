package Matrix;

import java.util.Arrays;
//import java.util.Enumeration;
import java.util.Hashtable;

public class SparseMatrix extends Matrix{
	protected int row;
	protected int column;
	protected int size;
	protected double[] reElements;
	protected double[] imElements;
	protected int[] rowIndex;
	protected SparseHMatrix hashMatrix;
	private boolean converted = false;
	
	public SparseMatrix(int row, int column){
		this.isSparse = true;
		hashMatrix = new SparseHMatrix(row, column);
		this.row = row;
		this.column = column;
	}
	
	public void setElement(int i, int j, double[] value) {
		hashMatrix.setRealPart(i+1, j+1, value[0]);
		hashMatrix.setImagPart(i+1, j+1, value[1]);
	}

	public void setReElement(int i, int j, double value) {
		hashMatrix.setRealPart(i+1, j+1, value);
	}

	public void setImElement(int i, int j, double value) {
		hashMatrix.setImagPart(i+1, j+1, value);
	}

	public double[] getElement(int i, int j) {
		double re = getReElement(i,j);
		double im = getImElement(i,j);
		return new double[]{re, im};
	}

	public double getReElement(int i, int j) {
		if(converted){
			int index = j*2;
			if(rowIndex[index]==i){
				return reElements[index];
			}
			else if(rowIndex[index+1]==i){
				return reElements[index+1];
			}else{
				return 0;
			}
		}
		else{
			return hashMatrix.getRealPart(i+1, j+1);
		}
	}
	

	@Override
	public double getImElement(int i, int j) {
		if(converted){
			int index = j*2;
			if(rowIndex[index]==i){
				return imElements[index];
			}
			else if(rowIndex[index+1]==i){
				return imElements[index+1];
			}else{
				return 0;
			}
		}
		else{
			return hashMatrix.getImagPart(i+1, j+1);
		}
	}
	
	public void convert(){
		hashMatrix.printMatrix();
		reElements = new double[2*column];
		imElements = new double[2*column];
		rowIndex = new int[2*column];
		//loop over column
		for(int j=1; j<=column; j++){
			//2 elements in each row
			int count = 0;
			for(int i=1; i<=row; i++){
				if(hashMatrix.getImagPart(i, j)!=0 ||hashMatrix.getRealPart(i,j)!=0){
						reElements[2*(j-1)+count]=hashMatrix.getRealPart(i,j);
						imElements[2*(j-1)+count]=hashMatrix.getImagPart(i,j);
						rowIndex[2*(j-1)+count]=(i-1);
						count++;
						if(count>=2){
							count=0;
							break;
					}
				}
			}
		}
		
		converted = true;
		
		//clear hash maps and run GC
		hashMatrix.EntryReal.clear();
		hashMatrix.EntryImag.clear();
		Runtime.getRuntime().gc();
	}
	
	public void print(){
		System.out.println(Arrays.toString(reElements));
		System.out.println(Arrays.toString(imElements));
		System.out.println(Arrays.toString(rowIndex));
	}
	
	public ComplexMatrix multiplyBy(ComplexMatrix b){
		return Matrix.Multiply(b, this);
	}
	public ComplexMatrix multiplyBy(RealMatrix b){
		return Matrix.Multiply(b, this);
	}

	@Override
	public Matrix addBy(Matrix m) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Matrix multiplyBy(Matrix m) {
		// TODO Auto-generated method stub
		return null;
	}
}