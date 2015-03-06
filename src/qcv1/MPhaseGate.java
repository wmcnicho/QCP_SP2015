package qcv1;

import Matrix.*;

public class MPhaseGate extends MGate{
	
	private Matrix onResult;
	private Matrix offResult;
	private double phase;
	//phase := pi / phaseInt
	public MPhaseGate(String matrixType, int [] controlQbits, int targetQbit, int numOfStates, double phaseInt){
		super(matrixType, controlQbits, targetQbit, numOfStates);
		phase = Math.PI/phaseInt;
		initResults(matrixType);
		initGate(matrixType);
	}
	
	public void initResults(String matrixType){
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