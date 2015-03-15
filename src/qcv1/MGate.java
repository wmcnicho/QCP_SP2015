package qcv1;

import Matrix.MatrixFactory;
import Matrix.Matrix;

/**
 * The MGate class represents a gate with a matrix representation. 
 *
 */

public abstract class MGate implements QGate{
	private String  matrixType = null;
	protected Matrix gate = null; //store the matrix that represents the gate
	
	//empty constructor
	public MGate(){}
	
	/**
	 * Create
	 * 
	 * @param m Matrix that represents the linear operation associated with the gate
	 */
	/*public MGate(Matrix m){
		gate = m.getClone();
	}
	
	public MGate(MGate m){
		this(m.gate);
	}*/
	
	/**
	 * Initialize the gate by creating the matrix that represents the
	 * linear operation associated with the gate
	 * 
	 * @param type Type of matrix representation of the gate
	 */
	public void initSingleTargetGate(String type, int [] controls, int target, int numOfStates,
			Matrix offResult, Matrix onResult){
		//find the matrix representation
		final int maxNum = numOfStates -1;
		final int mask = 1 << target;
		
		//create the matrix repres
		gate = MatrixFactory.create(numOfStates, numOfStates, type);
		
		//check if there are any control qubits
		if (controls != null){
			for (int i = 0; i <= maxNum; i++){
				//check if one of the controlQbit is nonzero
				boolean allOn = true;
				for (int j = 0; j < controls.length; j++){
					int tempMask = 1 << controls[j];
					if ((i & tempMask) != tempMask){//qubit is zero
						allOn = false;
						break;
					}
				}
				if (allOn){
					if ((i & mask) == mask){//qubit is 1
						gate.setElement(i, i, onResult.getElement(1,0));
						gate.setElement(i ^ mask, i, onResult.getElement(0,0));
					} else {//qubit is 0
						gate.setElement(i, i, offResult.getElement(0,0));
						gate.setElement(i ^ mask, i, offResult.getElement(1,0));
					}
				} else {//the state is not affected since qubit is zero
					gate.setElement(i, i, 1.0, 0.0);
				}
			}
		} else {
			for (int i = 0; i <= maxNum; i++){
				if ((i & mask) == mask){//qubit is 1
					gate.setElement(i, i, onResult.getElement(1,0));
					gate.setElement(i ^ mask, i, onResult.getElement(0,0));
				} else {//qubit is 0
					gate.setElement(i, i, offResult.getElement(0,0));
					gate.setElement(i ^ mask, i, offResult.getElement(1,0));
				}
			}
		}
	}
	
	/**
	 * Perform the matrix operation of applying the gate onto the register
	 * 
	 * @param reg Register of the qubits
	 */
	public void applyGate(QRegister reg){
		reg.getAmplitude().multiplyBy(gate);
	}
	
	/**
	 * Combine two gates into a single composite gate
	 * @param m1
	 * @param m2
	 * @return
	 */
/*	public static MGate combineGate(MGate m1, MGate m2){
		MGate combined = new MGate(m2);
		combined.gate.multiplyBy(m1.gate);
		return combined;
	}*/
	
	/**
	 * Combine a list of matrix representation gates (MGates) into a single gate
	 * @param m Array of MGates stored in the order of operation (e.g. [A, B, C] should
	 * stored for the operation CBA|state>
	 * @return A MGate that performs the same operation as the array of the MGates
	 */
	/*public static MGate combineGate(MGate [] m){
		MGate combined = new MGate(m[0]);
		for (int i = 1; i < m.length; i++){
			combined.gate.multiplyBy(m[i].gate);
		}
		return combined;
	}*/
}