package Matrix;

import java.util.Arrays;
import java.util.Hashtable;

/**
 * The concrete implementation of a sparse matrix.
 * Can hold up to 2 non-zero elements in each row, ideal for quantum gates
 * @author Gennaro
 *
 */
public class SparseGateMatrix extends Matrix{

	protected SparseGateMatrix(int row, int column){
		this.isSparse = true;
		this.isComplex = true;
		this.isGate = true;
		this.row = row;
		this.column = column;
		this.reMatrix = new double[column*2];
		this.imMatrix = new double[column*2];
		this.rowIndex = new int[column*2];
		//used to check which elements have been set
		Arrays.fill(rowIndex, -1);
	}
	
	public void setElement(int i, int j, double real, double imag){
		int tempIndex = 2*j;
		//checks if current index has not been written to already
		//and it is not just changing the value of an already set element
		if(rowIndex[tempIndex]!=-1&&rowIndex[tempIndex]!=i){
			tempIndex++;
		}

		reMatrix[tempIndex]=real;
		imMatrix[tempIndex]=imag;
		rowIndex[tempIndex]=i;
	}
	
	public void setElement(int i, int j, double[] value) {

		int tempIndex = 2*j;
		if(rowIndex[tempIndex]!=-1&&rowIndex[tempIndex]!=i){
			tempIndex++;
		}

		reMatrix[tempIndex]=value[0];
		imMatrix[tempIndex]=value[1];
		rowIndex[tempIndex]=i;
	}

	public void setReElement(int i, int j, double value) {
		int tempIndex = 2*j;
		if(rowIndex[tempIndex]!=-1&&rowIndex[tempIndex]!=i){
			tempIndex++;
		}

		reMatrix[tempIndex]=value;
		rowIndex[tempIndex]=i;
	}

	public void setImElement(int i, int j, double value) {
		int tempIndex = 2*j;
		if(rowIndex[tempIndex]!=-1&&rowIndex[tempIndex]!=i){
			tempIndex++;
		}

		imMatrix[tempIndex]=value;
		rowIndex[tempIndex]=i;
	}

	public double[] getElement(int i, int j) {
		double re = getReElement(i,j);
		double im = getImElement(i,j);
		return new double[]{re, im};
	}

	public double getReElement(int i, int j) {
		int index = j*2;
		if(rowIndex[index]==i){
			return reMatrix[index];
		}
		else if(rowIndex[index+1]==i){
			return reMatrix[index+1];
		}else{
			return 0;
		}
	}	

	public double getImElement(int i, int j) {
		int index = j*2;
		if(rowIndex[index]==i){
			return imMatrix[index];
		}
		else if(rowIndex[index+1]==i){
			return imMatrix[index+1];
		}else{
			return 0;
		}
	}

}
