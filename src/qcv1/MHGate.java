package qcv1;
import Matrix.*;

public class MHGate extends MGate{
	public MHGate(int targetQbit){
		super(null, targetQbit);
	}
	public MHGate(int [] controlQbits, int targetQbit){
		super(controlQbits, targetQbit);
	}
	
	private final double factor = 1.0/Math.sqrt(2);
	public DenseMatrix resultForOff(){
		DenseMatrix m = new DenseMatrix(2,1);
		m.setElement(0,0,factor);
		m.setElement(1,0,factor);
		return m;
	}
	public DenseMatrix resultForOn(){
		DenseMatrix m = new DenseMatrix(2,1);
		m.setElement(0,0,factor);
		m.setElement(1,0,-factor);
		return m;
	}
}
