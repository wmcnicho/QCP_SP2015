package qcv1;

import Matrix.Matrix;
import Matrix.MatrixFactory;

/**
 * Functional representation of the Pauli-Z gate
 * @author Michael Chiang
 *
 */
public class FZGate extends FGate{
	//store the results when the gate operates on a single qubit
	private Matrix onResult; //result for gate applied to state |1>
	private Matrix offResult; //result for gate applied to state |0>
	
	/**
	 * Construct a Pauli-Z gate in functional representation
	 * @param controlQbits Array of the positions of the controlled qubits
	 * @param targetQbit The position of the qubit that the gate is applied on
	 */
	public FZGate(int [] controlQbits, int targetQbit){
		super(controlQbits, targetQbit);
		/*
		 * store the result when the gate is applied to |0> (offResult) and |1> (onResult):
		 * Z|0> = |0>
		 * Z|1> = -|1>
		 */
		offResult = MatrixFactory.create(2, 1, "complex");
		offResult.setElement(0, 0, 1.0, 0.0);
		offResult.setElement(1, 0, 0.0, 0.0);
		
		onResult = MatrixFactory.create(2, 1, "complex");
		onResult.setElement(0, 0, 0.0, 0.0);
		onResult.setElement(1, 0, -1.0, 0.0);
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
		return "Z Gate";
	}
}
