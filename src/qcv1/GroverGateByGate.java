package qcv1;

import Matrix.*;
import Gui.QViewModel;

/**
 * An implementation of quantum search algorithm (Grover's algorithm). This is 
 * a gate-by-gate circuit of the Grover's diffusion operation
 * @author Michael Chiang
 *
 */
public class GroverGateByGate extends GateByGateCircuit {
	private int [] targets; //store the target indices for the oracle
	private int numOfStates;
	private int numOfQubits;
	private String gateRep;
	private Matrix solutionVector;
	private Matrix nonSolutionVector;
	private boolean updateGui;
	
	/**
	 * Create the Grover circuit
	 * @param rep Representation of the gate (complex matrix, sparse matrix, functional)
	 * @param targetIndices The target/solution states of the search problem
	 * @param numOfQbits Number of qubits required
	 * @param guiUpdate Whether or not to update the gui
	 */
	public GroverGateByGate(String rep, int [] targetIndices, int numOfQbits, boolean guiUpdate){
		
		gateRep = rep;
		targets = targetIndices;
		updateGui = guiUpdate;
		
		//check if the number of qubits is valid
		if (numOfQbits <= 0) numOfQbits = 1; 
		numOfQubits = numOfQbits;
		numOfStates = (int) Math.pow(2, numOfQubits);
		
		/*
		 * create the solution and non-solution vectors that span the plane
		 * where the state vector is rotating on
		 */
		solutionVector = MatrixFactory.create(1, numOfStates, "complex");
		nonSolutionVector = MatrixFactory.create(1, numOfStates, "complex");
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
		
		//build the circuit for one iteration of the Grover diffusion operation
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
		
		//perform Hadamard transform on all qubits
		for (int i = 0; i < numOfQubits; i++){
			addGate(GateFactory.createHGate(gateRep, null, i, numOfStates));
		}
		
		//create phase shift gate - perform the 2|0><0| - I operation (inversion by mean)
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
		
		/*
		 * perform Hadamard transform on all qubits again (reuse the gates created
		 * previously since they are the same)
		 */
		for (int i = 0; i < numOfQubits; i++){
			addGate(getGate(1+i));
		}
	}
	
	/**
	 * Perform Grover's algorithm on a register.
	 * 
	 * @param reg Quantum register
	 */
	public void applyCircuit(QRegister reg){
		//set all states of the register to equal amplitude
		reg.setEqualAmplitude();
		
		/*
		 * compute the number of Grover's iterations required to maximize
		 * the probability of measuring the target/solution states. This
		 * is approximately r = pi/4 sqrt(N/M) times, where N = number of entries
		 * in the list and M = number of solutions.
		 */
		final int iterations = (int) (Math.PI / 4.0 * 
				Math.sqrt((double) numOfStates/ targets.length));
		
		
		for (int i = 0; i < iterations; i++){
			//apply the gate circuit for each Grover iteration
			super.applyCircuit(reg);
			
			/*
			 * compute the projection of the state vector on the solutions 
			 * and non-solutions vectors:
			 */
			Matrix solComp = Matrix.Multiply(solutionVector, reg.getAmplitude());
			Matrix nonSolComp = Matrix.Multiply(nonSolutionVector, reg.getAmplitude());
			
			//update gui 
			if (updateGui){				
				//compute the percentage of the calculations done
				int percent = (int) ((double) i / iterations * 100);
				QViewModel.updateLoadingBar(percent);
				QViewModel.updateHistogramValues(reg.getProbabilities());
				QViewModel.setVector(nonSolComp.getReElement(0, 0), solComp.getReElement(0, 0));
			}
		}
		
		//set loading bar to 100% as all calculations are completed
		if (updateGui){
			QViewModel.updateLoadingBar(100);
		}
		
		//measure the register
		reg.measure();
	}
}