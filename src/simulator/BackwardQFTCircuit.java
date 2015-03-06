package simulator;

public class BackwardQFTCircuit extends GateByGateCircuit{
	private int numOfQubits;
	private int numOfStates;
	private GateRep gateRep;
	
	public BackwardQFTCircuit(GateRep rep, int numOfQbits){
		this.numOfQubits = numOfQbits;
		this.numOfStates = (int) Math.pow(2, numOfQubits);
		this.gateRep = rep;
		
		for (int i = numOfQubits-1; i >= 0; i--){
			//System.out.println(i + " out");
			addGate(GateFactory.createHGate(gateRep, null, i, numOfStates));
			for (int j = 0; j < i; j++){
				//System.out.println(j + " in " + (numOfQubits - (j+1)));
				addGate(GateFactory.createPhaseGate(gateRep, 
						new int [] {i}, j, numOfStates, Math.pow(2, numOfQubits - (j+1))));
			}
		}
		
	}
	
	public void applyCircuit(QRegister reg){
		super.applyCircuit(reg);
	}
}
