package simulator.frep;

import simulator.*;

public class CRegister implements QRegister{
	private Complex [] amplitudes = null;
	private static CRegister instance = null;
	private int numOfQubits = 0;
	private int numOfStates = 0;

	/*
	 * private constructor - exists to avoid automatic instantiation
	 */
	private CRegister(){}


	//set the nth (count from 0) state with amplitude 1 but others to 0
	public void setRegister(int num, int n){
		numOfQubits = num;
		numOfStates = (int) Math.pow(2, numOfQubits);
		amplitudes = new Complex [numOfStates];
		for (int i = 0; i < numOfStates; i++){
			amplitudes[i] = new Complex();
		}
		amplitudes[n].setReal(1.0);
	}
	
	//set all the states with equal probs
	public void setRegister(int num){
		numOfQubits = num;
		numOfStates = (int) Math.pow(2, numOfQubits);
		amplitudes = new Complex [numOfStates];
		double amp = Math.sqrt(1.0 / numOfStates);
		for (int i = 0; i < numOfStates; i++){
			amplitudes[i] = new Complex(amp,0);
		}
	}

	public void setAmplitude(Complex [] amps){
		amplitudes = amps;
	}
	public void setAmplitude(int qubitPos, Complex amp){
		amplitudes[qubitPos].set(amp);
	}
	public void setAmplitude(int qubitPos, double real, double imag){
		amplitudes[qubitPos].setReal(real);
		amplitudes[qubitPos].setImag(imag);
	}

	public int numOfQubit(){return numOfQubits;}
	public int numOfStates(){return numOfStates;}

	public Complex getAmplitude(int i){
		return amplitudes[i];
	}
	public Complex [] getAmplitude(){
		return amplitudes;
	}

	public void printAmplitude(){
		for (int i = 0; i < numOfStates; i++){
			amplitudes[i].printComplex();
		}
		System.out.println();
	}
	
	public void measure(){
	double sum = 0;
		for(int i = 0; i < numOfStates; i++){
			double probability = amplitudes[i].ampSquare();
			sum += probability;
			System.out.println("the probability of state "+(i)+" is "+probability);
		}
		System.out.println(sum);
	}
	
	//get an instance of the register
	public static CRegister getInstance(){
		if (instance == null){
			instance = new CRegister();
		}
		return instance;
	}
}
