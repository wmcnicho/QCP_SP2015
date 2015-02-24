package complex;

import simulator.*;

public class CFRHGate extends CFRGate{
	
	private Complex [] onResult = new Complex [2];
	private Complex [] offResult = new Complex [2];
	private final double factor = 1.0/Math.sqrt(2);
	
	public CFRHGate(int targetQbit){
		super(null, targetQbit);
		initResult();
		init();
	}
	public CFRHGate(int [] controlQbits, int targetQbit){
		super(controlQbits, targetQbit);
		initResult();
		init();
	}
	
	public void initResult(){
		onResult[0] = new Complex(factor,0);
		onResult[1] = new Complex(-1.0*factor,0);
		offResult[0] = new Complex(factor,0);
		offResult[1] = new Complex(factor,0);
	}
	
	public Complex [] resultForOff(){
		return offResult;
	}
	public Complex [] resultForOn(){
		return onResult;
	}
}
