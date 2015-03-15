package qcv1;

import Matrix.*;

public class MPhaseGate extends MGate{
	//phase := pi / phaseInt
	public MPhaseGate(String matrixType, int [] controlQbits, int targetQbit, int numOfStates, double phase){
		Matrix offResult = MatrixFactory.create(2, 1, matrixType);
		offResult.setElement(0, 0, 1.0, 0.0);
		offResult.setElement(1, 0, 0.0, 0.0);
		
		Matrix onResult = MatrixFactory.create(2, 1, matrixType);
		onResult.setElement(0, 0, 0.0, 0.0);
		onResult.setElement(1, 0, Math.cos(phase), Math.sin(phase));
		
		initSingleTargetGate(matrixType, controlQbits, targetQbit, numOfStates, offResult, onResult);
	}
	
	public String gateType() {
		return "Phase Gate";
	}
}