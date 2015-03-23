package qcv1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * An implementation of Shor's algorithm.
 * The effects of the second register are not completed using a quantum register,
 * but it makes use of the Quantum Fourier Transform we have implemented.
 * @author Gennaro
 *
 */
public class ShorsAlgorithm {
	private int number;
	private Random rand = new Random();
	private String gateRep;
	private int numOfQubits;
	private int numOfStates;
	private int x;

	//constructor
	/**
	 * Construct the Shor's Algorithm object which requires a number to be passed
	 * as well as a string to specify what gate representation is used. 
	 * Checks are in place so to catch numbers without co-prime factors and 
	 * classical algorithms are used to get much better performance in these cases
	 * @param rep Gate representation
	 * @param num The number to be factorised
	 */
	public ShorsAlgorithm(String rep, int num){
		gateRep = rep;
		number = num;
		//if number has n bits, need 2n+1 qubits
		numOfQubits = (int) Math.ceil(Math.log(number) / Math.log(2))*2+1;
		numOfStates = (int) Math.pow(2, numOfQubits);

	}

	/**
	 * Run this method to return the factors of the number
	 * @return returns an array containing the factors, [factor 1, factor 2]
	 */
	public int [] run(){
		//choose random int x, 1 <= x <= N-1
		x = rand.nextInt(number-2) + 1;
		//find highest common factor
		int factor = gcd(x,number);
		if (factor != 1){
			return new int [] {factor, gcd(number/factor, number)};
		}

		//do order finding 
		int r = orderFinding();
		int y = this.modularPow(x, r/2, number);
		if (y==-1){
			System.err.println("something broke");
			return null;
		}
		int[] out = new int[2];
		out[0] = gcd(y+1, number);
		out[1] = gcd(y-1, number);
		return out;
	}

	protected static int gcd(int a, int b){
		if (b == 0){
			return a;
		}
		return gcd(b, a % b);
	}

	protected int orderFinding(){
		MRegister reg = new MRegister(numOfQubits, "complex");
		int [] indices = reg2(numOfStates, x, number);

		//set the corresponding indices in first register with uniform amplitude
		double amps = 1/Math.sqrt(indices.length);
		for (int i = 0; i < indices.length; i++){
			reg.setAmplitude(indices[i], amps, 0);
		}
		BackwardQFTCircuit bqft = new BackwardQFTCircuit(gateRep, reg.numOfQubit(), true);
		bqft.applyCircuit(reg);
		int result = reg.measure();
		ContinuedFraction conFrac = new ContinuedFraction(result, reg.numOfStates());
		ArrayList<int[]> convergents = conFrac.getConvergents();
		Iterator<int[]> iterator = convergents.iterator();
		int r = 0;
		for (int i = 0; i < convergents.size(); i++)
		{

			int temp = iterator.next()[1];
			if(temp>number){
				break;
			}
			if(temp>r){
				r = temp;
			}

		}
		//arbitary limit for now
		int max = 20;
		if(r==0){
			return 0;
		}
		for(int i=1; i<max; i++){
			if(this.modularPow(x, r*i, number)==1){
				if((r*i)%2==0){
					return r*i;
				}
			}
		}
		return -1;
	}

	//finds the solution to x^a mod n
	protected int modularPow(int _base, int _exponent, int _modulus){

		int modulus = _modulus;
		int result = 1;
		int base = _base%_modulus;
		int exponent = _exponent;
		while(exponent > 0){
			if (exponent%2 == 1){
				result = (result * base)%modulus;
			}
			exponent = exponent >> 1;
		base = (base * base)%modulus;

		}
		return result;
	}

	//simulates the effects of the second register in Shor's algorithm
	//returns the indices which the first register should partially collapse onto
	protected int [] reg2(int noOfStates, int x, int n){
		int [] states = new int[noOfStates];
		for(int i=0; i<noOfStates; i++){
			states[i] = modularPow(x, i, n);
		}
		//randomly choose a state that it would collapse onto
		Random rnd = new Random();
		int temp = rnd.nextInt(noOfStates-1);
		temp = states[temp];
		ArrayList<Integer> list = new ArrayList<Integer>();
		//this corresponds to a set of states in register 1
		for(int i=0; i<noOfStates; i++){
			if(states[i]==temp){
				list.add(new Integer(i));
			}
		}
		//return values
		int[] ret = new int[list.size()];
		Iterator<Integer> iterator = list.iterator();
		for (int i = 0; i < ret.length; i++){
			ret[i] = iterator.next().intValue();
		}
		return ret;
	} 

}
