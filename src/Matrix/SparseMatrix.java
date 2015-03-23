package Matrix;

/**
 * General sparse matrix class. Uses a hash table to store values until all the values have been set
 * Then converted to the same data structure as the other matrices to allow for faster matrix multiplication
 * Never used in this project, unoptimised and very slow
 * 
 * @author Gennaro
 * @deprecated Slow, inefficient and possibly still contains bugs. Use SparseGateMatrix instead.
 */
public class SparseMatrix extends Matrix{

	protected int size;
	protected int elementsInRow = 0;
	protected SparseHMatrix hashMatrix;
	private boolean converted = false;
	
	public SparseMatrix(int row, int column){
		this.isSparse = true;
		this.isComplex = true;
		hashMatrix = new SparseHMatrix(row, column);
		this.row = row;
		this.column = column;
	}
	
	public void setElement(int i, int j, double real, double imag){
		hashMatrix.setRealPart(i+1, j+1, real);
		hashMatrix.setImagPart(i+1, j+1, imag);
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
			int index = j*elementsInRow;
			if(rowIndex[index]==i){
				return reMatrix[index];
			}
			else if(rowIndex[index+1]==i){
				return reMatrix[index+1];
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
			int index = j*elementsInRow;
			if(rowIndex[index]==i){
				return imMatrix[index];
			}
			else if(rowIndex[index+1]==i){
				return imMatrix[index+1];
			}else{
				return 0;
			}
		}
		else{
			return hashMatrix.getImagPart(i+1, j+1);
		}
	}
	
	public void convert(){
		int size = hashMatrix.EntryImag.size();
		reMatrix = new double[size];
		imMatrix = new double[size];
		rowIndex = new int[size];
		//loop over column
		for(int j=1; j<=column; j++){
			int count = 0;
			for(int i=1; i<=row; i++){
				if(hashMatrix.getImagPart(i, j)!=0 ||hashMatrix.getRealPart(i,j)!=0){
						reMatrix[elementsInRow*(j-1)+count]=hashMatrix.getRealPart(i,j);
						imMatrix[elementsInRow*(j-1)+count]=hashMatrix.getImagPart(i,j);
						rowIndex[elementsInRow*(j-1)+count]=(i-1);
						count++;
				}
				if(i==1){
					elementsInRow=count;
				}
			}
		}
		
		converted = true;
		
		//clear hash maps and run GC
		hashMatrix.EntryReal.clear();
		hashMatrix.EntryImag.clear();
		Runtime.getRuntime().gc();
	}
}
