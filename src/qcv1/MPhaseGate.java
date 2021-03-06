package qcv1;

import Matrix.Matrix;
import Matrix.MatrixFactory;

/**
 * Matrix representation of a general phase shift gate
 * @author Michael Chiang
 *
 */
public class MPhaseGate extends MGate{
	
	private double phase; //store the phase shift
	
	/**
	 * Construct a general phase shift gate in matrix representation
	 * @param matrixType Type of matrix (e.g. complex (dense), sparse, sparse gate)
	 * @param controlQbits Array of the positions of the controlled qubits
	 * @param targetQbit The position of the qubit that the gate is applied on
	 * @param numOfStates Number of basis states in the register
	 * @param phaseShift Phase shift of the gate
	 */
	public MPhaseGate(String matrixType, int [] controlQbits, int targetQbit, int numOfStates, double phaseShift){
		/*
		 * gives the result when the gate is applied to |0> (offResult) and |1> (onResult):
		 * P|0> = |0>
		 * P|1> = exp(i(phase))|1>
		 */
		phase = phaseShift;
		Matrix offResult = MatrixFactory.create(2, 1, matrixType);
		offResult.setElement(0, 0, 1.0, 0.0);
		offResult.setElement(1, 0, 0.0, 0.0);
		
		Matrix onResult = MatrixFactory.create(2, 1, matrixType);
		onResult.setElement(0, 0, 0.0, 0.0);
		onResult.setElement(1, 0, Math.cos(phase), Math.sin(phase));
		
		//call MGate to create the matrix representing this gate
		initGate(matrixType, controlQbits, targetQbit, numOfStates, offResult, onResult);
	}
	
	/**
	 * Get the gate type.
	 */
	public String gateType() {
		return String.format("Phase Gate - Phase: %.2f", phase);
	}
}