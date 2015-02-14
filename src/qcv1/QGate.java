package qcv1;

public abstract class QGate {
	protected int target;
	protected int [] controls = null;

	public QGate(int [] controlQbits, int targetQbit){
		target = targetQbit;
		controls = controlQbits;
	}
	
	public abstract void applyGate();
}
