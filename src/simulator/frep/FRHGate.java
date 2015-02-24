package simulator.frep;

import simulator.mrep.Matrix;


public class FRHGate extends FRGate{
	
	private Matrix onResult = new Matrix(2,1);
	private Matrix offResult = new Matrix(2,1);
	private final double factor = 1.0/Math.sqrt(2);
	
	public FRHGate(int targetQbit){
		super(null, targetQbit);
		initResult();
		init();
	}
	public FRHGate(int [] controlQbits, int targetQbit){
		super(controlQbits, targetQbit);
		initResult();
		init();
	}
	
	public void initResult(){
		onResult.setElement(0, 0, factor);
		onResult.setElement(1, 0, -factor);
		offResult.setElement(0, 0, factor);
		offResult.setElement(1, 0, factor);
	}
	
	public Matrix resultForOff(){
		return offResult;
	}
	public Matrix resultForOn(){
		return onResult;
	}
}
