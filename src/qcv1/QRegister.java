package qcv1;
import Matrix.*;

public interface QRegister {
	public int numOfQubit();
	public int numOfStates();
	public void setAmplitude(int qubitPos, double [] amps);
	public void setAmplitude(int qubitPos, double real, double imag);
	public double [] getAmplitude(int qubitPos);
	public Matrix getAmplitude();
	public void measure();
}
	