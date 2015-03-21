package qcv1;

public class ForwardQFTCircuit extends GateByGateCircuit {
	private int numOfQubits;
	private int numOfStates;
	private String gateRep;
	
	/**
	 * Create the circuit for forward QFT
	 * @param rep Gate representation
	 * @param numOfQbits Number of qubits in the register
	 * @param guiUpdate Whether or not to update the gui about the progress
	 * in operating the gates on the register
	 */
	public ForwardQFTCircuit(String rep, int numOfQbits, boolean guiUpdate){
		this.numOfQubits = numOfQbits;
		this.numOfStates = (int) Math.pow(2, numOfQubits);
		this.gateRep = rep;
		
		//whether or not to update the gui
		updateGui(guiUpdate);
		
		/*
		 * add the appropriate Hadamard and controlled phase shift gates to
		 * perform the forward QFT (see report for the circuit diagram) 
		 */
		for (int i = numOfQubits-1; i >= 0; i--){
			addGate(GateFactory.createHGate(gateRep, null, i, numOfStates));
			for (int j = 2; j <= i+1; j++){
				double phase = 2 * Math.PI / Math.pow(2, j);
				addGate(GateFactory.createPhaseGate(gateRep, new int [] {(i+1)-j}, i, numOfStates, phase));
			}
		}
		
		//swap the amplitudes of the basis states (i.e. |0> <-> |N-1>)
		int num = numOfQubits / 2;
		for (int i = 0; i < num; i++){
			//three control-not gates (with alernating control qubit) perform a swap operation
			addGate(GateFactory.createXGate(gateRep, new int [] {i}, (numOfQubits-1)-i, numOfStates));
			addGate(GateFactory.createXGate(gateRep, new int [] {(numOfQubits-1)-i}, i, numOfStates));
			addGate(GateFactory.createXGate(gateRep, new int [] {i}, (numOfQubits-1)-i, numOfStates));
		}		
	}
}
