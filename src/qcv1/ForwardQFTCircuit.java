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
			System.out.println("num of states: " + numOfStates);
			System.out.println("hadamard on " + i);
			addGate(GateFactory.createHGate(rep, null, i, numOfStates));
			for (int j = 2; j <= i+1; j++){
				//System.out.println("i: " + i + " \tj: " + j + "\tcontrol: " + (numOfQubits-j));
				double phase = 2 * Math.PI / Math.pow(2, j);
				//System.out.println(phase / Math.PI);
				System.out.println("phase on " + i + " with control " + (numOfQubits-j) + " and phase " + (phase / Math.PI));
				addGate(GateFactory.createPhaseGate(rep, new int [] {numOfQubits-j}, i, numOfStates, phase));
			}
		}
		
		//swap the qubits
		int num = numOfQubits / 2;
		for (int i = 0; i < num; i++){
			System.out.println(i + " " + ((numOfQubits-1)-i));
			addGate(GateFactory.createSwapGate(rep, i, (numOfQubits-1)-i, numOfStates));
		}		
	}
	
	public void applyCircuit(QRegister reg){
		reg.setAmplitude(0,0,0);
		reg.setAmplitude(1,0,0);
		reg.setAmplitude(2,0,0);
		reg.setAmplitude(3,1/Math.sqrt(2),0);
		reg.setAmplitude(4,1/Math.sqrt(2),0);
		reg.setAmplitude(5,0,0);
		reg.setAmplitude(6,0,0);
		reg.setAmplitude(7,0,0);
		super.applyCircuit(reg);
		//System.out.println("OK!");
		//QViewModel.updateHistogramValues(reg.getProbabilities());
	}
	
}
