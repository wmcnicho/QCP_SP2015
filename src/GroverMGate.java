
public class GroverMGate extends MGate{
	int findThis;
	@Override
	public Matrix resultForOn() {
		return null;
	}

	@Override
	public Matrix resultForOff() {
		return null;
	}
	
	public void setTarget(int findThis){
		this.findThis = findThis;
	//	System.out.println(findThis);
	}
	//Creates Grover step matrix, try to only call this once
	//will always find list index 0
	public Matrix output(int noOfQbits){
		int maxNum = (int) Math.pow(2, noOfQbits);
		//create oracle
		Matrix oracle = new Matrix(maxNum, maxNum);
		for(int i=0; i<maxNum; i++){
			oracle.setElement(i, i, 1);
		}
		oracle.setElement(findThis, findThis, -1);
		MGate hadmard = new MHGate();
		Matrix temp = new Matrix(maxNum, maxNum);
		temp = hadmard.output(noOfQbits, 0);
		for(int i=1; i<noOfQbits; i++){
			temp = Matrix.multiply(temp, hadmard.output(noOfQbits, i));
		}
		Matrix out = Matrix.multiply(oracle, temp);
		return Matrix.multiply(out, out);
	}
}
