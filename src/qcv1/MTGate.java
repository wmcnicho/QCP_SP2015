package qcv1;

import Matrix.*;

public class MTGate extends MGate{
	
	private Matrix onResult;
	private Matrix offResult;
	
	public MTGate(String matrixType, int [] controlQbits, int targetQbit, int numOfStates){
		super(matrixType, controlQbits, targetQbit, numOfStates);
		initResults(matrixType);
		initGate(matrixType);
	}
	
	public void initResults(String matrixType){
		final double factor = 1.0/Math.sqrt(2);
		offResult = MatrixFactory.create(2, 1, matrixType);
		offResult.setElement(0, 0, 1.0, 0.0);
		offResult.setElement(1, 0, 0.0, 0.0);
		
		onResult = MatrixFactory.create(2, 1, matrixType);
		onResult.setElement(0, 0, 0.0, 0.0);
		onResult.setElement(1, 0, factor, factor);
	}
	
	public Matrix resultForOff(){
		return offResult;
	}
	public Matrix resultForOn(){
		return onResult;
	}
}