package qcv1;

import Matrix.*;

public class FPhaseGate extends FGate{
	
	private Matrix onResult;
	private Matrix offResult;
	
	public FPhaseGate(int [] controlQbits, int targetQbit, double phase){
		super(controlQbits, targetQbit);
		initResults(phase);
	}
	
	public void initResults(double phase){
		offResult = MatrixFactory.create(2, 1, "complex");
		offResult.setElement(0, 0, 1.0, 0.0);
		offResult.setElement(1, 0, 0.0, 0.0);
		
		onResult = MatrixFactory.create(2, 1, "complex");
		onResult.setElement(0, 0, 0.0, 0.0);
		onResult.setElement(1, 0, Math.cos(phase), Math.sin(phase));
	}
	
	public Matrix resultForOff(){
		return offResult;
	}
	public Matrix resultForOn(){
		return onResult;
	}
	
	public String gateType() {
		return "Phase Gate";
	}
}
