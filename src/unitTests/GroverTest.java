package unitTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Map.Entry;

import org.junit.Test;

import Gui.QuantumGuiPanel;
import qcv1.GroverGateByGate;
import qcv1.MRegister;
import qcv1.QCircuit;
import qcv1.QProcess;

public class GroverTest {
	QuantumGuiPanel p = new QuantumGuiPanel();
	@Test
		public void testSparse() {
		for(int j=0; j<3;j++){
			for(int i=3; i<17; i++){
				int numQubits =i;
				int searchValue = 1 ;
				int numOfStates = (int) Math.pow(2,numQubits);
				long t1 = System.nanoTime();
				MRegister reg = new MRegister(numQubits, "gate");//change to refresh register		
				QCircuit q = new GroverGateByGate("gate", numOfStates, new int[]{1}, numQubits, numOfStates);
				q.applyCircuit(reg);
				System.out.println(j + "," + i+"th trial: "+(System.nanoTime()-t1));
			}
		}
	}
	public void testFunctional() {
		for(int j=0; j<3;j++){
			for(int i=2; i<20; i++){
				int numQubits =i;
				int searchValue = 1 ;
				int numOfStates = (int) Math.pow(2,numQubits);
				long t1 = System.nanoTime();
				MRegister reg = new MRegister(numQubits, "functional");//change to refresh register		
				QCircuit q = new GroverGateByGate("functional", numOfStates, new int[]{1}, numQubits, numOfStates);
				q.applyCircuit(reg);
				System.out.println(j + "," + i+"th trial: "+(System.nanoTime()-t1));
			}
		}
	}
	public void testDense() {
		for(int j=0; j<3;j++){
			for(int i=2; i<11; i++){
				int numQubits =i;
				int searchValue = 1 ;
				int numOfStates = (int) Math.pow(2,numQubits);
				long t1 = System.nanoTime();
				MRegister reg = new MRegister(numQubits, "complex");//change to refresh register		
				QCircuit q = new GroverGateByGate("complex", numOfStates, new int[]{1}, numQubits, numOfStates);
				q.applyCircuit(reg);
				System.out.println("" + i+"th trial: "+(System.nanoTime()-t1));
			}
		}
	}


}
