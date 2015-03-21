package qcv1;

/**
 * A factory for generating single qubit gates with the implemented representations
 * @author Michael Chiang
 *
 */
public abstract class GateFactory {
	/**
	 * Create a single qubit gate
	 * @param type Type of gate (X, Y, Z, H, S, T)
	 * @param rep Gate representation (normal, gate, functional)
	 * @param controls Array of the positions of the control qubits
	 * @param target The position of the qubit that the gate is applied on
	 * @param numOfStates Number of basis states in the register
	 * @return A single qubit gate
	 */
	public static QGate create(String type, String rep, int [] controls, int target, int numOfStates){
		switch (type){
		case "X":
			return createXGate(rep,controls,target,numOfStates);
		case "Y":
			return createYGate(rep,controls,target,numOfStates);
		case "Z":
			return createZGate(rep,controls,target,numOfStates);
		case "H":
			return createHGate(rep,controls,target,numOfStates);
		case "S":
			return createSGate(rep,controls,target,numOfStates);
		case "T":
			return createTGate(rep,controls,target,numOfStates);
		default:
			return null;
		}
	}
	
	/**
	 * Create the Hadamard gate
	 * @param rep Gate representation (normal, gate, functional)
	 * @param controls Array of the positions of the control qubits
	 * @param target The position of the qubit that the gate is applied on
	 * @param numOfStates Number of basis states in the register
	 * @return A Hadamard gate
	 */
	public static QGate createHGate(String rep, int [] controls, int target, int numOfStates){
		switch (rep){
		case "sparse":
			return new MHGate(rep, controls, target, numOfStates);
		case "functional":
			return new FHGate(controls, target);
		case "gate":
			return new MHGate(rep, controls, target, numOfStates);
		default:
			return new MHGate("complex", controls, target, numOfStates);
		}
	}

	/**
	 * Create the Pauli-X gate (NOT gate)
	 * @param rep Gate representation (normal, gate, functional)
	 * @param controls Array of the positions of the control qubits
	 * @param target The position of the qubit that the gate is applied on
	 * @param numOfStates Number of basis states in the register
	 * @return A Pauli-X gate (NOT gate)
	 */
	public static QGate createXGate(String rep, int [] controls, int target, int numOfStates){
		switch (rep){
		case "sparse":
			return new MXGate(rep, controls, target, numOfStates);
		case "functional":
			return new FXGate(controls, target);
		case "gate":
			return new MXGate(rep, controls, target, numOfStates);
		default:
			return new MXGate("complex", controls, target, numOfStates);
		}
	}
	
	/**
	 * Create the Pauli-Y gate
	 * @param rep Gate representation (normal, gate, functional)
	 * @param controls Array of the positions of the control qubits
	 * @param target The position of the qubit that the gate is applied on
	 * @param numOfStates Number of basis states in the register
	 * @return A Pauli-Y gate
	 */
	public static QGate createYGate(String rep, int [] controls, int target, int numOfStates){
		switch (rep){
		case "sparse":
			return new MYGate(rep, controls, target, numOfStates);
		case "functional":
			return new FYGate(controls, target);
		case "gate":
			return new MYGate(rep, controls, target, numOfStates);
		default:
			return new MYGate("complex", controls, target, numOfStates);
		}
	}
	
	/**
	 * Create the Pauli-Z gate
	 * @param rep Gate representation (normal, gate, functional)
	 * @param controls Array of the positions of the control qubits
	 * @param target The position of the qubit that the gate is applied on
	 * @param numOfStates Number of basis states in the register
	 * @return A Pauli-Z gate
	 */
	public static QGate createZGate(String rep, int [] controls, int target, int numOfStates){
		switch (rep){
		case "sparse":
			return new MZGate(rep, controls, target, numOfStates);
		case "functional":
			return new FZGate(controls, target);
		case "gate":
			return new MZGate(rep, controls, target, numOfStates);		
		default:
			return new MZGate("complex", controls, target, numOfStates);
		}
	}
	
	/**
	 * Create the phase gate (S gate)
	 * @param rep Gate representation (normal, gate, functional)
	 * @param controls Array of the positions of the control qubits
	 * @param target The position of the qubit that the gate is applied on
	 * @param numOfStates Number of basis states in the register
	 * @return The phase gate (S gate)
	 */
	public static QGate createSGate(String rep, int [] controls, int target, int numOfStates){
		switch (rep){
		case "sparse":
			return new MSGate(rep, controls, target, numOfStates);
		case "functional":
			return new FSGate(controls, target);
		case "gate":
			return new MSGate(rep, controls, target, numOfStates);
		default:
			return new MSGate("complex", controls, target, numOfStates);
		}
	}
	
	/**
	 * Create the pi/8 gate (T gate)
	 * @param rep Gate representation (normal, gate, functional)
	 * @param controls Array of the positions of the control qubits
	 * @param target The position of the qubit that the gate is applied on
	 * @param numOfStates Number of basis states in the register
	 * @return The pi/8 gate (T gate)
	 */
	public static QGate createTGate(String rep, int [] controls, int target, int numOfStates){
		switch (rep){
		case "sparse":
			return new MTGate(rep, controls, target, numOfStates);
		case "functional":
			return new FTGate(controls, target);
		case "gate":
			return new MTGate(rep, controls, target, numOfStates);
		default:
			return new MTGate("complex", controls, target, numOfStates);
		}
	}
	
	/**
	 * Create an arbitrary phase shift gate
	 * @param rep Gate representation (normal, gate, functional)
	 * @param controls Array of the positions of the control qubits
	 * @param target The position of the qubit that the gate is applied on
	 * @param numOfStates Number of basis states in the register
	 * @param phase Phase shift of the gate
	 * @return A phase shift gate with the desired phase
	 */
	public static QGate createPhaseGate(String rep, int [] controls, int target, 
			int numOfStates, double phase){
		switch (rep){
		case "sparse":
			return new MPhaseGate(rep, controls, target, numOfStates, phase);
		case "functional":
			return new FPhaseGate(controls, target, phase);
		case "gate":
			return new MPhaseGate(rep, controls, target, numOfStates, phase);
		default:
			return new MPhaseGate("complex", controls, target, numOfStates, phase);
		}
	}
}
