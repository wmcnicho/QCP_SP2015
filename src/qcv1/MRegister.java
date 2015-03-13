package qcv1;

import Matrix.*;
import java.util.Random;

public class MRegister implements QRegister{
	private Matrix amplitudes = null;
	private int numOfQubits = 0;
	private int numOfStates = 0;
	private String matrixType = "";

	//constructors
	public MRegister(int num, String type){
		matrixType = type;
		if (num < 1) num = 1;
		numOfQubits = num;
		numOfStates = (int) Math.pow(2, numOfQubits);
		amplitudes = MatrixFactory.create(numOfStates, 1, matrixType);
	
	}
	
	public MRegister(int num, int n, String type){
		this(num, type);
		
		if (n < 0 || n > numOfStates) n = 0;
		amplitudes.setElement(n, 0, 1.0, 0.0);
	}
		
	public void setEqualAmplitude(){
		double amp = Math.sqrt(1.0 / numOfStates);
		for (int i = 0; i < numOfStates; i++){
			amplitudes.setElement(i, 0, amp, 0.0);
		}
	}
	
	public void setAmplitude(Matrix amps){
		amplitudes = amps;
	}
	public void setAmplitude(int qubitPos, double [] amps){
		amplitudes.setElement(0, qubitPos, amps);
	}
	public void setAmplitude(int qubitPos, double real, double imag){
		amplitudes.setElement(0, qubitPos, real, imag);
	}

	public int numOfQubit(){return numOfQubits;}
	public int numOfStates(){return numOfStates;}
	
	public Matrix getAmplitude(){
		return amplitudes;
	}
	
	public double [] getAmplitude(int i){
		return amplitudes.getElement(i, 0);
	}


	public void printAmplitude(){
		for (int i = 0; i < numOfStates; i++){
			System.out.println(amplitudes.getElement(i,0)[0] + " " + amplitudes.getElement(i,0)[1]);
		}
	}
	
	public double [] getProbabilities(){
		double [] probs = new double [numOfStates];
		for(int i=0; i<numOfStates; i++){
			probs[i] = Complex.magSquare(amplitudes.getElement(i, 0));
		}
		return probs;
	}
	
	public void measure(){
		Random rand = new Random();
		double key = rand.nextDouble();
		double sum = 0.0;
		for (int i = 0; i < numOfStates; i++){
			sum += Complex.magSquare(amplitudes.getElement(i, 0));
			if (sum > key){
				//set all the states to zero 
				for (int j = 0; j < numOfStates; j++){
					amplitudes.setElement(j, 0, 0.0, 0.0);
				}
				amplitudes.setElement(i, 0, 1.0, 0.0);
				break;
			}
		}
	}

}
