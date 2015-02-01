package qcv1;

public abstract class QGate {
	public abstract int getTargetQbit();
	public abstract int [] getControlQbits();
	
	public void applyTo(QRegister reg){
		if (getControlQbits() == null){
			updateRegister(reg, getTargetQbit());
		} else {
			updateRegister(reg, getControlQbits(), getTargetQbit());
		}
	}
	
	//for normal gate
	public abstract void updateRegister(QRegister reg, int targetQbit);
	//for contorl gate
	public abstract void updateRegister(QRegister reg, int [] controlQbits, int targetQbit);
}
