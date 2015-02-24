package simulator.frep;

import simulator.*;

public class CFRGrover extends GateByGateCircuit {
	private int targetIndex;
	private int numOfEntries;
	
	public CFRGrover(int num, int target){
		targetIndex = target;
		numOfEntries = num;
		
		//build the circuit for one loop
		
		//create the oracle
		class Oracle implements QGate{
			public void applyGate(){
				CRegister.getInstance().getAmplitude(targetIndex).multiply(-1);
			}
		}
		
		addGate(new Oracle());
		
		//perform Hadamard on all qubits
		for (int i = 0; i < CRegister.getInstance().numOfQubit(); i++){
			addGate(new CFRHGate(null, i));
		}
		
		//create phase shift gate - perform the 2|0><0| - I operation
		class PhaseShiftGate implements QGate{
			public void applyGate(){
				for (int j = 1; j < CRegister.getInstance().numOfStates(); j++){
					CRegister.getInstance().getAmplitude(j).multiply(-1);
				}
			}
		}
		addGate(new PhaseShiftGate());
		for (int i = 0; i < CRegister.getInstance().numOfQubit(); i++){
			addGate(getGate(1+i));
		}
		System.out.println("Gate set up completed");
	}
	
	public void applyCircuit(){
		//need to apply approximately r = pi/4 sqrt(N) times
		final int iterations = (int) (Math.PI / 4.0 * Math.sqrt(numOfEntries));
		for (int i = 0; i < iterations; i++){
			super.applyCircuit();
			//CRegister.getInstance().getAmplitude().printMatrix();
		}
	}
}
