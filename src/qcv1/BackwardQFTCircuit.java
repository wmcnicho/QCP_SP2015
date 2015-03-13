package qcv1;

public class BackwardQFTCircuit extends GateByGateCircuit{
	private int numOfQubits;
	private int numOfStates;
	private String gateRep;
	
	public BackwardQFTCircuit(String rep, int numOfQbits){
		this.numOfQubits = numOfQbits;
		this.numOfStates = (int) Math.pow(2, numOfQubits);
		this.gateRep = rep;
		
		//reverse the swap of the qubits
		int num = numOfQubits / 2;
		for (int i = 0; i < num; i++){
			//System.out.println(i + " " + ((numOfQubits-1)-i));
			addGate(GateFactory.createSwapGate(rep, i, (numOfQubits-1)-i, numOfStates));
		}	
		
		for (int i = 0; i < numOfQubits; i++){
			for (int j = i+1; j >= 2; j--){
				double phase = 2 * Math.PI / Math.pow(2, j);
				//System.out.println("phase on " + i + " with control " + (i+1-j) + " and phase " + (phase / Math.PI));
				addGate(GateFactory.createPhaseGate(rep, new int [] {i+1-j}, i, numOfStates, -phase));
			}
			//System.out.println("hadamard on " + i);
			addGate(GateFactory.createHGate(rep, null, i, numOfStates));
		}	
	}
	
	public void applyCircuit(QRegister reg){
		super.applyCircuit(reg);
	}
}
