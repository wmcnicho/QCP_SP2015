package qcv1;

public class ForwardQFTCircuit implements QCircuit {
	private int numOfStates;
	private String gateRep;
	
	public ForwardQFTCircuit(String rep, int numOfStates){
		this.numOfStates = numOfStates;
		this.gateRep = rep;
	}
	
	public void applyCircuit(QRegister reg){
		for (int i = 0; i < numOfStates; i++){
			for (int j = 0; j < i; j++){
				GateFactory.createPhaseGate(gateRep, new int [] {i}, j, numOfStates, 
						Math.pow(2, numOfStates - (j+1))).applyGate(reg);
			}
			GateFactory.createHGate(gateRep, null, i, numOfStates).applyGate(reg);
		}
	}
	
}
