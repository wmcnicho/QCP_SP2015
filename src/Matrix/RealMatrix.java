package Matrix;

/**
 * The concrete implementation of a real dense matrix.
 * 
 * @author Gennaro
 *
 */

public class RealMatrix extends Matrix{

	protected RealMatrix(int m, int n){
		if (m < 0) m = 0;
		row = m;
		if (n < 0) n = 0;
		column = n;
		reMatrix = new double [row*column];
	}

	public void setElement(int i, int j, double real, double imag){
		if (i >= 0 && j >= 0){
			reMatrix[i*column+j] = real;
		}
	}
	public void setElement(int i, int j, double value[]){
		if (i >= 0 && j >= 0){
			reMatrix[i*column+j] = value[0];
		}
	}
	public double[] getElement(int i, int j){
		return new double[]{reMatrix[i*column+j],0};
	}

	public void setReElement(int i, int j, double value) {
		reMatrix[i*column+j]=value;		
	}

	public void setImElement(int i, int j, double value) {		
	}

	public double getReElement(int i, int j) {
		return reMatrix[i*column+j];
	}

	public double getImElement(int i, int j) {
		return 0;
	}
}
