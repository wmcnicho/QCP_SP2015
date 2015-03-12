package qcv1;

import Matrix.*;

public class FHGate extends FGate{
	
	private Matrix onResult;
	private Matrix offResult;
	
	public FHGate(String matrixType, int [] controlQbits, int targetQbit){
		super(matrixType, controlQbits, targetQbit);
		initResults(matrixType);
	}
	
	public void initResults(String matrixType){
		final double factor = 1.0/Math.sqrt(2);
		offResult = MatrixFactory.create(2, 1, matrixType);
		offResult.setElement(0, 0, factor, 0.0);
		offResult.setElement(1, 0, factor, 0.0);
		
		onResult = MatrixFactory.create(2, 1, matrixType);
		onResult.setElement(0, 0, factor, 0.0);
		onResult.setElement(1, 0, -factor, 0.0);
	}
	
	public Matrix resultForOff(){
		return offResult;
	}
	public Matrix resultForOn(){
		return onResult;
	}
}
