package qcv1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class ContinuedFraction {

	private int x,y;
	private ArrayList<Integer> tempCoeff = new ArrayList<Integer>();
	private int[] coeff;
	private ArrayList<int[]> convergents = new ArrayList<int[]>();
	
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
			coeff[0] = 0;
			convergents.add(new int[]{0,0});
		}
	}
	
	private void generateCoeffs(int x, int y){
		int a=y/x;
		int y1=x;
		int x1=y%x;
		tempCoeff.add(new Integer(a));
		if(x1!=0){
			generateCoeffs(x1, y1);
		}
	}
	
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
		convergents.add(new int[]{x,y});
	}
	
	public ArrayList<int[]> getConvergents(){
		return convergents;
	}
	
	public static int[] Simplify(int a, int b){
		int x=a,y=b;
		int z = ContinuedFraction.gcd(x,y);
		while(z!=1){
			z = ContinuedFraction.gcd(x,y);
			x=x/z;
			y=y/z;
		};
		return new int[]{x,y};
	}
	
	public static int gcd(int a, int b){
		while (b != 0){
			int t = b;
			b = a % b;
			a = t;
		}
		return a;
	}
	
	public static void main(String[] args) {
		ContinuedFraction frac = new ContinuedFraction(4915, 8192);
		System.out.println(Arrays.toString(frac.coeff));
		ArrayList<int[]> convergents=frac.convergents;
		for(int i=0; i<convergents.size(); i++){
			System.out.println(Arrays.toString(convergents.get(i)));
		}
	}
}
