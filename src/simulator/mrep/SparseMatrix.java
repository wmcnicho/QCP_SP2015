package simulator.mrep;

import java.util.ArrayList;
import java.util.Hashtable;

//Class can handle a maximum of 31 Qubits 
public class SparseMatrix extends Matrix{
	private int row;
	private int column;
	private long size;
	
	private Hashtable<Long, Double> entryReal;
	private Hashtable<Long, Double> entryImag;
	
	
	//constructors
	public SparseMatrix(int nrow, int ncol){
		this(nrow, ncol, new double[][]{{},{}});		
	}
	
	public SparseMatrix(int nrowcol){
		this(nrowcol, nrowcol);
	}
	
	public SparseMatrix(int nrowcol, double[][] initVals){
		this(nrowcol, nrowcol, initVals);
	}
	
	public SparseMatrix(int nrow, int ncol, double[][] initVals){
		if (nrow<1) nrow=1;
		if (ncol<1) ncol=1;
		
		row = nrow;
		column = ncol;
		size = (long) row*column;
		
		//System.out.println("SIZE:  "+size);
		//System.out.println("Power: "+(int)Math.pow(2,60));
		
		entryReal = new Hashtable<Long, Double>();
		entryImag = new Hashtable<Long, Double>();
		
		long max = initVals[0].length;
		if (max>size) max=size;
		
		for (int n=0; n<max; n++){
			setReal(n, initVals[0][n]);
		}
		
		max = initVals[1].length;
		if (max>size) max=size;
		
		for (int n=0; n<max; n++){
			setImag(n, initVals[1][n]);
		}
	}
	
	//deep copy constructor of original matrix
	public SparseMatrix (SparseMatrix m1){
		this(m1.row, m1.column);
		this.entryReal = copyHashtable(m1.entryReal);
		this.entryImag = copyHashtable(m1.entryImag);
	}
	
	//deep copy a hashtable
	private Hashtable<Long, Double> copyHashtable(Hashtable<Long, Double> input){
		Hashtable<Long, Double> output = new Hashtable<Long, Double>(input.size());
		for (long i : input.keySet()){
			output.put(i,input.get(i));
		}
		return output;
	}
	
	//Output-Function
	public void printRealMatrix(){
		
	}
	public void printImagMatrix(){
		
	}
	public void printMatrix(){
		String outString;
		System.out.println("--------------");
		System.out.println("Matrix-Output:");
		System.out.println("--------------");
		for(int m = 0; m<row; m++){
			outString="(";
			for(int n = 0; n<column; n++){
				outString = outString + String.format("%6.2f", getReal(m, n)) + " ";
			}
			
			outString = outString + ")     (";
			
			for(int n = 0; n<column; n++){
				outString = outString + String.format("%6.2f", getImag(m, n)) + " ";
			}
			
			outString = outString + ")";
			System.out.println(outString);
		}
		System.out.println("--------------");
	}
	
	//---------------------------------------------------------------------
	//Get-Functions
	//---------------------------------------------------------------------
	public int getRowDimension(){return row;}
	public int getColumnDimension(){return column;}
	public long getSize(){return size;}
	
	public double getReal(long index){
		if (index<0 || index>=size) System.err.println("Index "+index+" out of bounds");
		if (entryReal.containsKey(index)){
			return entryReal.get(index);
		}
		return 0;
	}
	
	public double getReal(int i, int j){
		return getReal(j + i*column);
	}
	
	public double getImag(long index){
		if (index<0 || index>=size) System.err.println("Index "+index+" out of bounds");
		if (entryImag.containsKey(index)){
			return entryImag.get(index);
		}
		return 0;
	}
	public double getImag(int i, int j){
		return getImag(j + i*column);
	}
	
	public double [] get(long index){
		return new double [] {getReal(index), getImag(index)};
	}
	public double [] get(int i, int j){
		return get(j + i*column);
	}
	
	public Hashtable<Long, Double> getHashReal(){
		return entryReal;
	}
	public Hashtable<Long, Double> getHashImag(){
		return entryImag;
	}
	
	//---------------------------------------------------------------------
	//Set-Functions
	//---------------------------------------------------------------------
	public void setHashReal(Hashtable<Long, Double> hash){
		entryReal=hash;
	}

	public void setHashImag(Hashtable<Long, Double> hash){
		entryImag=hash;
	}
	
	public void setReal(long index, double real){
		if (index >= 0 && index < size){
			if (real == 0){
				entryReal.remove(index);
			} else {
				entryReal.put(index, real);
			}
		}
	}
	public void setReal(int i, int j, double real){		
		setReal(j + i*column , real);
	}
	
	public void setImag(long index, double imag){
		if (index >= 0 && index < size){
			if (imag == 0){
				entryImag.remove(index);
			} else {
				entryImag.put(index, imag);
			}
		}
	}
	public void setImag(int i, int j, double imag){		
		setImag(j + i*column , imag);
	}
	
	public void set(long index, double real, double imag){
		setReal(index, real);
		setImag(index, imag);
	}
	public void set(int i, int j, double real, double imag){
		setReal(j + i*column, real);
		setImag(j + i*column, imag);
	}
	
	public void set(long index, double [] z){
		setReal(index, z[0]);
		setImag(index, z[1]);
	}
	public void set(int i, int j, double [] z){
		set(j + i*column, z);
	}
	
	//matrix algebra
	public void addBy(Matrix m) {
		if (canAdd(this,m)){
			if (m instanceof SparseMatrix){
				addBy((SparseMatrix) m);
			} else {
				for (long i = 0; i < m.getSize(); i++){
					//add real part
					if (m.getReal(i) != 0){
						entryReal.put(i, entryReal.get(i) + m.getReal(i));
					}
					//add imaginary part
					if (m.getImag(i) != 0){
						entryImag.put(i, entryImag.get(i) + m.getImag(i));
					}
				}
			}
		}
	}
	
	public void addBy(SparseMatrix m){
		//add real part
		for (long index : m.entryReal.keySet()){
			entryReal.put(index, entryReal.get(index) + m.entryReal.get(index));
		}
		//add imaginary part
		for (long index : m.entryImag.keySet()){
			entryImag.put(index,  entryImag.get(index) + m.entryImag.get(index));
		}
	}
	
	public void preMultiplyBy(Matrix m){
		if (canMultiply(m,this)){
			int mrow = m.getRowDimension();
			
			Hashtable<Long, Double> real = new Hashtable<Long, Double>();
			Hashtable<Long, Double> imag = new Hashtable<Long, Double>();
			
			double dReal, dImag;
			for (int i = 0; i < mrow; i++){
				for (int k = 0; k < column; k++){
					dReal = 0;
					dImag = 0;
					for (int j = 0; j < row; j++){
						dReal = dReal + m.getReal(i, j) * this.getReal(j, k) - m.getImag(i, j) * this.getImag(j, k);
						dImag = dImag + m.getReal(i, j) * this.getImag(j, k) + m.getImag(i, j) * this.getReal(j, k);
					}
					if (dReal != 0){
						real.put((long)i*column+k, dReal);
					}
					if (dImag != 0){
						imag.put((long)i*column+k, dImag);
					}
				}
			}
			entryReal = real;
			entryImag = imag;
		}
	}

	public void preMultiplyBy(SparseMatrix m) {
		
		int row = m.row;
		int column = this.column;
		/*
		 * ensure all keys for complex numbers with non-zero real or 
		 * imaginary part are included
		 */
		
		ArrayList<Long> index = new ArrayList<Long>();
		index.addAll(m.entryReal.keySet());
		for (Long i : entryImag.keySet()){
			if (!index.contains(i)){
				index.add(i);
			}
		}
		
		//instantiate the result hashtables
		Hashtable<Long, Double> real = new Hashtable<Long, Double>();
		Hashtable<Long, Double> imag = new Hashtable<Long, Double>();
				
		//perform the matrix multiplication
		for (Long i : index){
			//need to resolve the real and imag part
			
		}
		
	}

	@Override
	public void postMultiplyBy(Matrix m) {
		// TODO Auto-generated method stub
		
	}
	
	//---------------------------------------------------------------------
	// ADD-Matrix
	//---------------------------------------------------------------------
	/*public static SparseMatrix add(SparseMatrix m1, SparseMatrix m2){
		int row = m1.row;
		int column = m1.column;
		
		if (row!=m2.row || column!=m2.column){
			System.err.println("The matrices have different sizes!");
			return null;
		}
		
		SparseMatrix m3 = SparseMatrix.copy(m1);
		
		for (Long index : m2.entryReal.keySet()){
          m3.setReal(index, m1.getReal(index) + m2.entryReal.get(index));
      }
		
		for (Long index : m2.entryImag.keySet()){
          m3.setImag(index, m1.getImag(index) + m2.entryImag.get(index));
      }
		
		return m3;
	}
	
	public boolean add(SparseMatrix m){
		if (row!=m.row || column!=m.column){
			System.err.println("The matrices have different sizes!");
			return false;
		}
		
		for (Long index : m.entryReal.keySet()){
          setReal(index, getReal(index) + m.entryReal.get(index));
      }

		for (Long index : m.entryImag.keySet()){
          setImag(index, getImag(index) + m.entryImag.get(index));
      }
		
		return true;
	}*/
	//---------------------------------------------------------------------
	// SCALAR-Matrix
	//---------------------------------------------------------------------
	public void multiplyBy(double d){

		for (Long index : entryReal.keySet()){
          setReal(index, d*getReal(index) );
      }
		for (Long index : entryImag.keySet()){
          setImag(index, d*getImag(index) );
      }
	}
	//---------------------------------------------------------------------
	// TRANSPOSE-Matrix
	//---------------------------------------------------------------------
	/*public boolean transpose(){
		Hashtable<Long, Double> entryRealT = new Hashtable<Long, Double>();
		Hashtable<Long, Double> entryImagT = new Hashtable<Long, Double>();
		
		for (Long index : entryReal.keySet()){
          int m = (int) Math.ceil(((double)index)/column);//row
          int n = ((index-1) % column)+1;//column
          
          entryRealT.put(m + (n-1)*row, entryReal.get(index));
      }
		for (Long index : entryImag.keySet()){
          int m = (int) Math.ceil(((double)index)/column);//row
          int n = ((index-1) % column)+1;//column
          
          entryImagT.put(m + (n-1)*row, entryImag.get(index));
      }
		int temp = row;
		row = column;
		column = temp;
		
		entryReal = entryRealT;
		entryImag = entryImagT;
		
		return true;
	}*/
	
	//---------------------------------------------------------------------
	// MULTIPLY-Matrix
	//---------------------------------------------------------------------
	public static SparseMatrix Multiply(SparseMatrix mL, SparseMatrix mR)
	//SLOW VERSION (but working correctly)
	{
		if (mL.column != mR.row){
			System.err.println("The matrices can't be multiplied!");
			return null;
		}
		
		SparseMatrix m3 = new SparseMatrix(mL.row, mR.column);
		
		double dReal, dImag;
		for (int m=0; m<mL.row; m++){
			for (int p=0; p<mR.column; p++){
				dReal=0;
				dImag=0;
				for (int n=0; n<mL.column; n++){
					//a1  mL.getRealPart(m, n)
					//a2  mL.getImagPart(m, n)
					//b1  mR.getRealPart(n, p)
					//b2  mR.getImagPart(n, p)
					
					dReal = dReal + mL.getReal(m, n)*mR.getReal(n, p) - mL.getImag(m, n)*mR.getImag(n, p);
					dImag = dImag + mL.getReal(m, n)*mR.getImag(n, p) + mL.getImag(m, n)*mR.getReal(n, p);
				}
				m3.setReal(m, p, dReal);
				m3.setImag(m, p, dImag);
			}
		}
		
		return m3;
	}
}	
