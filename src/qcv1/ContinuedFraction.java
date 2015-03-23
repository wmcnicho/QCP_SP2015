package qcv1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Calculates the continued fraction of x/y
 * Can also work out convergents
 * @author Gennaro
 *
 */
public class ContinuedFraction {

	private int x,y;
	//used to store values while computations are carried out
	private ArrayList<Integer> tempCoeff = new ArrayList<Integer>();
	private int[] coeff;
	private ArrayList<int[]> convergents = new ArrayList<int[]>();
	
	/**
	 * Creates a continued fraction object from which the coefficients and convergents can be retrieved
	 * @param x numerator of the fraction
	 * @param y	denominator of the fraction
	 */
public ContinuedFraction(int x, int y){
		if(x!=0){
			this.x=x;
			this.y=y;
			tempCoeff.add(0);
			this.generateCoeffs(x, y);
			coeff = new int[tempCoeff.size()];
			Iterator<Integer> iterator = tempCoeff.iterator();
			for (int i = 0; i < tempCoeff.size(); i++)
			{
				coeff[i] = iterator.next().intValue();
			}
			this.calcConvergents();
		}
		else{
			coeff = new int[1];
			coeff[0] = 0;
			convergents.add(new int[]{0,0});
		}
	}
	
	//recursive method to calculate the coefficients
	private void generateCoeffs(int x, int y){
		int a=y/x;
		int y1=x;
		int x1=y%x;
		tempCoeff.add(new Integer(a));
		if(x1!=0){
			generateCoeffs(x1, y1);
		}
	}
	
	//calculates a single step of the convergents
	private int[] convergentStep(int a, int b, int n){
		int x = b;
		int y = a*b+n;
		return ContinuedFraction.Simplify(x,y);
	}
	
	
	private int[] convergent(int start){
		int[] result = new int[]{1,coeff[start]};
		for(int i=start; i>0; i--){
			result = convergentStep(coeff[i-1], result[1], result[0]);
		}
		return new int[]{result[1], result[0]};
	}
	
	
	private void calcConvergents(){
		//first one is always 1/a1
		convergents.add(new int[]{1,coeff[1]});
		for(int i=2; i<coeff.length-1; i++){
			convergents.add(convergent(i));
		}
		convergents.add(ContinuedFraction.Simplify(x, y));
	}
	
	/**
	 *Returns the convergents in an array list, each entry contains an array [x,y]
	 * which represents the fraction x/y
	 * @return Array list of convergents
	 */
	public ArrayList<int[]> getConvergents(){
		return convergents;
	}
	
	/**
	 * simplifies the fraction x/y and returns it in an array [x,y]
	 * @param a numerator of the fraction
	 * @param b denominator of the fraction
	 * @return an array containing the fraction
	 */
	private static int[] Simplify(int _x, int _y){
		int x=_x,y=_y;
		int z = ContinuedFraction.gcd(x,y);
		while(z!=1){
			z = ContinuedFraction.gcd(x,y);
			x=x/z;
			y=y/z;
		};
		return new int[]{x,y};
	}
	
	/**
	 * Finds the highest common factor of two numbers
	 * @param a a number
	 * @param b another number
	 * @return the highest common factor of a and b
	 */
	private static int gcd(int a, int b){
		while (b != 0){
			int t = b;
			b = a % b;
			a = t;
		}
		return a;
	}
}
