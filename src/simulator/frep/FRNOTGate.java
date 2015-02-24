package simulator.frep;

import simulator.mrep.Matrix;


public class FRNOTGate extends FRGate {
	public FRNOTGate(int targetQbit){
		super(null, targetQbit);
	}
	public FRNOTGate(int [] controlQbits, int targetQbit){
		super(controlQbits, targetQbit);
	}
	
	public Matrix resultForOff(){
		Matrix m = new Matrix(2,1);
		m.setElement(0,0,0.0);
		m.setElement(1,0,1.0);
		return m;
	}
	public Matrix resultForOn(){
		Matrix m = new Matrix(2,1);
		m.setElement(0,0,1.0);
		m.setElement(1,0,0.0);
		return m;
	}
}
