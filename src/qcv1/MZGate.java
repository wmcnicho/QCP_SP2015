package qcv1;

import Matrix.*;

public class MZGate extends MGate{
	public MZGate(String matrixType, int [] controlQbits, int targetQbit, int numOfStates){
		//gives the result when the gate is applied to |0> (off) and |1> (on)
		Matrix offResult = MatrixFactory.create(2, 1, matrixType);
		offResult.setElement(0, 0, 1.0, 0.0);
		offResult.setElement(1, 0, 0.0, 0.0);
		
		Matrix onResult = MatrixFactory.create(2, 1, matrixType);
		onResult.setElement(0, 0, 0.0, 0.0);
		onResult.setElement(1, 0, -1.0, 0.0);
		
		initSingleTargetGate(matrixType, controlQbits, targetQbit, numOfStates, offResult, onResult);
	}
}