package simulator;

public class FRGrover extends GateByGateCircuit {
	private int targetIndex;
	private int numOfEntries;
	
	public FRGrover(int num, int target){
		targetIndex = target;
		numOfEntries = num;
		
		//build the circuit for one loop
		
		//create the oracle
		class Oracle implements QGate{
			public void applyGate(){
				MRegister.getInstance().setAmplitude(targetIndex, 
					MRegister.getInstance().getAmplitude(targetIndex) * -1);
			}
		}
		
		addGate(new Oracle());
		
		//perform Hadamard on all qubits
		for (int i = 0; i < MRegister.getInstance().numOfQubit(); i++){
			addGate(new FRHGate(null, i));
		}
		
		//create phase shift gate - perform the 2|0><0| - I operation
		class PhaseShiftGate implements QGate{
			public void applyGate(){
				for (int j = 1; j < MRegister.getInstance().numOfStates(); j++){
					MRegister.getInstance().setAmplitude(j, 
							MRegister.getInstance().getAmplitude(j) * -1);
				}
			}
		}
		addGate(new PhaseShiftGate());
		for (int i = 0; i < MRegister.getInstance().numOfQubit(); i++){
			addGate(getGate(1+i));
		}
		System.out.println("Gate set up completed");
	}
	
	public void applyCircuit(){
		//need to apply approximately r = pi/4 sqrt(N) times
		final int iterations = (int) (Math.PI / 4.0 * Math.sqrt(numOfEntries));
		for (int i = 0; i < iterations; i++){
			super.applyCircuit();
			//MRegister.getInstance().getAmplitude().printMatrix();
		}
	}
}
