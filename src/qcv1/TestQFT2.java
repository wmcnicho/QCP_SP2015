package qcv1;

import Matrix.ComplexMatrix;

public class TestQFT2 {
	public static void main (String [] args){
		ComplexMatrix m = new ComplexMatrix(8,8);
		for (int i = 0; i < 8; i++){
			for (int j = 0; j < 8; j++){
				m.setElement(i, j, w(i*j));
			}
		}
		m.printMatrix();
		ComplexMatrix v = new ComplexMatrix(8,1);
		for (int i = 0; i < 8; i++){
			v.setElement(i, 0, 1.0/Math.sqrt(8),0);
		}
		v.setElement(0,0,0,0);
		v.setElement(1,0,0,0);
		v.setElement(2,0,0,0);
		v.setElement(3,0,1/Math.sqrt(2),0);
		v.setElement(4,0,1/Math.sqrt(2),0);
		v.setElement(5,0,0,0);
		v.setElement(6,0,0,0);
		v.setElement(7,0,0,0);
		v.printMatrix();
		System.out.println();
		v.multiplyBy(m);
		System.out.println();
		v.printMatrix();
	}
	
	public static double [] w (int n){
		double re = Math.cos(Math.PI * 2 / 8.0 * n) / Math.sqrt(8);
		double im = Math.sin(Math.PI * 2 / 8.0 * n) / Math.sqrt(8);
		return new double [] {re,im};
	}
}
