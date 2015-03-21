package qcv1;

/**
 * Interface of a general quantum circuit
 * @author Michael Chiang
 *
 */
public interface QCircuit {
	/**
	 * Apply the circuit to the quantum register
	 * @param reg Register
	 */
	public void applyCircuit(QRegister reg);
}
