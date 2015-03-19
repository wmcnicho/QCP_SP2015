package qcv1;
import java.util.ArrayList;

/**
 * A gate by gate quantum circuit that is independent of the gate representation.
 * @author Michael
 *
 */
public class GateByGateCircuit implements QCircuit {
	private ArrayList<QGate> gates = null;
	
	/**
	 * Construct an empty gate by gate circuit
	 */
	public GateByGateCircuit(){
		gates = new ArrayList<QGate>();
	}
	
	/**
	 * Add a quantum gate to the circuit
	 * @param g	The quantum gate to be added (QGate)
	 */
	public void addGate(QGate g){
		gates.add(g); //add gate to the last position
	}
	
	/**
	 * Add a quantum gate to the circuit
	 * @param pos Position index where the quantum gate should be added
	 * @param g The quantum gate to be added (QGate)
	 */
	public void addGate(int pos, QGate g){
		gates.add(pos,g);
	}
	
	/**
	 * Get a gate from the circuit
	 * @param pos Position index of the gate
	 * @return QGate The quantum gate at position pos of the circuit 
	 */
	public QGate getGate(int pos){
		return gates.get(pos);
	}
	
	/**
	 * Remove a quantum gate from the circuit.
	 * @param pos Position index of the gate to be removed.
	 */
	public void removeGate(int pos){
		gates.remove(pos);
	}
	
	/**
	 * Get the number of gates in the circuit
	 * @return
	 */
	public int getCircuitSize(){return gates.size();}
	
	/**
	 * Apply the circuit to a quantum register
	 * @param reg Quantum register to be applied to the circuit
	 */
	public void applyCircuit(QRegister reg){
		for (QGate g : gates){
			g.applyGate(reg);
		}
	}
}