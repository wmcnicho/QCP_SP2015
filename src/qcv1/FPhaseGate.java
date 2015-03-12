package qcv1;

import Matrix.*;

public class FPhaseGate extends FGate{
	
	private Matrix onResult;
	private Matrix offResult;
	
	public FPhaseGate(String matrixType, int [] controlQbits, int targetQbit, double phase){
		super(matrixType, controlQbits, targetQbit);
		initResults(matrixType, phase);
	}
	
	public void initResults(String matrixType, double phase){
		offResult = MatrixFactory.create(2, 1, matrixType);
		offResult.setElement(0, 0, 1.0, 0.0);
		offResult.setElement(1, 0, 0.0, 0.0);
		
		onResult = MatrixFactory.create(2, 1, matrixType);
		onResult.setElement(0, 0, 0.0, 0.0);
		onResult.setElement(1, 0, Math.cos(phase), Math.sin(phase));
	}
	
	public Matrix resultForOff(){
		return offResult;
	}
	public Matrix resultForOn(){
		return onResult;
	}
}
