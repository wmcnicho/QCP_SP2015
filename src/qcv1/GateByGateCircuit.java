package qcv1;

import java.util.ArrayList;
import Gui.QViewModel;

/**
 * A gate by gate quantum circuit that is independent of the gate representation.
 * @author Michael Chiang
 *
 */

public class GateByGateCircuit implements QCircuit {
	//store a list of gates in the order how they would be applied to the register
	private ArrayList<QGate> gates = null;
	
	/*
	 * an option for whether telling the GUI the progress of how much a 
	 */
	private boolean updateGui = false;
	
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
	
	public void updateGui(boolean val){
		updateGui = val;
	}
	
	/**
	 * Apply the circuit to a quantum register
	 * @param reg Quantum register to be applied to the circuit
	 */
	public void applyCircuit(QRegister reg){
		if (updateGui){
			//update gui 
			int iterations = gates.size();
			final int updateFreq = iterations / 10;
			for (int i = 0; i < gates.size(); i++){
				gates.get(i).applyGate(reg);
				//compute the percentage of the calculations done
				if (i % updateFreq == 0){
					int percent = (int) ((double) i / iterations * 100);
					QViewModel.updateLoadingBar(percent);
					QViewModel.updateHistogramValues(reg.getProbabilities());
				}
			}
			QViewModel.updateLoadingBar(100);
			QViewModel.updateHistogramValues(reg.getProbabilities());
		} else {
			for (int i = 0; i < gates.size(); i++){
				gates.get(i).applyGate(reg);
			}
		}
		
	}
}