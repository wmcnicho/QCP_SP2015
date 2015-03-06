package qcv1;
import Matrix.*;


public class FRZGate extends FRGate {
	public FRZGate(int targetQbit){
		super(null, targetQbit);
	}
	public FRZGate(int [] controlQbits, int targetQbit){
		super(controlQbits, targetQbit);
	}
	
	public DenseMatrix resultForOff(){
		DenseMatrix m = new DenseMatrix(2,1);
		m.setElement(0,0,1.0);
		m.setElement(1,0,0.0);
		return m;
	}
	public DenseMatrix resultForOn(){
		DenseMatrix m = new DenseMatrix(2,1);
		m.setElement(0,0,0.0);
		m.setElement(1,0,-1.0);
		return m;
	}
}