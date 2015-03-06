package qcv1;

public class QFTCircuit extends GateByGateCircuit{
	private int numOfQubits;
	private int numOfStates;
	
	public QFTCircuit(String gateRep, int numOfQbits, int numOfStates){
		this.numOfQubits = numOfQbits;
		this.numOfStates = numOfStates;
	}
	
	/*public void applyCircuit(QRegister reg){
		for (int i = 0; i < )
	}*/
}
