package qcv1;

import Matrix.Matrix;
import Matrix.MatrixFactory;

/**
 * Matrix representation of the Hadamard gate
 * @author Michael Chiang
 *
 */
public class MHGate extends MGate{
	/**
	 * Construct a Hadamard gate in matrix representation
	 * @param matrixType Type of matrix (e.g. complex (dense), sparse, sparse gate)
	 * @param controlQbits Array of the positions of the controlled qubits
	 * @param targetQbit The position of the qubit that the gate is applied on
	 * @param numOfStates Number of basis states in the register
	 */
	public MHGate(String matrixType, int [] controlQbits, int targetQbit, int numOfStates){
		/*
		 * gives the result when the gate is applied to |0> (offResult) and |1> (onResult):
		 * H |0> = 1/sqrt(2) (|0> + |1>)
		 * H |1> = 1/sqrt(2) (|0> - |1>)
		 */
		final double factor = 1.0/Math.sqrt(2);
		Matrix offResult = MatrixFactory.create(2, 1, matrixType);
		offResult.setElement(0, 0, factor, 0.0);
		offResult.setElement(1, 0, factor, 0.0);
		
		Matrix onResult = MatrixFactory.create(2, 1, matrixType);
		onResult.setElement(0, 0, factor, 0.0);
		onResult.setElement(1, 0, -factor, 0.0);
		
		//call MGate to create the matrix representing this gate
		initGate(matrixType, controlQbits, targetQbit, numOfStates, offResult, onResult);
	}
	
	/**
	 * Get the gate type.
	 */
	public String gateType() {
		return "H Gate";
	}
}