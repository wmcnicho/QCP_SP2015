package Matrix;

public class MatrixMultiply {

	//optimised gate-complex vector multiplication
	protected static Matrix GCMultiply(Matrix a, Matrix b) {

		//returns a complex vector
		Matrix out = MatrixFactory.create(a.row, b.column, "complex");

		int rowIndex;
		
		//loop over columns of a
		for(int j=0; j<a.column; j++){
			double reSum = 0.0;
			double imSum = 0.0;
			//index of first element of column j in the array
			int currentColIndex = 2*j;
			//get row index of element
			rowIndex = a.rowIndex[currentColIndex];
			//if element has been set
			if(rowIndex!=-1){
				//takes hermitian of the complex vector by adding - signs in the right places
				reSum += b.reMatrix[rowIndex]*a.reMatrix[currentColIndex];
				reSum -= b.imMatrix[rowIndex]*a.imMatrix[currentColIndex];
				imSum -= b.reMatrix[rowIndex]*a.imMatrix[currentColIndex];
				imSum -= b.imMatrix[rowIndex]*a.reMatrix[currentColIndex];
			}
			//move on to second element in the column
			currentColIndex++;
			rowIndex = a.rowIndex[currentColIndex];
			if(rowIndex!=-1){
				reSum += b.reMatrix[rowIndex]*a.reMatrix[currentColIndex];
				reSum -= b.imMatrix[rowIndex]*a.imMatrix[currentColIndex];
				imSum -= b.reMatrix[rowIndex]*a.imMatrix[currentColIndex];
				imSum -= b.imMatrix[rowIndex]*a.reMatrix[currentColIndex];
			}
			
			out.reMatrix[j]=reSum;
			//need to take hermitian again
			out.imMatrix[j]=imSum*-1;
		}
		return out;
	}

	//standard matrix multiplication, not much to say about it. always returns a complex matrix
	public static Matrix Multiply(Matrix a, Matrix b){
		Matrix out = MatrixFactory.create(a.row, b.column, "complex");
		for(int i=0; i<a.row; i++){
			for(int j=0; j<b.column; j++){
				double reSum = 0.0;
				double imSum = 0.0;
				for(int k=0; k<b.row; k++){
					reSum+=a.getReElement(i, k)*b.getReElement(k,j);
					reSum-=a.getImElement(i, k)*b.getImElement(k, j);
					imSum+=a.getImElement(i, k)*b.getReElement(k,j);
					imSum+=a.getReElement(i, k)*b.getImElement(k, j);
				}
				out.setReElement(i, j, reSum);
				out.setImElement(i, j, imSum);
			}
		}
		return out;
	}

}
