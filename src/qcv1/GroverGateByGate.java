package qcv1;

public class GroverGateByGate extends GateByGateCircuit {
	private int targetIndex;
	private int numOfEntries;
	
	public GroverGateByGate(String gateRep, int num, int target){
		targetIndex = target;
		numOfEntries = num;
		
		
		
		MRegister.getInstance().setRegister((int)Math.sqrt(numOfEntries));
		
		//build the circuit for one loop
		
		//create the oracle
		class Oracle extends QGate{
			public Oracle(){
				super(null,0);
			}
			public void applyGate(){
				MRegister.getInstance().setAmplitude(targetIndex, 
					MRegister.getInstance().getAmplitude(targetIndex) * -1);
			}
		}
		
		addGate(new Oracle());
		
		//perform Hadamard on all qubits
		for (int i = 0; i < MRegister.getInstance().numOfQubit(); i++){
			addGate(new FRHGate2(null, i));
		}
		
		//create phase shift gate - perform the 2|0><0| - I operation
		class PhaseShiftGate extends QGate{
			public PhaseShiftGate(){
				super(null,0);
			}
			public void applyGate(){
				for (int j = 1; j < MRegister.getInstance().numOfStates(); j++){
					MRegister.getInstance().setAmplitude(j, 
							MRegister.getInstance().getAmplitude(j) * -1);
				}
			}
		}
		addGate(new PhaseShiftGate());
		for (int i = 0; i < MRegister.getInstance().numOfQubit(); i++){
			addGate(new FRHGate2(null, i));
		}
		
	}
	
	public void applyCircuit(){
		//need to apply approximately r = pi/4 sqrt(N) times
		final int iterations = (int) (Math.PI / 4.0 * Math.sqrt(numOfEntries));
		for (int i = 0; i < iterations; i++){
			super.applyCircuit();
		}
	}
}
