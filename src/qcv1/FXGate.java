package qcv1;

import Matrix.*;

public class FXGate extends FGate{
	
	private Matrix onResult;
	private Matrix offResult;
	
	public FXGate(String matrixType, int [] controlQbits, int targetQbit){
		super(matrixType, controlQbits, targetQbit);
		initResults(matrixType);
	}
	
	public void initResults(String matrixType){
		offResult = MatrixFactory.create(2, 1, matrixType);
		offResult.setElement(0, 0, 0.0, 0.0);
		offResult.setElement(1, 0, 1.0, 0.0);
		
		onResult = MatrixFactory.create(2, 1, matrixType);
		onResult.setElement(0, 0, 1.0, 0.0);
		onResult.setElement(1, 0, 0.0, 0.0);
	}
	
	public Matrix resultForOff(){
		return offResult;
	}
	public Matrix resultForOn(){
		return onResult;
	}
}
