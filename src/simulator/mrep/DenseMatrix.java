package simulator.mrep;

public class DenseMatrix extends Matrix{
	protected int row;
	protected int column;
	protected long size;
	protected double[] reMatrix;
	protected double[] imMatrix;

	//constructors
	public DenseMatrix(int m, int n){
		if (m < 0) m = 0;
		row = m;
		if (n < 0) n = 0;
		column = n;
		size = row*column;
		reMatrix = new double[(int)size];
		imMatrix = new double[(int)size];
		initMatrix();
		//keeps java array indexing convention
	}
	
	//deep copy constructor
	public DenseMatrix(DenseMatrix m){
		this(m.row, m.column);
		reMatrix = copyArray(m.reMatrix);
		imMatrix = copyArray(m.imMatrix);
	}
	
	//deep copy of array
	private double [] copyArray(double [] input){
		double [] output = new double [input.length];
		for (int i = 0; i < input.length; i++){
			output[i] = input[i];
		}
		return output;
	}
	
	public void initMatrix(){
		for (int i = 0; i < size; i++){
			reMatrix[i] = 0.0;
			imMatrix[i] = 0.0;
		}
	}

	//accessor methods
	public void set(long index, double [] value){
		if (index < size && index >= 0){
			reMatrix[(int) index] = value[0];
			imMatrix[(int) index] = value[1];
		}
	}
	public void set(int i, int j, double[] value){
		set((long) i*column+j, value);
	}
	
	public void set(long index, double real, double imag){
		if (index < size && index >= 0){
			reMatrix[(int) index] = real;
			imMatrix[(int) index] = imag;
		}
	}
	public void set(int i, int j, double real, double imag){
		set(i*column+j, real, imag);
	}
	
	public void setReal(long index, double value){
		if (index < size && index >= 0){
			reMatrix[(int) index] = value;
		}
	}
	public void setReal(int i, int j, double value){
		setReal(i*column+j, value);
	}
	
	public void setImag(long index, double value){
		if (index < size && index >= 0){
			imMatrix[(int) index] = value;
		}
	}
	public void setImag(int i, int j, double value){
		setImag(i*column+j, value);
	}
	
	public void setReal(double [] value){
		int max = value.length;
		if (max > reMatrix.length){
			max = reMatrix.length;
		}
		for (int i = 0; i < max; i++){
			reMatrix[i] = value[i];
		}
	}
	
	public void setImag(double [] value){
		int max = value.length;
		if (max > imMatrix.length){
			max = imMatrix.length;
		}
		for (int i = 0; i < max; i++){
			imMatrix[i] = value[i];
		}
	}
	
	public double [] get(long index){
		if (index < size && index >= 0){
			return new double [] {reMatrix[(int) index], imMatrix[(int) index]};
		}
		return new double [] {0.0,0.0};
	}
	public double[] get(int i, int j){
		return get(i*column+j);
	}
	
	public double getReal(long index){
		if (index < size && index >= 0){
			return reMatrix[(int) index];
		}
		return 0;
	}
	public double getReal(int i, int j){
		return getReal(i*column+j);
	}
	
	public double getImag(long index){
		if (index < size && index >= 0){
			return imMatrix[(int) index];
		}
		return 0;
	}
	public double getImag(int i, int j){
		return getImag(i*column+j);
	}
	
	public int getRowDimension(){return row;}
	public int getColumnDimension(){return column;}
	public long getSize(){return size;}

	public void printRealMatrix(){
		for (int i = 0; i < row; i++){
			for (int j = 0; j < column; j++){
				System.out.printf("%.2f\t",reMatrix[i*column+j]);
			}
			System.out.println();
		}
	}

	public void printImagMatrix(){
		for (int i = 0; i < row; i++){
			for (int j = 0; j < column; j++){
				System.out.printf("%.2f\t",imMatrix[i*column+j]);
			}
			System.out.println();
		}
	}
	
	public void printMatrix(){
		for (int i = 0; i < row; i++){
			for (int j = 0; j < column; j++){
				System.out.printf("%.2f + %.2fi\t",reMatrix[i*column+j],imMatrix[i*column+j]);
			}
			System.out.println();
		}
	}

	//matrix algebra methods
	public void addBy(Matrix m){
		if (canAdd(m, this)){
			for (int i = 0; i < size; i++){
				reMatrix[i] += m.getReal(i);
				imMatrix[i] += m.getImag(i);
			}
		}
	}
	
	public void multiplyBy(double k){
		for (int i = 0; i < size; i++){
			reMatrix[i] *= k;
			imMatrix[i] *= k;
		}
	}
	
	public void preMultiplyBy(DenseMatrix m){	
		if (canMultiply(m,this)){
			size = m.row * this.column;
			double [] real = new double [(int) size];
			double [] imag = new double [(int) size];
			
			for(int global = 0; global<size; global++){
				int i = global / this.column;
				int k = global % this.column;
				double reValue = 0;
				double imValue = 0;
				for(int j = 0; j < this.row; j++){
					int eleA = i * m.column + j;
					int eleB = j * this.column + k;
					reValue += m.reMatrix[eleA] * this.reMatrix[eleB] - m.reMatrix[eleA] * this.imMatrix[eleB];
					imValue += m.reMatrix[eleA] * this.imMatrix[eleB] +  m.imMatrix[eleA] * this.reMatrix[eleB];
				}
				real[i * this.column + k] = reValue;
				imag[i * this.column + k] = imValue;

			}
			reMatrix = real;
			imMatrix = imag;
			
			//update row and column length
			this.row = m.getRowDimension();
		}
	}
	
	public void preMultiplyBy(Matrix m){
		if (canMultiply(m,this)){
			if (m instanceof DenseMatrix){
				preMultiplyBy((DenseMatrix) m);
			} else {
				int mrow = m.getRowDimension();
				int mcolumn = m.getColumnDimension();
				size = mrow * this.column;
				double [] real = new double [(int) size];
				double [] imag = new double [(int) size];
				
				for(int global = 0; global<size; global++){
					int i = global / this.column;
					int k = global % this.column;
					double reValue = 0;
					double imValue = 0;
					for(int j = 0; j < this.row; j++){
						int eleA = i * mcolumn + j;
						int eleB = j * this.column + k;
						reValue += m.getReal(eleA) * this.reMatrix[eleB] - m.getImag(eleA) * this.imMatrix[eleB];
						imValue += m.getReal(eleA) * this.imMatrix[eleB] +  m.getImag(eleA) * this.reMatrix[eleB];
					}
					real[i * this.column + k] = reValue;
					imag[i * this.column + k] = imValue;

				}
				reMatrix = real;
				imMatrix = imag;
				
				//update row and column length
				this.row = mrow;
			}
		}
	}

	public void postMultiplyBy(Matrix m) {
		int mcolumn = m.getColumnDimension();
		size = this.row * mcolumn;
		double [] real = new double [(int) size];
		double [] imag = new double [(int) size];
		
		for(int global = 0; global<size; global++){
			int i = global / mcolumn;
			int k = global % mcolumn;
			double reValue = 0;
			double imValue = 0;
			for(int j = 0; j < this.row; j++){
				int eleA = i * this.column + j;
				int eleB = j * mcolumn + k;
				reValue += this.reMatrix[eleA] * m.getReal(eleB) - this.imMatrix[eleA] * m.getImag(eleB);
				imValue += this.reMatrix[eleA] * m.getImag(eleB) + this.imMatrix[eleA] * m.getReal(eleB);
			}
			real[i * mcolumn + k] = reValue;
			imag[i * mcolumn + k] = imValue;
		}
		reMatrix = real;
		imMatrix = imag;
		
		//update row and column length
		this.row = m.getRowDimension();
		
	}
}
