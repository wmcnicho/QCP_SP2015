package qcv1;

import Matrix.*;
import java.util.Random;

/**
 * The quantum register with the amplitudes of its basis states (i.e. state vector)
 * stored in a matrix representation.
 * @author Michael Chiang
 *
 */
public class MRegister implements QRegister{
	private Matrix amplitudes = null;
	private int numOfQubits = 0;
	private int numOfStates = 0;
	private String matrixType = "";

	/**
	 * Create a register with a specified number of qubits
	 * @param num Number of qubits
	 * @param type Type of matrix (e.g. complex (dense), sparse, sparse gate)
	 */
	public MRegister(int num, String type){
		matrixType = type;
		if (num < 1) num = 1;
		numOfQubits = num;
		numOfStates = (int) Math.pow(2, numOfQubits);
		amplitudes = MatrixFactory.create(numOfStates, 1, matrixType);
	
	}
	
	/**
	 * Create a register with a specified number of qubits. The amplitude of the
	 * nth state is set to one and others are set to zero.
	 * @param num
	 * @param n 
	 * @param type
	 */
	public MRegister(int num, int n, String type){
		this(num, type);
		//check if n is a valid basis state
		if (n < 0 || n >= numOfStates) n = 0;
		amplitudes.setElement(n, 0, 1.0, 0.0);
	}
		
	/**
	 * Set all states of the register to equal (real) amplitude
	 */
	public void setEqualAmplitude(){
		double amp = Math.sqrt(1.0 / numOfStates);
		for (int i = 0; i < numOfStates; i++){
			amplitudes.setElement(i, 0, amp, 0.0);
		}
	}
	
	/**
	 * Set the amplitudes of all basis states
	 * @param amps
	 */
	public void setAmplitude(Matrix amps){
		amplitudes = amps;
	}
	
	/**
	 * Set the amplitude of a basis state
	 * @param state Basis state
	 * @param amps Complex amplitude for that basis state (i.e. [real, imag])
	 */
	public void setAmplitude(int state, double [] amps){
		amplitudes.setElement(0, state, amps);
	}
	
	/**
	 * Set the amplitude of a basis state
	 * @param state Basis state
	 * @param real Real part of the amplitude for that basis state
	 * @param imag Imaginary part of the amplitude for that basis state
	 */
	public void setAmplitude(int state, double real, double imag){
		amplitudes.setElement(0, state, real, imag);
	}

	/**
	 * Get the number of qubits in the register
	 */
	public int numOfQubit(){return numOfQubits;}
	
	/**
	 * Get the number of basis states in the register
	 */
	public int numOfStates(){return numOfStates;}
	
	/**
	 * Get the amplitudes of all basis states
	 */
	public Matrix getAmplitude(){
		return amplitudes;
	}
	
	/**
	 * Get the amplitude of a basis state
	 * @param state Basis state
	 */
	public double [] getAmplitude(int state){
		return amplitudes.getElement(state, 0);
	}

	/**
	 * Print the amplitudes of all basis states to console
	 */
	public void printAmplitude(){
		for (int i = 0; i < numOfStates; i++){
			System.out.println(amplitudes.getElement(i,0)[0] + " " + amplitudes.getElement(i,0)[1]);
		}
	}
	
	/**
	 * Get the probabilities of observing each of the basis states
	 */
	public double [] getProbabilities(){
		double [] probs = new double [numOfStates];
		for(int i=0; i<numOfStates; i++){
			probs[i] = Complex.magSquare(amplitudes.getElement(i, 0));
		}
		return probs;
	}
	
	/**
	 * Print the probabilities of observing each of the basis states
	 */
	public void printProbabilities(){
		double [] probs = new double [numOfStates];
		double sum = 0;
		for(int i=0; i<numOfStates; i++){
			probs[i] = Complex.magSquare(amplitudes.getElement(i, 0));
			sum += probs[i];
			System.out.println(probs[i]);
		}
		System.out.println(sum);
	}
	
	/**
	 * Perform a measurement on the register
	 */
	public int measure(){
		Random rand = new Random();
		double key = rand.nextDouble();
		double sum = 0.0;
		for (int i = 0; i < numOfStates; i++){
			sum += Complex.magSquare(amplitudes.getElement(i, 0));
			if (sum > key){
				//collapse the wave function to the observed state
				for (int j = 0; j < numOfStates; j++){
					amplitudes.setElement(j, 0, 0.0, 0.0);
				}
				amplitudes.setElement(i, 0, 1.0, 0.0);
				return i;
			}
		}
		return 0;
	}

}
