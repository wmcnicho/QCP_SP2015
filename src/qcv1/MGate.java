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
		
		//create the matrix
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
				//store the result
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
	 * @param reg Register storing the qubits
	 */
	public void applyGate(QRegister reg){
		reg.getAmplitude().multiplyBy(gate);
	}
}