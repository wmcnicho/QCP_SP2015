package simulator.mrep;

import simulator.*;
import simulator.frep.*;

public class TestMatrix {
	public static void main (String [] args){
		
		QGate g = GateFactory.createPhaseGate(GateRep.DENSE_MATRIX, new int [] {2}, 0, 8, 16);
		
	}
}
