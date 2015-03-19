package qcv1;

import Matrix.Matrix;
import Matrix.MatrixFactory;

/**
 * Matrix representation of the pi/8 phase shift gate (T gate)
 * @author Michael
 *
 */
public class MTGate extends MGate{
	/**
	 * Construct a pi/8 phase shift gate (T gate) in matrix representation
	 * @param matrixType Type of matrix (e.g. complex (dense), sparse, sparse gate)
	 * @param controlQbits Array of the positions of the controlled qubits
	 * @param targetQbit The position of the qubit that the gate is applied on
	 * @param numOfStates Number of basis states in the register
	 */
	public MTGate(String matrixType, int [] controlQbits, int targetQbit, int numOfStates){
		/*
		 * gives the result when the gate is applied to |0> (offResult) and |1> (onResult):
		 * T|0> = |0>
		 * T|1> = exp(i(pi/4))|1>
		 */
		final double factor = 1.0/Math.sqrt(2);
		Matrix offResult = MatrixFactory.create(2, 1, matrixType);
		offResult.setElement(0, 0, 1.0, 0.0);
		offResult.setElement(1, 0, 0.0, 0.0);
		
		Matrix onResult = MatrixFactory.create(2, 1, matrixType);
		onResult.setElement(0, 0, 0.0, 0.0);
		onResult.setElement(1, 0, factor, factor);
		
		//call MGate to create the matrix representing this gate
		initSingleTargetGate(matrixType, controlQbits, targetQbit, numOfStates, offResult, onResult);
	}
	
	/**
	 * Get the gate type.
	 */
	public String gateType() {
		return "T Gate";
	}
}