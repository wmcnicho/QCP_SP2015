package qcv1;
import Matrix.*;


public class FRHGate2 extends FRGate2{
	public FRHGate2(int targetQbit){
		super(null, targetQbit);
		init();
	}
	public FRHGate2(int [] controlQbits, int targetQbit){
		super(controlQbits, targetQbit);
		init();
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
