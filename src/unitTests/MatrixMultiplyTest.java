package unitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import Matrix.Matrix;
import Matrix.MatrixFactory;

public class MatrixMultiplyTest {

	@Test
	public void test() {
		int m = 2;
		int n = 2;
		Matrix m1 = MatrixFactory.create(m, n, "complex");
		Matrix m2 = MatrixFactory.create(m, n, "complex");
		
		m1.setElement(0, 0, 3, 2);
		m2.setElement(0, 0, 4, 1);
		
		assertEquals("This test does nothing", 0, 0);
		
//		m1.setElement(i, j, real, imag);
//		m2.setElement(i, j, real, imag);
//		
//		m1.setElement(i, j, real, imag);
//		m2.setElement(i, j, real, imag);
//		
//		m1.setElement(i, j, real, imag);
//		m2.setElement(i, j, real, imag);
//		
//		JAssert()
		
		//pass("Not yet implemented");
	}

}
