package Matrix;

import java.util.Hashtable;
/**
 * General sparse matrix implementation using hash tables to store values.
 * Still used by SparseMatrix while setting elements but never used in the project otherwise
 * 
 * @author Christoph
 * @deprecated 
 */
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
}
