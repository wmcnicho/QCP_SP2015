package qcv1;
public abstract class MGate extends QGate{
	
	public abstract Matrix resultForOn();
	public abstract Matrix resultForOff();
	
	public void updateRegister(QRegister reg, int targetQbit){
		final int maxNum = reg.numOfStates() -1;
		final int mask = 1 << targetQbit;
		
		//create the gate in matrix representation
		Matrix gate = new Matrix(maxNum+1, maxNum+1);
		
		for (int i = 0; i <= maxNum; i++){
			calcElement(gate,i,mask);
		}
		reg.getAmplitude().multiplyBy(gate);
	}
	
	//generate matrix representing the control version of the gate
	public void updateRegister(QRegister reg, int [] controlQbit, int targetQbit){
		final int maxNum = reg.numOfStates() -1;
		final int mask = 1 << targetQbit;
		
		//create the gate in matrix representation
		Matrix gate = new Matrix(maxNum+1, maxNum+1);
		
		final int noOfControlQbits = controlQbit.length;
		for (int i = 0; i <= maxNum; i++){
			//check if one of the controlQbit is nonzero
			boolean allOn = true;
			for (int j = 0; j < noOfControlQbits; j++){
				int tempMask = 1 << controlQbit[j];
				if ((i & tempMask) != tempMask){//qubit is zero
					allOn = false;
					break;
				}
			}
			if (allOn){
				calcElement(gate,i,mask);
			} else {//the state is not affected since qubit is zero
				gate.setElement(i, i, 1.0);
			}
		}
		reg.getAmplitude().multiplyBy(gate);
	}
	
	//set the value for each element of the matrix
	private void calcElement(Matrix gate, int i, int mask){
		//check if that qubit is 0 or 1
		if ((i & mask) == mask){//qubit is 1
			gate.setElement(i, i, resultForOn().getElement(1,0));
			gate.setElement(i ^ mask, i, resultForOn().getElement(0,0));
		} else {//qubit is 0
			gate.setElement(i, i, resultForOff().getElement(0,0));
			gate.setElement(i ^ mask, i, resultForOff().getElement(1,0));
		}
	}
}
