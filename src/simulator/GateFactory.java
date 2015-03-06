package simulator;

import simulator.mrep.*;
import simulator.frep.*;


public abstract class GateFactory {
	public static QGate create(GateType type, GateRep rep, int [] controls, int target, int numOfStates){
		switch (type){
		case X:
			return createXGate(rep,controls,target,numOfStates);
		case Y:
			return createYGate(rep,controls,target,numOfStates);
		case Z:
			return createZGate(rep,controls,target,numOfStates);
		case H:
			return createHGate(rep,controls,target,numOfStates);
		case S:
			return createSGate(rep,controls,target,numOfStates);
		case T:
			return createTGate(rep,controls,target,numOfStates);
		case NOT:
			return createNOTGate(rep,controls,target,numOfStates);
		default:
			return null;
		}
	}
	
	//Hadamard gate factory
	public static QGate createHGate(GateRep rep, int [] controls, int target, int numOfStates){
		switch (rep){
		case SPARSE_MATRIX:
			return new MHGate(MatrixType.SPARSE, controls, target, numOfStates);
		case FUNC_REP:
			return new FHGate(MatrixType.SPARSE, controls, target);
		default:
			return new MHGate(MatrixType.DENSE, controls, target, numOfStates);
		}
	}
	
	//NOT gate factory
	public static QGate createNOTGate(GateRep rep, int [] controls, int target, int numOfStates){
		switch (rep){
		case SPARSE_MATRIX:
			return new MNOTGate(MatrixType.SPARSE, controls, target, numOfStates);
		//case FUNC_REP:
		
		default:
			return new MNOTGate(MatrixType.DENSE, controls, target, numOfStates);
		}
	}
	
	//Pauli-X gate factory
	public static QGate createXGate(GateRep rep, int [] controls, int target, int numOfStates){
		switch (rep){
		case SPARSE_MATRIX:
			return new MXGate(MatrixType.SPARSE, controls, target, numOfStates);
		//case FUNC_REP:
		
		default:
			return new MXGate(MatrixType.DENSE, controls, target, numOfStates);
		}
	}
	
	//Pauli-Y gate factory
	public static QGate createYGate(GateRep rep, int [] controls, int target, int numOfStates){
		switch (rep){
		case SPARSE_MATRIX:
			return new MYGate(MatrixType.SPARSE, controls, target, numOfStates);
		//case FUNC_REP:
		
		default:
			return new MYGate(MatrixType.DENSE, controls, target, numOfStates);
		}
	}
	
	//Pauli-Z gate factory
	public static QGate createZGate(GateRep rep, int [] controls, int target, int numOfStates){
		switch (rep){
		case SPARSE_MATRIX:
			return new MZGate(MatrixType.SPARSE, controls, target, numOfStates);
		//case FUNC_REP:
		
		default:
			return new MZGate(MatrixType.DENSE, controls, target, numOfStates);
		}
	}
	
	//Phase gate factory
	public static QGate createSGate(GateRep rep, int [] controls, int target, int numOfStates){
		switch (rep){
		case SPARSE_MATRIX:
			return new MSGate(MatrixType.SPARSE, controls, target, numOfStates);
		//case FUNC_REP:
		
		default:
			return new MSGate(MatrixType.DENSE, controls, target, numOfStates);
		}
	}
	
	//pi/8 gate factory
	public static QGate createTGate(GateRep rep, int [] controls, int target, int numOfStates){
		switch (rep){
		case SPARSE_MATRIX:
			return new MTGate(MatrixType.SPARSE, controls, target, numOfStates);
		//case FUNC_REP:
		
		default:
			return new MTGate(MatrixType.DENSE, controls, target, numOfStates);
		}
	}
	
	public static QGate createPhaseGate(GateRep rep, int [] controls, int target, int numOfStates, double phaseFrac){
		switch (rep){
		case SPARSE_MATRIX:
			return new MPhaseGate(MatrixType.SPARSE, controls, target, numOfStates, phaseFrac);
		//case FUNC_REP:
		
		default:
			return new MPhaseGate(MatrixType.DENSE, controls, target, numOfStates, phaseFrac);
		}
	}
}
