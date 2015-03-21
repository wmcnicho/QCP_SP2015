package qcv1;
import Matrix.Matrix;

/**
 * Interface of a general quantum register
 * @author Michael Chiang
 *
 */
public interface QRegister {
	/**
	 * Get the number of qubits in the register
	 */
	public int numOfQubit();
	
	/**
	 * Get the number of basis states in the register
	 */
	public int numOfStates();
	
	/**
	 * Set all basis states to equal amplitude
	 */
	public void setEqualAmplitude();
	
	/**
	 * Set the amplitude of a basis state
	 * @param state Basis state
	 * @param amps Complex amplitude of the basis state [real, imag]
	 */
	public void setAmplitude(int state, double [] amps);
	
	/**
	 * Set the amplitude of a basis state
	 * @param state Basis state
	 * @param real Real part of the amplitude
	 * @param imag Imaginary part of the amplitude
	 */
	public void setAmplitude(int state, double real, double imag);
	
	/**
	 * Get the amplitude of a basis state
	 * @param state Basis state
	 * @return Complex amplitude of this basis state
	 */
	public double [] getAmplitude(int state);
	
	/**
	 * Get the amplitudes of all basis states
	 */
	public Matrix getAmplitude();
	
	/**
	 * Get the probabilities of measuring each of the basis states
	 */
	public double [] getProbabilities();
	
	/**
	 * Perform a measurement on the register (i.e. collapses the wave function
	 * to a particular basis state according to the states' probabilities)
	 */
	public int measure();
}
	
