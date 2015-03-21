package qcv1;

import Matrix.Matrix;
import Matrix.MatrixFactory;

/**
 * Functional representation of a general phase shift gate
 * @author Michael Chiang
 *
 */
public class FPhaseGate extends FGate{
	//store the results when the gate operates on a single qubit
	private Matrix onResult; //result for gate applied to state |1>
	private Matrix offResult; //result for gate applied to state |0>
	private double phase; //store the phase shift
	
	/**
	 * Construct a general phase shift gate in matrix representation
	 * @param controlQbits Array of the positions of the controlled qubits
	 * @param targetQbit The position of the qubit that the gate is applied on
	 * @param phaseShift Phase shift of the gate
	 */
	public FPhaseGate(int [] controlQbits, int targetQbit, double phaseShift){
		super(controlQbits, targetQbit);
		/*
		 * store the result when the gate is applied to |0> (offResult) and |1> (onResult):
		 * P|0> = |0>
		 * P|1> = exp(i(phase))|1>
		 */
		phase = phaseShift;
		offResult = MatrixFactory.create(2, 1, "complex");
		offResult.setElement(0, 0, 1.0, 0.0);
		offResult.setElement(1, 0, 0.0, 0.0);
		
		onResult = MatrixFactory.create(2, 1, "complex");
		onResult.setElement(0, 0, 0.0, 0.0);
		onResult.setElement(1, 0, Math.cos(phase), Math.sin(phase));
	}

	/**
	 * Return the result for gate applied to state |0>
	 */
	public Matrix resultForOff(){
		return offResult;
	}
	/**
	 * Return the result for gate applied to state |1>
	 */
	public Matrix resultForOn(){
		return onResult;
	}
	
	/**
	 * Get the gate type.
	 */
	public String gateType() {
		return String.format("Phase Gate - Phase: %.2f", phase);
	}
}
