package qcv1;

import Matrix.ComplexMatrix;

public class TestBitOperation {
	public static void main (String [] args){
		String rep = "complex";
		int numOfStates = 8;
		MRegister reg = new MRegister(3, "complex");
		reg.setAmplitude(0,0,0);
		reg.setAmplitude(1,0,0);
		reg.setAmplitude(2,0,0);
		reg.setAmplitude(3,1/Math.sqrt(2),0);
		reg.setAmplitude(4,1/Math.sqrt(2),0);
		reg.setAmplitude(5,0,0);
		reg.setAmplitude(6,0,0);
		reg.setAmplitude(7,0,0);
		MGate [] g = new MGate[7];
		g[0] = new MHGate(rep, null, 2, 8);
		g[1] = new MPhaseGate(rep, new int [] {1}, 2, 8, 0.5 * Math.PI);
		g[2] = new MPhaseGate(rep, new int [] {0}, 2, 8, 0.25 * Math.PI);
		g[3] = new MHGate(rep, null, 1, 8);
		g[4] = new MPhaseGate(rep, new int [] {0}, 1, 8, 0.5 * Math.PI);
		g[5] = new MHGate(rep, null, 0, 8);
		g[6] = new MSwapGate(rep, 0, 2, 8);
		MGate combine = MGate.combineGate(g);
		ComplexMatrix m = (ComplexMatrix) combine.gate;
		m.scaleBy(Math.sqrt(8));
		m.printMatrix();
		reg.printAmplitude();
		for (int i = 0; i < g.length; i++){
			ComplexMatrix matrix = (ComplexMatrix) g[i].gate;
			matrix.printMatrix();
			System.out.println();
			g[i].applyGate(reg);
		}
		reg.printAmplitude();
	}
}
