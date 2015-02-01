package qcv1;


public class FRNOTGate extends FRGate {
	
	private int targetQbit;
	private int [] controlQbits = null;
	
	public FRNOTGate(int target){
		targetQbit = target;
	}
	public FRNOTGate(int [] controls, int target){
		controlQbits = controls;
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
	
	public int getTargetQbit(){return targetQbit;}
	public int [] getControlQbits(){return controlQbits;}
}
