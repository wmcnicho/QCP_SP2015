package Matrix;

public interface IComplexMatrix extends IMatrix{
	public void setElement(int i, int j, double[] value);
	public void setReElement(int i, int j, double value);
	public void setImElement(int i, int j, double value);
	public double[] getElement(int i, int j);
	public double getReElement(int i, int j);
	public double getImElement(int i, int j);
}
