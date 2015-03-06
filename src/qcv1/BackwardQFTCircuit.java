package qcv1;

public class BackwardQFTCircuit implements QCircuit{
	private int numOfStates;
	private String gateRep;
	
	public BackwardQFTCircuit(String rep, int numOfStates){
		this.numOfStates = numOfStates;
		this.gateRep = rep;
	}
	
	public void applyCircuit(QRegister reg){
		for (int i = numOfStates-1; i >= 0; i--){
			System.out.println(i);
			GateFactory.createHGate(gateRep, null, i, numOfStates).applyGate(reg);
			for (int j = i-1; j >= 0; j++){
				GateFactory.createPhaseGate(gateRep, new int [] {i}, j, numOfStates, 
						Math.pow(2, numOfStates - (j+1))).applyGate(reg);
			}
		}
	}
}
