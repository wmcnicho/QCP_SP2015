package simulator;

import simulator.mrep.Matrix;
import simulator.mrep.MatrixType;

public class GroverGateByGate extends GateByGateCircuit {
	private int [] targets;
	private int numOfEntries;
	private GateRep gateRep;
	private Matrix solutionVector;
	private Matrix nonSolutionVector;
	
	public GroverGateByGate(GateRep rep, int num, int [] targetIndices, int numOfQubits, int numOfStates){
		gateRep = rep;
		targets = targetIndices;
		numOfEntries = num;
		
		//testing rotation
		solutionVector = Matrix.create(MatrixType.SPARSE, 1, numOfStates);
		nonSolutionVector = Matrix.create(MatrixType.SPARSE, 1, numOfStates);
		
		double solutionFactor = 1.0/Math.sqrt(targets.length);
		double nonSolutionFactor = 1.0/Math.sqrt(numOfStates - targets.length);
		int j = 0;
		for (int i = 0; i < numOfStates; i++){
			if (j < targets.length && i == targets[j]){
				solutionVector.set(i,solutionFactor, 0.0);
				j++;
			} else {
				nonSolutionVector.set(i, nonSolutionFactor, 0.0);
			}
		}
		
		//build the circuit for one loop
		//create the oracle
		class Oracle implements QGate{
			public void applyGate(QRegister reg){
				for (int i = 0; i < targets.length; i++){
					reg.setAmplitude(targets[i], 
							Complex.multiply(-1, reg.getAmplitude(targets[i])));
				}
			}
		}
		
		addGate(new Oracle());
		
		//perform Hadamard on all qubits
		for (int i = 0; i < numOfQubits; i++){
			addGate(GateFactory.createHGate(gateRep, null, i, numOfStates));
		}
		
		//create phase shift gate - perform the 2|0><0| - I operation
		class PhaseShiftGate implements QGate{
			public void applyGate(QRegister reg){
				for (int j = 1; j < reg.numOfStates(); j++){
					reg.setAmplitude(j, Complex.multiply(-1,reg.getAmplitude(j)));
				}
			}
		}
		addGate(new PhaseShiftGate());
		for (int i = 0; i < numOfQubits; i++){
			addGate(getGate(1+i));
		}
		System.out.println("Gate setup completed.");
	}
	
	public void applyCircuit(QRegister reg){
		//need to apply approximately r = pi/4 sqrt(N) times
		final int iterations = (int) (Math.PI / 4.0 * Math.sqrt((double) numOfEntries/targets.length));
		reg.setEqualAmplitude();
		for (int i = 0; i < iterations; i++){
			super.applyCircuit(reg);
			Matrix solComp = Matrix.multiply(MatrixType.SPARSE, solutionVector, reg.getAmplitude());
			solComp.printMatrix();
			Matrix nonSolComp = Matrix.multiply(MatrixType.SPARSE, nonSolutionVector, reg.getAmplitude());
			//solComp.printMatrix();
			//nonSolComp.printMatrix();
			System.out.printf("(%.6f %.6f)\n",solComp.getReal(0),nonSolComp.getReal(0));
		}
	}
}
