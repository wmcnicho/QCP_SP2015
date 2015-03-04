package Matrix;

import java.util.Hashtable;

//Class can handle a maximum of 59 Qubits 
public class SparseHMatrix {
	protected long Nrow;
	protected long Ncol;
	protected long size;
	
	protected Hashtable<Long, Double> EntryReal;
	protected Hashtable<Long, Double> EntryImag;
	
	
	//constructors
	public SparseHMatrix(long nrow, long ncol){
		this(nrow, ncol, new double[][]{{},{}});		
	}
	
	public SparseHMatrix(long nrowcol){
		this(nrowcol, nrowcol);
	}
	
	public SparseHMatrix(long nrowcol, double[][] initVals){
		this(nrowcol, nrowcol, initVals);
	}
	
	public SparseHMatrix(long nrow, long ncol, double[][] initVals){
		if (nrow<1) nrow=1;
		if (ncol<1) ncol=1;
		
		Nrow = nrow;
		Ncol = ncol;
		size = Nrow*Ncol;
		
		//System.out.println("SIZE:  "+size);
		//System.out.println("Power: "+(long)Math.pow(2,60));
		
		EntryReal = new Hashtable<Long, Double>();
		EntryImag = new Hashtable<Long, Double>();
		
		long max = initVals[0].length;
		if (max>size) max=size;
		
		for(int n=0; n<max; n++)
		{
			setRealPart(n+1, initVals[0][n]);
		}
		
		max = initVals[1].length;
		if (max>size) max=size;
		
		for(int n=0; n<max; n++)
		{
			setImagPart(n+1, initVals[1][n]);
		}
		
	}
	

	
	//copy
	static public SparseHMatrix copy(SparseHMatrix m1)
	{
		SparseHMatrix m2 = new SparseHMatrix(m1.Nrow, m1.Ncol);
		m2.EntryReal = m1.EntryReal;
		m2.EntryImag = m1.EntryImag;
		return m2;
	}
	
	//Output-Function
	public void printMatrix()
	{
		String outString;
		System.out.println("--------------");
		System.out.println("Matrix-Output:");
		System.out.println("--------------");
		for(int m = 1; m<=Nrow; m++)
		{
			outString="(";
			for(int n = 1; n<=Ncol; n++)
			{
				outString = outString + String.format("%6.2f", getRealPart(m, n)) + " ";
			}
			
			outString = outString + ")     (";
			
			for(int n = 1; n<=Ncol; n++)
			{
				outString = outString + String.format("%6.2f", getImagPart(m, n)) + " ";
			}
			
			outString = outString + ")";
			System.out.println(outString);
		}
		System.out.println("--------------");
	}
	
	//---------------------------------------------------------------------
	//Get-Functions
	//---------------------------------------------------------------------
	public long getNrow()
	{
		return Nrow;
	}
	public long getNcol()
	{
		return Ncol;
	}
	
	public double getRealPart(long index)
	{
		if (index<1 || index>size) System.err.println("Index "+index+" out of bounds");
		
		if (EntryReal.containsKey(index))
		{
			return EntryReal.get(index);
		}
		return 0;
	}
	
	public double getRealPart(long row, long col)
	{
		return getRealPart( col + (row-1)*Ncol );
	}
	
	public double getImagPart(long index)
	{
		if (index<1 || index>size) System.err.println("Index "+index+" out of bounds");
		
		if (EntryImag.containsKey(index))
		{
			return EntryImag.get(index);
		}
		return 0;
	}
	
	public double getImagPart(long row, long col)
	{
		return getImagPart( col + (row-1)*Ncol );
	}
	
	public Hashtable<Long, Double> getHashReal()
	{
		return EntryReal;
	}

	public Hashtable<Long, Double> getHashImag()
	{
		return EntryImag;
	}
	
	//---------------------------------------------------------------------
	//Set-Functions
	//---------------------------------------------------------------------
	public void setHashReal(Hashtable<Long, Double> hash)
	{
		EntryReal=hash;
	}

	public void setHashImag(Hashtable<Long, Double> hash)
	{
		EntryImag=hash;
	}
	
	public boolean setRealPart(long index, double realPart)
	{
		if (index>=1 && index<=size)
		{
			if(realPart==0)
			{
				EntryReal.remove(index);
			}
			else
			{
				EntryReal.put(index, realPart);
			}
			return true;
		}
		return false;
	}
	
	public boolean setImagPart(long index, double imagPart)
	{
		if (index>=1 && index<=size)
		{
			if(imagPart==0)
			{
				EntryImag.remove(index);
			}
			else
			{
				EntryImag.put(index, imagPart);
			}
			return true;
		}
		return false;
	}
	
	public boolean setRealPart(long row, long col, double realPart)
	{		
		return setRealPart( col + (row-1)*Ncol , realPart);
	}
	
	public boolean setImagPart(long row, long col, double imagPart)
	{		
		return setImagPart( col + (row-1)*Ncol , imagPart);
	}
	
	public boolean setEntry(long index, double realPart, double imagPart)
	{
		return (setRealPart(index, realPart) && setImagPart(index, imagPart));
	}
	
	public boolean setEntry(long row, long col, double realPart, double imagPart)
	{
		return (setRealPart(col + (row-1)*Ncol, realPart) && setImagPart(col + (row-1)*Ncol, imagPart));
	}
	
	//---------------------------------------------------------------------
	// ADD-Matrix
	//---------------------------------------------------------------------
	static public SparseHMatrix Add(SparseHMatrix m1, SparseHMatrix m2)
	{
		long Nrow = m1.Nrow;
		long Ncol = m1.Ncol;
		
		if(Nrow!=m2.Nrow || Ncol!=m2.Ncol)
		{
			System.err.println("The matrices have different sizes!");
			return null;
		}
		
		SparseHMatrix m3 = SparseHMatrix.copy(m1);
		
		for (Long index : m2.EntryReal.keySet())
		{
            m3.setRealPart(index, m1.getRealPart(index) + m2.EntryReal.get(index));
        }
		
		for (Long index : m2.EntryImag.keySet())
		{
            m3.setImagPart(index, m1.getImagPart(index) + m2.EntryImag.get(index));
        }
		
		return m3;
	}
	
	public boolean Add(SparseHMatrix m)
	{
		if(Nrow!=m.Nrow || Ncol!=m.Ncol)
		{
			System.err.println("The matrices have different sizes!");
			return false;
		}
		
		for (Long index : m.EntryReal.keySet())
		{
            setRealPart(index, getRealPart(index) + m.EntryReal.get(index));
        }

		for (Long index : m.EntryImag.keySet())
		{
            setImagPart(index, getImagPart(index) + m.EntryImag.get(index));
        }
		
		return true;
	}
	//---------------------------------------------------------------------
	// SCALAR-Matrix
	//---------------------------------------------------------------------
	public boolean Rescale(double d)
	{

		for (Long index : EntryReal.keySet())
		{
            setRealPart(index, d*getRealPart(index) );
        }
		for (Long index : EntryImag.keySet())
		{
            setImagPart(index, d*getImagPart(index) );
        }
		
		return true;
	}
	//---------------------------------------------------------------------
	// TRANSPOSE-Matrix
	//---------------------------------------------------------------------
	public boolean Transpose()
	{
		Hashtable<Long, Double> EntryRealT = new Hashtable<Long, Double>();
		Hashtable<Long, Double> EntryImagT = new Hashtable<Long, Double>();
		
		for (Long index : EntryReal.keySet())
		{
            long m = (long) Math.ceil(((double)index)/Ncol);//row
            long n = ((index-1) % Ncol)+1;//column
            
            EntryRealT.put(m + (n-1)*Nrow, EntryReal.get(index));
        }
		for (Long index : EntryImag.keySet())
		{
            long m = (long) Math.ceil(((double)index)/Ncol);//row
            long n = ((index-1) % Ncol)+1;//column
            
            EntryImagT.put(m + (n-1)*Nrow, EntryImag.get(index));
        }
		long temp = Nrow;
		Nrow = Ncol;
		Ncol = temp;
		
		EntryReal = EntryRealT;
		EntryImag = EntryImagT;
		
		return true;
	}
	
	//---------------------------------------------------------------------
	// MULTIPLY-Matrix
	//---------------------------------------------------------------------
	static public SparseHMatrix Multiply(SparseHMatrix mL, SparseHMatrix mR)
	//SLOW VERSION (but working correctly)
	{
		if(mL.Ncol != mR.Nrow)
		{
			System.err.println("The matrices can't be multiplied!");
			return null;
		}
		
		SparseHMatrix m3 = new SparseHMatrix(mL.Nrow, mR.Ncol);
		
		double dReal, dImag;
		for (long m=1; m<=mL.Nrow; m++)
		{
			for (long p=1; p<=mR.Ncol; p++)
			{
				dReal=0;
				dImag=0;
				for (long n=1; n<=mL.Ncol; n++)
				{
					//a1  mL.getRealPart(m, n)
					//a2  mL.getImagPart(m, n)
					//b1  mR.getRealPart(n, p)
					//b2  mR.getImagPart(n, p)
					
					dReal = dReal + mL.getRealPart(m, n)*mR.getRealPart(n, p) - mL.getImagPart(m, n)*mR.getImagPart(n, p);
					dImag = dImag + mL.getRealPart(m, n)*mR.getImagPart(n, p) + mL.getImagPart(m, n)*mR.getRealPart(n, p);
				}
				m3.setRealPart(m, p, dReal);
				m3.setImagPart(m, p, dImag);
			}
		}
		
		return m3;
	}
}