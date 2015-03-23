package unitTests;



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
	public void testFunctional() {
		Runtime.getRuntime().gc();
		System.out.println("Functional");
		//for(int j=0; j<3;j++){
			for(int i=2; i<=18; i++){
				int numQubits =i;
				int searchValue = 1 ;
				int numOfStates = (int) Math.pow(2,numQubits);
				long t1 = System.nanoTime();
				Runtime.getRuntime().gc();
				MRegister reg = new MRegister(numQubits, "complex");//change to refresh register		
				QCircuit q = new GroverGateByGate("functional", new int[]{1}, numQubits, false);
				//q.applyCircuit(reg);
				Runtime.getRuntime().gc();
				//System.out.println(j + "," + i+"th trial: "+(System.nanoTime()-t1));
			}
		//}
			Runtime.getRuntime().gc();
	}
	@Test
	public void testSparse() {
		Runtime.getRuntime().gc();
		System.out.println("Sparse");
		//for(int j=0; j<3;j++){
			for(int i=2; i<=18; i++){
				int numQubits =i;
				int searchValue = 1 ;
				int numOfStates = (int) Math.pow(2,numQubits);
				long t1 = System.nanoTime();
				Runtime.getRuntime().gc();
				MRegister reg = new MRegister(numQubits, "complex");//change to refresh register		
				QCircuit q = new GroverGateByGate("gate", new int[]{1}, numQubits, false);
				//q.applyCircuit(reg);
				Runtime.getRuntime().gc();
				//System.out.println(j + "," + i+"th trial: "+(System.nanoTime()-t1));
			}
		//}
			Runtime.getRuntime().gc();
	}
	@Test
	public void testDense() {
		Runtime.getRuntime().gc();
		System.out.println("Dense");
		//for(int j=0; j<3;j++){
			for(int i=2; i<=11; i++){
				int numQubits =i;
				int searchValue = 1 ;
				int numOfStates = (int) Math.pow(2,numQubits);
				long t1 = System.nanoTime();
				Runtime.getRuntime().gc();
				MRegister reg = new MRegister(numQubits, "complex");//change to refresh register		
				QCircuit q = new GroverGateByGate("complex", new int[]{1}, numQubits, false);
				//q.applyCircuit(reg);
				Runtime.getRuntime().gc();
				//System.out.println("" + i+"th trial: "+(System.nanoTime()-t1));
			}
		//}
			Runtime.getRuntime().gc();
	}

}
