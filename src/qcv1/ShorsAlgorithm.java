package qcv1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class ShorsAlgorithm {
	private int number;
	private Random rand = new Random();
	private String gateRep;
	private int numOfQubits;
	private int numOfStates;
	private int x;
	//constructor
	public ShorsAlgorithm(String rep, int num){
		gateRep = rep;
		number = num;
		
		numOfQubits = (int) Math.ceil(Math.log(num) / Math.log(2));
		numOfStates = (int) Math.pow(2, numOfQubits);
		
		//check if the number is even
		if (number % 2 == 0){
			
		}
		//check if it is a squared number
	}
	
	public double [] run(){
		//choose random int x, 1 <= x <= N-1
		x = rand.nextInt(number-1) + 1;
		
		//find highest common factor
		if (gcd(x,number) != 1){
			return new double [] {x, number / x};
		}
		//do order finding 
		int r = orderFinding();
		int y = (int)Math.pow(x, r/2);
		if (y==-1){
			System.out.println("something broke");
			return null;
		}
		double[] out = new double[2];
		out[0] = ShorsAlgorithm.gcd(y+1, number);
		out[1] = ShorsAlgorithm.gcd(y-1, number);
		return out;
	}
	
	public static int gcd(int a, int b){
		while (b != 0){
			int t = b;
			b = a % b;
			a = t;
		}
		return a;
	}
	
	public int orderFinding(){
		MRegister reg = new MRegister(numOfStates, "complex");
		int [] indices = reg2(numOfStates, x, number);
		
		//set the corresponding indices in first register with uniform amplitude
		double amps = 1/Math.sqrt(indices.length);
		for (int i = 0; i < indices.length; i++){
			reg.setAmplitude(i, amps, 0);
		}
		BackwardQFTCircuit bqft = new BackwardQFTCircuit(gateRep, reg.numOfQubit());
		bqft.applyCircuit(reg);
		int result = reg.measure();
		ContinuedFraction conFrac = new ContinuedFraction(result, reg.numOfStates());
		ArrayList<int[]> convergents = conFrac.getConvergents();
		int r = 0;
		int max = 0;
		int order;
		
		
		//get convergents of c/q to be d/r. pick largest r such that r<n
		for(int i=0; i<max; i++){
			if((int)Math.pow(x, r*i)%n==1){
				return r*i;
			}
		}
		
	}
	
	public int modularPow(int _base, int _exponent, int _modulus){

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

	public int [] reg2(int noOfStates, int x, int n){
		int [] states = new int[noOfStates];
		for(int i=0; i<noOfStates; i++){
			states[i] = modularPow(x, i, n);
		}
		Random rnd = new Random();
		int temp = rnd.nextInt(noOfStates-1);
		temp = states[temp];
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		for(int i=0; i<noOfStates; i++){
			if(states[i]==temp){
				list.add(new Integer(i));
			}
		}
		
		int[] ret = new int[list.size()];
		Iterator<Integer> iterator = list.iterator();
		for (int i = 0; i < ret.length; i++){
			ret[i] = iterator.next().intValue();
		}
		return ret;
	} 
	 
}
