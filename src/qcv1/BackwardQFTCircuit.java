package qcv1;

/**
 * A gate by gate circuit that performs the inverse (backward) quantum Fourier transform (QFT)
 * @author Michael
 *
 */
public class BackwardQFTCircuit extends GateByGateCircuit{
	private int numOfQubits;
	private int numOfStates;
	private String gateRep;
	
	/**
	 * Create the circuit for backward QFT
	 * @param rep Gate representation
	 * @param numOfQbits Number of qubits in the register
	 */
	public BackwardQFTCircuit(String rep, int numOfQbits){
		this.numOfQubits = numOfQbits;
		this.numOfStates = (int) Math.pow(2, numOfQubits);
		this.gateRep = rep;
		
		//reverse the swap of the qubits
		int num = numOfQubits / 2;
		for (int i = 0; i < num; i++){
			addGate(GateFactory.createNOTGate(gateRep, new int [] {(numOfQubits-1)-i}, i, numOfStates));
			addGate(GateFactory.createNOTGate(gateRep, new int [] {i}, (numOfQubits-1)-i, numOfStates));
			addGate(GateFactory.createNOTGate(gateRep, new int [] {(numOfQubits-1)-i}, i, numOfStates));
		}	
		
		for (int i = 0; i < numOfQubits; i++){
			for (int j = i+1; j >= 2; j--){
				double phase = 2 * Math.PI / Math.pow(2, j);
				addGate(GateFactory.createPhaseGate(gateRep, new int [] {i+1-j}, i, numOfStates, -phase));
			}
			addGate(GateFactory.createHGate(gateRep, null, i, numOfStates));
		}	
	}
}
