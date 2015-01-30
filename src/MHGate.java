

public class MHGate extends MGate{
	public Matrix resultForOff(){
		Matrix m = new Matrix(2,1);
		m.setElement(0,0,1.0/Math.sqrt(2));
		m.setElement(1,0,1.0/Math.sqrt(2));
		return m;
	}
	public Matrix resultForOn(){
		Matrix m = new Matrix(2,1);
		m.setElement(0,0,1.0/Math.sqrt(2));
		m.setElement(1,0,-1.0/Math.sqrt(2));
		return m;
	}
}
