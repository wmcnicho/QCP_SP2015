package qcv1;
import java.util.ArrayList;

public class GateByGateCircuit implements QCircuit {
	private ArrayList<QGate> gates = null;
	
	//constructor
	public GateByGateCircuit(){
		gates = new ArrayList<QGate>();
	}
	
	public void addGate(QGate g){
		gates.add(g); //add gate to the last position
	}
	public void addGate(int pos, QGate g){
		gates.add(pos,g);
	}
	public QGate getGate(int pos){
		return gates.get(pos);
	}
	public void removeGate(int pos){
		gates.remove(pos);
	}
	
	public void applyCircuit(QRegister reg){
		for (QGate g : gates){
			g.applyGate(reg);
		}
	}
}