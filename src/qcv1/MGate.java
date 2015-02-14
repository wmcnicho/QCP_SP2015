package qcv1;
public abstract class MGate extends QGate{
	
	private Matrix gate; //store the matrix that represents the gate
	
	//abstract methods
	public abstract Matrix resultForOn();
	public abstract Matrix resultForOff();
	
	//constructor
	public MGate (int [] controlQbits, int targetQbit){
		super(controlQbits, targetQbit);
		
		//find the matrix representation
		final int maxNum = MRegister.getInstance().numOfStates() -1;
		final int mask = 1 << target;
		
		//create the gate in matrix representation
		gate = new Matrix(maxNum+1, maxNum+1);
		
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
					calcElement(gate,i,mask);
				} else {//the state is not affected since qubit is zero
					gate.setElement(i, i, 1.0);
				}
			}
		} else {
			for (int i = 0; i <= maxNum; i++){
				calcElement(gate,i,mask);
			}
		}
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
	
	public void applyGate(){
		MRegister.getInstance().getAmplitude().multiplyBy(gate);
	}
}
