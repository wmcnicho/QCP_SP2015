package Matrix;

public class TComplexMatrix extends ComplexMatrix{

	protected TComplexMatrix(ComplexMatrix m) {
		super(m.row, m.column);
		this.reMatrix = m.reMatrix;
		this.imMatrix = m.imMatrix;
	}
	
	public void setElement(int i, int j, double[] value){
		super.setElement(j, i, value);
	}

	public void setReElement(int i, int j, double value){
		super.setReElement(j, i, value);
	}

	public void setImElement(int i, int j, double value){
		super.setImElement(j, i, value);
	}

	public double getReElement(int i, int j){
		return super.getReElement(j, i);
	}
	public double getImElement(int i, int j){
		return super.getImElement(j, i)*-1;
	}
	public double[] getElement(int i, int j){
		return new double[]{super.getReElement(j, i),super.getImElement(j, i)*-1} ;
	}

}
