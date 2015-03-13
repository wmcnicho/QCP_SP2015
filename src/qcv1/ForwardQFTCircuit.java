package qcv1;

import Gui.QViewModel;
import Matrix.ComplexMatrix;

public class ForwardQFTCircuit extends GateByGateCircuit {
	private int numOfQubits;
	private int numOfStates;
	private String gateRep;
	
	public ForwardQFTCircuit(String rep, int numOfQbits){
		this.numOfQubits = numOfQbits;
		this.numOfStates = (int) Math.pow(2, numOfQubits);
		this.gateRep = rep;
		
		for (int i = numOfQubits-1; i >= 0; i--){
			System.out.println("hadamard on " + i);
			addGate(GateFactory.createHGate(rep, null, i, numOfStates));
			for (int j = 2; j <= i+1; j++){
				double phase = 2 * Math.PI / Math.pow(2, j);
				System.out.println("phase on " + i + " with control " + ((i+1)-j) + " and phase " + (phase / Math.PI));
				addGate(GateFactory.createPhaseGate(rep, new int [] {(i+1)-j}, i, numOfStates, phase));
			}
		}
		
		//swap the qubits
		int num = numOfQubits / 2;
		for (int i = 0; i < num; i++){
			System.out.println(i + " " + ((numOfQubits-1)-i));
			//recall three c-not gate performs a swap operation
			addGate(GateFactory.createNOTGate(rep, new int [] {i}, (numOfQubits-1)-i, numOfStates));
			addGate(GateFactory.createNOTGate(rep, new int [] {(numOfQubits-1)-i}, i, numOfStates));
			addGate(GateFactory.createNOTGate(rep, new int [] {i}, (numOfQubits-1)-i, numOfStates));
			//addGate(GateFactory.createSwapGate(rep, i, (numOfQubits-1)-i, numOfStates));
		}		
	}
	
	public void applyCircuit(QRegister reg){
		
		super.applyCircuit(reg);
		//System.out.println("OK!");
		//QViewModel.updateHistogramValues(reg.getProbabilities());
	}
	
}
