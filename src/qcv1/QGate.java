package qcv1;

/**
 * Interface of a general quantum gate
 * @author Michael Chiang
 *
 */
public interface QGate {
	/**
	 * Apply the gate to the quantum register
	 * @param reg Register
	 */
	public void applyGate(QRegister reg);
	
	/**
	 * Get the gate type
	 */
	public String gateType();
}
