package qcv1;

import Matrix.*;
import Gui.QViewModel;

/**
 * GroverGateByGate
 * An implementation of quantum search algorithm (Grover's algorithm) 
 * @author Michael
 *
 */
public class GroverGateByGate extends GateByGateCircuit {
	private int [] targets; //store the target indices for the oracle
	private int numOfEntries;
	private String gateRep;
	private Matrix solutionVector;
	private Matrix nonSolutionVector;
	
	/**
	 * Create the Grover circuit
	 * 
	 * @param rep Representation of the gate (complex matrix, sparse matrix, functional)
	 * @param num 
	 * @param targetIndices The target/solution states of the search problem
	 * @param numOfQubits 
	 * @param numOfStates
	 */
	public GroverGateByGate(String rep, int num, int [] targetIndices,
			int numOfQubits, int numOfStates){
		
		gateRep = rep;
		targets = targetIndices;
		numOfEntries = num;
		
		//create the vectors that span the plane where the state vector is rotating on
		solutionVector = MatrixFactory.create(1, numOfStates, rep);
		nonSolutionVector = MatrixFactory.create(1, numOfStates, rep);
		double solutionFactor = 1.0/Math.sqrt(targets.length);
		double nonSolutionFactor = 1.0/Math.sqrt(numOfStates - targets.length);
		
		int j = 0;
		for (int i = 0; i < numOfStates; i++){
			if (j < targets.length && i == targets[j]){
				solutionVector.setElement(0, i, solutionFactor, 0.0);
				j++;
			} else {
				nonSolutionVector.setElement(0, i, nonSolutionFactor, 0.0);
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
			public String gateType() {
				return "Oracle Gate";
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
			public String gateType() {
				return "Phase Shift Gate";
			}
		}
		addGate(new PhaseShiftGate());
		for (int i = 0; i < numOfQubits; i++){
			addGate(getGate(1+i));
		}
	}
	
	/**
	 * Perform Grover's algorithm on a register.
	 * 
	 * @param reg Quantum register for applying the circuit
	 */
	public void applyCircuit(QRegister reg){
		reg.setEqualAmplitude();
		
		/*
		 * compute the number of Grover's iterations required to maximize
		 * the probability of measuring the target/solution states. This
		 * is approximately r = pi/4 sqrt(N/M) times, where N = number of entries
		 * in the list and M = number of solutions.
		 */
		final int iterations = (int) (Math.PI / 4.0 * 
				Math.sqrt((double) numOfEntries/ targets.length));
		
		
		for (int i = 0; i < iterations; i++){
			//apply the gate circuit for each Grover iteration
			super.applyCircuit(reg);
			
			/*
			 * compute the projection of the state vector on the solutions and non-solutions 
			 * vectors:
			 */
			Matrix solComp = MatrixMultiply.Multiply(solutionVector, reg.getAmplitude());
			Matrix nonSolComp = MatrixMultiply.Multiply(nonSolutionVector, reg.getAmplitude());
			
			//compute the percentage of the calculations done
			int percent = (int) ((double) i / iterations * 100);
			
			//update gui 
			QViewModel.updateLoadingBar(percent);
			QViewModel.updateHistogramValues(reg.getProbabilities());
			QViewModel.setVector(nonSolComp.getReElement(0, 0), solComp.getReElement(0, 0));
		}
		
		//set loading bar to 100% as all calculations are completed
		QViewModel.updateLoadingBar(100);
	}
}