package qcv1;

public interface QGate {
	public void applyGate(QRegister reg);
	public String gateType();
}
