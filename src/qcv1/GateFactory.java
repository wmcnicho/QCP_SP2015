package qcv1;

public abstract class GateFactory {
	public static QGate create(GateType type, String rep, int [] controls, int target, int numOfStates){
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
	public static QGate createHGate(String rep, int [] controls, int target, int numOfStates){
		switch (rep){
		case "sparse":
			return new MHGate(rep, controls, target, numOfStates);
		case "functional":
			return new FHGate(rep, controls, target);
		case "gate":
			return new MHGate(rep, controls, target, numOfStates);
		default:
			return new MHGate("complex", controls, target, numOfStates);
		}
	}
	
	//NOT gate factory
	public static QGate createNOTGate(String rep, int [] controls, int target, int numOfStates){
		switch (rep){
		case "sparse":
			return new MNOTGate(rep, controls, target, numOfStates);
		case "functional":
			return new FHGate(rep, controls, target);
		case "gate":
			return new MNOTGate(rep, controls, target, numOfStates);
		default:
			return new MNOTGate("complex", controls, target, numOfStates);
		}
	}
	
	//Pauli-X gate factory
	public static QGate createXGate(String rep, int [] controls, int target, int numOfStates){
		switch (rep){
		case "sparse":
			return new MXGate(rep, controls, target, numOfStates);
		case "functional":
			return new FXGate(rep, controls, target);
		case "gate":
			return new MXGate(rep, controls, target, numOfStates);
		default:
			return new MXGate("complex", controls, target, numOfStates);
		}
	}
	
	//Pauli-Y gate factory
	public static QGate createYGate(String rep, int [] controls, int target, int numOfStates){
		switch (rep){
		case "sparse":
			return new MYGate(rep, controls, target, numOfStates);
		case "functional":
			return new FYGate(rep, controls, target);
		case "gate":
			return new MYGate(rep, controls, target, numOfStates);
		default:
			return new MYGate("complex", controls, target, numOfStates);
		}
	}
	
	//Pauli-Z gate factory
	public static QGate createZGate(String rep, int [] controls, int target, int numOfStates){
		switch (rep){
		case "sparse":
			return new MZGate(rep, controls, target, numOfStates);
		case "functional":
			return new FZGate(rep, controls, target);
		case "gate":
			return new MZGate(rep, controls, target, numOfStates);		
		default:
			return new MZGate("complex", controls, target, numOfStates);
		}
	}
	
	//Phase gate factory
	public static QGate createSGate(String rep, int [] controls, int target, int numOfStates){
		switch (rep){
		case "sparse":
			return new MSGate(rep, controls, target, numOfStates);
		case "functional":
			return new FSGate(rep, controls, target);
		case "gate":
			return new MSGate(rep, controls, target, numOfStates);
		default:
			return new MSGate("complex", controls, target, numOfStates);
		}
	}
	
	//pi/8 gate factory
	public static QGate createTGate(String rep, int [] controls, int target, int numOfStates){
		switch (rep){
		case "sparse":
			return new MTGate(rep, controls, target, numOfStates);
		case "functional":
			return new FTGate(rep, controls, target);
		case "gate":
			return new MTGate(rep, controls, target, numOfStates);
		default:
			return new MTGate("complex", controls, target, numOfStates);
		}
	}
	
	//arbitrary phase gate
	public static QGate createPhaseGate(String rep, int [] controls, int target, 
			int numOfStates, double phase){
		switch (rep){
		case "sparse":
			return new MPhaseGate(rep, controls, target, numOfStates, phase);
		case "functional":
			return new FPhaseGate(rep, controls, target, phase);
		case "gate":
			return new MPhaseGate(rep, controls, target, numOfStates, phase);
		default:
			return new MPhaseGate("complex", controls, target, numOfStates, phase);
		}
	}
}
