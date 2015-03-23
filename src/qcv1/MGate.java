package qcv1;

import Matrix.Matrix;
import Matrix.MatrixFactory;

/**
 * Matrix representation of a single qubit gate
 * @author Michael Chiang
 *
 */
public abstract class MGate implements QGate{
	private Matrix gate = null; //store the matrix that represents the gate
	
	/**
	 * Initialize the gate by creating the matrix that represents the
	 * linear operation associated with the gate
	 * @param type Type of matrix (e.g. complex (dense), sparse, sparse gate)
	 * @param controls Array of the positions of the control qubits
	 * @param target The position of the qubit that the gate is applied on
	 * @param numOfStates Number of basis states in the register
	 * @param offResult Result when gate is applied to state |0>
	 * @param onResult Result when gate is applied to state |1>
	 */
	public void initGate(String type, int [] controls, int target, int numOfStates,
			Matrix offResult, Matrix onResult){
		
		final int maxNum = numOfStates -1;
		//create a mask for determining whether the target qubit is zero or one
		final int mask = 1 << target; 
		
		//create the matrix
		gate = MatrixFactory.create(numOfStates, numOfStates, type);
		
		//check if there are any control qubits
		if (controls != null){//have control qubits
			//find the elements in each column of the matrix
			for (int i = 0; i <= maxNum; i++){
				//check if any of the control qubit is zero
				boolean allOn = true;
				for (int j = 0; j < controls.length; j++){
					int tempMask = 1 << controls[j];
					if ((i & tempMask) != tempMask){//a control qubit is zero
						allOn = false;
						break;
					}
				}
				/*
				 * find the two elements in this column (column i) of the matrix
				 * using the results when gate operates on a single qubit and 
				 * checking whether the target qubit is zero or one.
				 */
				if (allOn){
					if ((i & mask) == mask){//qubit is 1
						gate.setElement(i, i, onResult.getElement(1,0));
						gate.setElement(i ^ mask, i, onResult.getElement(0,0));
					} else {//qubit is 0
						gate.setElement(i, i, offResult.getElement(0,0));
						gate.setElement(i ^ mask, i, offResult.getElement(1,0));
					}
				} else {
					/*
					 * the state is not affected not all control qubits are non zero. 
					 * this means the only element in this column (column i) must be G(i,i) = 1
					 */
					gate.setElement(i, i, 1.0, 0.0);
				}
			}
		} else {//no control qubits
			//find the two elements in each column of the matrix
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
	 * Apply the gate to the register (i.e perform the matrix multiplication of 
	 * the gate with the state vector of the register)
	 * @param reg Register storing the qubits
	 */
	public void applyGate(QRegister reg){
		reg.getAmplitude().multiplyBy(gate);
	}
}