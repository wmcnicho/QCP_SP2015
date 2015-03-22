package Matrix;

/**
 * The concrete implementation of a complex dense matrix.
 * @author Gennaro
 *
 */

public class ComplexMatrix extends Matrix{
	
	protected ComplexMatrix(int m, int n){
		if (m < 0) m = 0;
		row = m;
		if (n < 0) n = 0;
		column = n;
		reMatrix = new double[row*column];
		imMatrix = new double[row*column];
		isComplex = true;
	}

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
	
	public void scaleBy(double x){
		for(int i=0; i<reMatrix.length; i++){
			reMatrix[i]*=x;
			imMatrix[i]*=x;
		}
	}
}
