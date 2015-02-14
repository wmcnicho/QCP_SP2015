package qcv1;


public class FRZGate extends FRGate {
	public FRZGate(int targetQbit){
		super(null, targetQbit);
	}
	public FRZGate(int [] controlQbits, int targetQbit){
		super(controlQbits, targetQbit);
	}
	
	public Matrix resultForOff(){
		Matrix m = new Matrix(2,1);
		m.setElement(0,0,1.0);
		m.setElement(1,0,0.0);
		return m;
	}
	public Matrix resultForOn(){
		Matrix m = new Matrix(2,1);
		m.setElement(0,0,0.0);
		m.setElement(1,0,-1.0);
		return m;
	}
}
