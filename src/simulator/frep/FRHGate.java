package simulator.frep;
import Matrix.*;


public class FRHGate extends FRGate{
	public FRHGate(int targetQbit){
		super(null, targetQbit);
	}
	public FRHGate(int [] controlQbits, int targetQbit){
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
