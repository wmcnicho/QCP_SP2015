package qcv1;

/**
 * A gate by gate circuit that performs the inverse (backward) quantum Fourier transform (QFT)
 * @author Michael Chiang
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
	 * @param guiUpdate Whether or not to update the gui about the progress
	 * in operating the gates on the register
	 */
	public BackwardQFTCircuit(String rep, int numOfQbits, boolean guiUpdate){
		this.numOfQubits = numOfQbits;
		this.numOfStates = (int) Math.pow(2, numOfQubits);
		this.gateRep = rep;
		
		//whether or not to update the gui
		updateGui(guiUpdate);
		
		//perform the reverse procedure for forward QFT (see ForwardQFTCircuit)
		//reverse the swap of the amplitudes of the basis states (i.e. |N-1> <-> |0>)
		int num = numOfQubits / 2;
		for (int i = 0; i < num; i++){
			//three control-not gates (with alernating control qubit) perform a swap operation
			addGate(GateFactory.createXGate(gateRep, new int [] {(numOfQubits-1)-i}, i, numOfStates));
			addGate(GateFactory.createXGate(gateRep, new int [] {i}, (numOfQubits-1)-i, numOfStates));
			addGate(GateFactory.createXGate(gateRep, new int [] {(numOfQubits-1)-i}, i, numOfStates));
		}	
		
		/*
		 * add the appropriate Hadamard and controlled phase shift gates to
		 * perform the backward QFT (see report for the circuit diagram) 
		 */
		for (int i = 0; i < numOfQubits; i++){
			for (int j = i+1; j >= 2; j--){
				double phase = 2 * Math.PI / Math.pow(2, j);
				addGate(GateFactory.createPhaseGate(gateRep, new int [] {i+1-j}, i, numOfStates, -phase));
			}
			addGate(GateFactory.createHGate(gateRep, null, i, numOfStates));
		}			
	}
}
