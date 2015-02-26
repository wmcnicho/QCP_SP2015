package simulator;


public abstract class GateFactory {
	public enum GateType {X,Y,Z,H,NOT};
	public enum GateRep {MATRIX, SPARSE_MATRIX, FUNCREP};
	
	public static QGate create(GateType type, GateRep rep){
		switch (type){
		case X:
			break;
		case Y:
			break;
		case Z:
			break;
		case H:
			break;
		case NOT:
			break;
		default:
			
		}
	}
}
