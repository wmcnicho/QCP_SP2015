

public class MZGate extends MGate {
	public Matrix resultForOff(){
		Matrix m = new Matrix(2,1);
		m.setElement(0,0,1.0);
		m.setElement(1,0,0.0);
		return m;
	}
	public Matrix resultForOn(){
		Matrix m = new Matrix(2,1);
		m.setElement(0,0,0.0);
		m.setElement(1,0,-1.0);
		return m;
	}
}
