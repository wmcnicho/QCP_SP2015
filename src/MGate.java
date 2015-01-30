

public abstract class MGate{
	
	public abstract Matrix resultForOn();
	public abstract Matrix resultForOff();

	public Matrix output(int noOfQbits, int targetPos){
		int maxNum = (int) Math.pow(2, noOfQbits) - 1;
		int mask = 1 << targetPos;
		
		//create the gate in matrix representation
		Matrix gate = new Matrix(maxNum+1, maxNum+1);
		
		for (int i = 0; i <= maxNum; i++){
			//check if that qubit is 0 or 1
			if ((i & mask) == mask){//qubit is 1
				gate.setElement(i, i, resultForOn().getElement(1,0));
				gate.setElement(i ^ mask, i, resultForOn().getElement(0,0));
			} else {//qubit is 0
				gate.setElement(i, i, resultForOff().getElement(0,0));
				gate.setElement(i ^ mask, i, resultForOff().getElement(1,0));
			}
		}
		//gate.printMatrix();
		return gate;
	}
}
