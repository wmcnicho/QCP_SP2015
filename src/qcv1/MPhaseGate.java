package qcv1;

import Matrix.*;

public class MPhaseGate extends MGate{
	
	private Matrix onResult;
	private Matrix offResult;
	
	//phase := pi / phaseInt
	public MPhaseGate(String matrixType, int [] controlQbits, int targetQbit, int numOfStates, double phase){
		super(matrixType, controlQbits, targetQbit, numOfStates);
		initResults(matrixType, phase);
		initGate(matrixType);
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