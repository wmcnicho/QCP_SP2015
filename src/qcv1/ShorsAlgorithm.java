package qcv1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

public class ShorsAlgorithm {
	private int number;
	private Random rand = new Random();
	private String gateRep;
	private int numOfQubits;
	private int numOfStates;
	private int x;
	public static void main(String[] args) {
		int num = 1017;
		for(int i=0; i<100; i++){
			ShorsAlgorithm test = new ShorsAlgorithm("functional", num);
			int[] vals = test.run();
			System.out.println(Arrays.toString(vals));
			if(vals[0]*vals[1]==num){
				System.out.println("Pass");
			}else{
				System.out.println("Fail");
				System.out.println(vals[0]);
				System.out.println(vals[1]);
			}
		}
	}
	//constructor
	public ShorsAlgorithm(String rep, int num){
		gateRep = rep;
		number = num;
		//if number has n bits, need 2n+1 qubits
		numOfQubits = (int) Math.ceil(Math.log(13) / Math.log(2))*2+1;
		numOfStates = (int) Math.pow(2, numOfQubits);

	}

	public int [] run(){
		//choose random int x, 1 <= x <= N-1
		x = rand.nextInt(number-2) + 1;
		//find highest common factor
		int factor = gcd(x,number);
		if (factor != 1){
			System.out.println("Got lucky");
			return new int [] {factor, gcd(number/factor, number)};
		}

		//do order finding 
		int r = orderFinding();
		int y = this.modularPow(x, r/2, number);
		if (y==-1){
			System.out.println("something broke");
			return null;
		}
		int[] out = new int[2];
		out[0] = ShorsAlgorithm.gcd(y+1, number);
		out[1] = ShorsAlgorithm.gcd(y-1, number);
		return out;
	}

	public static int gcd(int a, int b){
		if (b == 0){
			return a;
		}
		return gcd(b, a % b);
	}

	public int orderFinding(){
		MRegister reg = new MRegister(numOfQubits, "complex");
		int [] indices = reg2(numOfStates, x, number);

		//set the corresponding indices in first register with uniform amplitude
		double amps = 1/Math.sqrt(indices.length);
		for (int i = 0; i < indices.length; i++){
			reg.setAmplitude(indices[i], amps, 0);
		}
		BackwardQFTCircuit bqft = new BackwardQFTCircuit(gateRep, reg.numOfQubit());
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
		System.out.println("What happened?");
		return -1;
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
