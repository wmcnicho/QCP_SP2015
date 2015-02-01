package qcv1;
import java.util.ArrayList;

public class GateByGateCircuit implements QCircuit {
	private ArrayList<QGate> gates = null;
	
	//constructor
	public GateByGateCircuit(){
		gates = new ArrayList<QGate>();
	}
	
	public void addGate(int pos, QGate g){
		gates.add(pos,g);
	}
	public void removeGate(int pos){
		gates.remove(pos);
	}
	
	public void updateRegister(QRegister reg){
		for (QGate g : gates){
			g.applyTo(reg);
		}
	}
}
