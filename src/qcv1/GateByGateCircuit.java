package qcv1;
import java.util.ArrayList;

import Matrix.ComplexMatrix;

public class GateByGateCircuit implements QCircuit {
	private ArrayList<QGate> gates = null;
	
	//constructor
	public GateByGateCircuit(){
		gates = new ArrayList<QGate>();
	}
	
	/**
	 * Add a quantum gate to the circuit.
	 * 
	 * @param g	The quantum gate to be added (QGate).
	 */
	public void addGate(QGate g){
		gates.add(g); //add gate to the last position
	}
	
	/**
	 * Add a quantum gate to the circuit.
	 * 
	 * @param pos Position index where the quantum gate should be added.
	 * @param g The quantum gate to be added (QGate).
	 */
	public void addGate(int pos, QGate g){
		gates.add(pos,g);
	}
	
	/**
	 * 
	 * @param pos
	 * @return QGate
	 */
	public QGate getGate(int pos){
		return gates.get(pos);
	}
	
	/**
	 * Remove a quantum gate from the circuit.
	 * 
	 * @param pos The position index of the gate to be removed.
	 */
	public void removeGate(int pos){
		gates.remove(pos);
	}
	
	public int getCircuitSize(){return gates.size();}
	
	/**
	 * Apply the circuit to a quantum register.
	 * 
	 * 
	 * @param reg Quantum register to be applied to the circuit
	 */
	public void applyCircuit(QRegister reg){
		for (QGate g : gates){
			//((ComplexMatrix) ((MGate) g).gate).printMatrix();
			//System.out.println();
			g.applyGate(reg);
			//System.out.println(g.gateType());
			//((MRegister) reg).printAmplitude();
			//System.out.println();
		}
	}
}