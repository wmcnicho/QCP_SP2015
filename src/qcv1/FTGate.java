package qcv1;

import Matrix.*;

/**
 * Functional representation of the pi/8 gate
 * @author Michael Chiang
 *
 */
public class FTGate extends FGate{
	//store the results when the gate operates on a single qubit
	private Matrix onResult; //result for gate applied to state |1>
	private Matrix offResult; //result for gate applied to state |0>
	
	/**
	 * Construct the pi/8 gate (T gate) in matrix representation
	 * @param controlQbits Array of the positions of the controlled qubits
	 * @param targetQbit The position of the qubit that the gate is applied on
	 */
	public FTGate(int [] controlQbits, int targetQbit){
		super(controlQbits, targetQbit);
		/*
		 * store the result when the gate is applied to |0> (offResult) and |1> (onResult):
		 * T|0> = |0>
		 * T|1> = exp(i(pi/4))|1>
		 */
		final double factor = 1.0/Math.sqrt(2);
		offResult = MatrixFactory.create(2, 1, "complex");
		offResult.setElement(0, 0, 1.0, 0.0);
		offResult.setElement(1, 0, 0.0, 0.0);
		
		onResult = MatrixFactory.create(2, 1, "complex");
		onResult.setElement(0, 0, 0.0, 0.0);
		onResult.setElement(1, 0, factor, factor);
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
		return "T Gate";
	}
}
